package com.fhzc.app.system.controller.personal;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;


import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Double_J on 2016/7/7 15:22
 */
@Controller
@RequestMapping(value = "personal/planner")
public class PlannerController extends BaseController {

    @Resource
    private PlannerService plannerService;

    @Resource
    private UserService userService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private AreasService areasService;

    @Resource
    private PlannerAchivementsDailyService plannerAchivementsDailyService;

    @Resource
    private PlannerAchivementsMonthlyService plannerAchivementsMonthlyService;

    /**
     * 理财师列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listPlanners(){
        ModelAndView mav = new ModelAndView("personal/planner/list");
        PageableResult<Planner> pageableResult = plannerService.findPagePlanners(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("planners", pageableResult.getItems());

        //查询用户表的理财师信息
        List<Planner> planners = pageableResult.getItems();
        List<User> users = new ArrayList<User>();
        for(Planner planner : planners){
            User user = userService.getUser(planner.getUid());
            try {
                user.setPassportCode(EncryptUtils.decryptByDES(user.getSalt(), user.getPassportCode()));
                user.setMobile(EncryptUtils.decryptByDES(user.getSalt(),user.getMobile()));
                user.setEmail(EncryptUtils.decryptByDES(user.getSalt(), user.getEmail()));
            } catch (Exception e) {
                logger.error("解密失败");
            }
            users.add(user);
        }

        mav.addObject("users", users);
        mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
        mav.addObject("areas", areasService.getAllAreas());
        mav.addObject("url", "personal/planner");
        return mav;
    }

    /**
     * 在职理财师导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importPlanner(){
        ModelAndView mav = new ModelAndView("personal/planner/importor");
        return mav;
    }

    /**
     * excel导入--在职
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView importExcel(MultipartFile multiFile){
    	ModelAndView mav = new ModelAndView("personal/planner/importor");
    	Map<String,Object> result = new HashMap<String,Object>();
        try {
        	result = plannerService.importExcelFile(multiFile);
        	result = plannerService.importDepartmentExcelFile(multiFile);
        	result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
        }
        return mav;
    }

    /**
     * 离职理财师导入页面
     * @return
     */
    @RequestMapping(value = "/importoroff", method = RequestMethod.GET)
    public ModelAndView importOffPlanner(){
        ModelAndView mav = new ModelAndView("personal/planner/importoroff");
        return mav;
    }

    /**
     * excel导入--离职
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importoff", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView importOffExcel(MultipartFile multiFile){
    	ModelAndView mav = new ModelAndView("personal/planner/importoroff");
    	Map<String,Object> result = new HashMap<String,Object>();
        try {
            result = plannerService.importExcelFileOff(multiFile);
            result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
        }
        return mav;
    }

    @RequestMapping(value = "/achivement")
    public ModelAndView achivement(){
        ModelAndView mav = new ModelAndView("personal/planner/achivement");
        mav.addObject("area", JSON.toJSON(departmentService.findChildren(Const.ROOT_DEPT_ID)));
        return mav;
    }

    @RequestMapping(value = "/achivement/getDepartment", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getDepartment(Integer departmentId){

        return new AjaxJson(true, departmentService.findChildren(departmentId));
    }

    /**
     * 理财师业绩数据
     * @param area
     * @param subCompany
     * @param team
     * @param startDate
     * @return
     */
    @RequestMapping(value = "/achivement/find", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson achivementData(Integer area, Integer subCompany, Integer team, String startDate){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //判断是否为当前月份 如果是则从日表取数据，否则从月表取
        String nowMonth = DateUtil.formatDate(new Date(), "yyyy-MM");
        if(nowMonth.equals(startDate)){
            result = this.findDailyAchivement(subCompany, team, result);
        }else{
            result = this.findMonthlyAchivement(subCompany, team, startDate, result);
        }
        return new AjaxJson(true, result);
    }

    /**
     * 理财师业绩日表数据,如果只选择区总则按分公司统计，选择了分公司按团队统计，选择了团队按理财师统计
     * @param subCompany
     * @param team
     * @param result
     * @return
     */
    private List<Map<String, Object>> findDailyAchivement(Integer subCompany, Integer team, List<Map<String, Object>> result){
        Integer totalAmount = 0;
        if(team != 0){
            List<Planner> planners = plannerService.findPlannerByDepartment(team);
            List<Integer> plannerIds = new ArrayList<Integer>();
            for(Planner planner : planners){
                plannerIds.add(planner.getId());
            }
            if(plannerIds.size() > 0){
                result = this.buildDailyResult(totalAmount, plannerIds, result);
            }
        }else{
            List<Department> departments = departmentService.findChildren(subCompany);
            List<Integer> deptIds = new ArrayList<Integer>();
            for(Department department : departments){
                deptIds.add(department.getDepartmentId());
            }
            if(deptIds.size() > 0) {
                List<Integer> plannerIds = plannerService.findPlannerByDepartment(deptIds);
                result = this.buildDailyResult(totalAmount, plannerIds, result);
            }
        }

        return result;
    }

    private List<Map<String, Object>> buildDailyResult(Integer totalAmount, List<Integer> plannerIds, List<Map<String, Object>> result){
        List<PlannerAchivementsDaily> achivementsDailies = plannerAchivementsDailyService.findAchivementsDaily(plannerIds);

        for(PlannerAchivementsDaily achivementsDaily : achivementsDailies){
            totalAmount += achivementsDaily.getContractAmount();
        }
        for(PlannerAchivementsDaily achivementsDaily : achivementsDailies){
            Map<String, Object> map = new HashMap<String, Object>();
            DecimalFormat format = new DecimalFormat("#0.00");
            String percent = format.format(achivementsDaily.getContractAmount().doubleValue() / totalAmount.doubleValue() * 100);
            map.put("value", achivementsDaily.getContractAmount() / 10000);
            map.put("percent", percent);
            map.put("date", achivementsDaily.getTransferDate());
            User user = userService.getUser(plannerService.getPlanner(achivementsDaily.getPlannerUid()).getUid());
            map.put("name", user.getRealname());
            result.add(map);
        }

        return result;
    }

    /**
     * 理财师业绩月表
     * @param subCompany
     * @param team
     * @param startDate
     * @param result
     * @return
     */
    private List<Map<String, Object>> findMonthlyAchivement(Integer subCompany, Integer team, String startDate, List<Map<String, Object>> result){
        //找到该分公司下的所有理财师
        Integer totalAmount = 0;
        if(team != 0){
            List<Planner> planners = plannerService.findPlannerByDepartment(team);
            List<Integer> plannerIds = new ArrayList<Integer>();
            for(Planner planner : planners){
                plannerIds.add(planner.getId());
            }
            if(plannerIds.size() > 0){
                result = this.buildMonthlyResult(totalAmount, plannerIds, startDate, result);
            }
        }else{
            List<Department> departments = departmentService.findChildren(subCompany);
            List<Integer> deptIds = new ArrayList<Integer>();
            for(Department department : departments){
                deptIds.add(department.getDepartmentId());
            }
            if(deptIds.size() > 0) {
                List<Integer> plannerIds = plannerService.findPlannerByDepartment(deptIds);
                result = this.buildMonthlyResult(totalAmount, plannerIds, startDate, result);
            }
        }

        return result;
    }

    private List<Map<String, Object>> buildMonthlyResult(Integer totalAmount, List<Integer> plannerIds, String startDate, List<Map<String, Object>> result){
        List<PlannerAchivementsMonthly> achivementsMonthlies = plannerAchivementsMonthlyService.findAchivementsMonthly(plannerIds, startDate);
        for(PlannerAchivementsMonthly achivementsMonthly : achivementsMonthlies){
            totalAmount += achivementsMonthly.getAnnualised();
        }
        for(PlannerAchivementsMonthly achivementsMonthly : achivementsMonthlies){
            Map<String, Object> map = new HashMap<String, Object>();
            DecimalFormat format = new DecimalFormat("#0.00");
            String percent = format.format(achivementsMonthly.getAnnualised().doubleValue() / totalAmount.doubleValue() * 100);
            map.put("value", achivementsMonthly.getAnnualised() / 10000);
            map.put("percent", percent);
            map.put("date", achivementsMonthly.getTransferDate());
            User user = userService.getUser(plannerService.getPlanner(achivementsMonthly.getPlannerUid()).getUid());
            map.put("name", user.getRealname());
            result.add(map);
        }

        return result;
    }


}
