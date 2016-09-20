package com.fhzc.app.system.controller.personal;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;


import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
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
    @SystemControllerLog(description = "查看理财师列表")
    public ModelAndView listPlanners(){
        ModelAndView mav = new ModelAndView("personal/planner/list");
        Admin admin = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());
        PageableResult<Planner> pageableResult = plannerService.findPagePlanners(departments, page, size);
//        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
//        mav.addObject("planners", pageableResult.getItems());
        mav.addObject("page", 0);
        mav.addObject("planners", null);

        //查询用户表的理财师信息
        List<Planner> planners = pageableResult.getItems();
        List<User> users = new ArrayList<User>();
        for(Planner planner : planners){
            User user = userService.getUser(planner.getUid());
            users.add(user);
        }

//        mav.addObject("users", users);
//        mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
//        mav.addObject("areas", areasService.getAllAreas());
        mav.addObject("users", null);
        mav.addObject("departments", null);
        mav.addObject("areas", null);
        mav.addObject("url", "personal/planner");
        return mav;
    }

    /**
     * 根据工号查询客户
     * @param workNum
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看理财师列表")
    public ModelAndView findSingleCustomers(String realName){
        ModelAndView mav = new ModelAndView("personal/planner/list");
        Admin admin = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());
        List<Integer> plannerIds = plannerService.findPlannerByDepartment(departments);
        List<Planner> planners = new LinkedList<Planner>();
        for (Integer id : plannerIds) {
			planners.add(plannerService.getPlanner(id));
    		}
        List<Planner> temPlanners = new ArrayList<Planner>();
        List<User> users = new ArrayList<User>();
	    if(planners.isEmpty()){
	    	 return mav;
	     }else{
	    	 if(realName.isEmpty()){
	    		 return mav;
	    	 }else{
	    		 for (Planner planner : planners) {
	    			 //if(planner.getWorkNum().equals(workNum.trim())){
	    			 User user = userService.getUser(planner.getUid());
	    			 if(user.getRealname().contains(realName.trim())){
	    				 temPlanners.add(planner);
	    				 users.add(user);
	    			 }
	    			 //}
	    		 }
	    		 mav.addObject("planners", temPlanners);
	    		 mav.addObject("users", users);
	    		 mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
	    		 mav.addObject("areas", areasService.getAllAreas());
	    		 mav.addObject("url", "personal/planner");
	    		 return mav;
	    	 }
	     }
    }
    
    /**
     * 在职理财师导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importPlanner(){
        ModelAndView mav = new ModelAndView("personal/planner/importor");
        mav.addObject("url", "personal/planner/importor");
        return mav;
    }

    /**
     * excel导入--在职
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "在职理财师导入")
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
        mav.addObject("url","personal/planner");
        return mav;
    }

    /**
     * 离职理财师导入页面
     * @return
     */
    @RequestMapping(value = "/importoroff", method = RequestMethod.GET)
    public ModelAndView importOffPlanner(){
        ModelAndView mav = new ModelAndView("personal/planner/importoroff");
        mav.addObject("url", "personal/planner/importoroff");
        return mav;
    }

    /**
     * excel导入--离职
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importoff", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "离职理财师导入")
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
        mav.addObject("url","personal/planner");
        return mav;
    }

    @RequestMapping(value = "/achivement")
    public ModelAndView achivement(){
        ModelAndView mav = new ModelAndView("personal/planner/achivement");
        Admin admin = super.getCurrentUser();
        Department department = departmentService.getDepartment(admin.getOrgan());
        List<Department> areas = new ArrayList<Department>();
        if(department.getLevel() == 1){
            areas = departmentService.findChildren(Const.ROOT_DEPT_ID);
        }else if(department.getLevel() == 2){
            areas.add(departmentService.getDepartment(department.getDepartmentId()));
            List<Department> departs = departmentService.findChildren(department.getDepartmentId());
            if (departs != null && departs.size() > 0){
                mav.addObject("companies", JSON.toJSON(departs));
            }
        }else{
            areas.add(departmentService.getDepartment(department.getParentDeptId()));
            mav.addObject("company", JSON.toJSON(departmentService.getDepartment(admin.getOrgan())));
        }
        mav.addObject("area", JSON.toJSON(areas));
        mav.addObject("url","personal/planner");
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
    @SystemControllerLog(description = "查看理财师业绩")
    public AjaxJson achivementData(Integer area, Integer subCompany, Integer team, String startDate){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //判断是否为当前月份 如果是则从日表取数据，否则从月表取
        String nowMonth = DateUtil.formatDate(new Date(), "yyyy-MM");
        if(nowMonth.equals(startDate)){
            result = this.findDailyAchivement(area, subCompany, team, result);
        }else{
            result = this.findMonthlyAchivement(area, subCompany, team, startDate, result);
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
    private List<Map<String, Object>> findDailyAchivement(Integer area, Integer subCompany, Integer team, List<Map<String, Object>> result){
        Integer totalAmount = 0;

        if(area == 0){
            List<Department> departments = departmentService.findChildren(Const.ROOT_DEPT_ID);
            List<Integer> areas = new ArrayList<Integer>();
            for(Department department : departments){
                areas.add(department.getDepartmentId());
            }
            if(areas.size() > 0){
                List<PlannerAchivementsDaily> achivementsDailies = plannerAchivementsDailyService.findAchiveDailyByArea(areas);
                result = this.buildDailyResult(totalAmount, achivementsDailies, result, "area");
            }
        }

        if(subCompany == 0){  //只选择区总，按照分公司统计
            //获得区总下的分公司
            List<Department> departments = departmentService.findChildren(area);
            List<Integer> companys = new ArrayList<Integer>();
            for(Department department : departments){
                companys.add(department.getDepartmentId());
            }
            if(companys.size() > 0){
                List<PlannerAchivementsDaily> achivementsDailies = plannerAchivementsDailyService.findAchiveDailyBySub(companys);
                result = this.buildDailyResult(totalAmount, achivementsDailies, result, "company");
            }
        }
        if(team == 0){  //选择了分公司，按团队统计
            List<Department> departments = departmentService.findChildren(subCompany);
            List<Integer> teams = new ArrayList<Integer>();
            for(Department department : departments){
                teams.add(department.getDepartmentId());
            }
            if(teams.size() > 0) {
                List<PlannerAchivementsDaily> achivementsDailies = plannerAchivementsDailyService.findAchiveDailyByTeam(teams);
                result = this.buildDailyResult(totalAmount, achivementsDailies, result, "team");
            }
        }else{ //选择了团队，按理财师统计
            List<Planner> planners = plannerService.findPlannerByDepartment(team);
            List<Integer> plannerIds = new ArrayList<Integer>();
            for(Planner planner : planners){
                plannerIds.add(planner.getId());
            }
            if(plannerIds.size() > 0){
                List<PlannerAchivementsDaily> achivementsDailies = plannerAchivementsDailyService.findAchiveDailyByPlanner(plannerIds);
                result = this.buildDailyResult(totalAmount, achivementsDailies, result, "planner");
            }
        }

        return result;
    }

    private List<Map<String, Object>> buildDailyResult(Integer totalAmount, List<PlannerAchivementsDaily> achivementsDailies, List<Map<String, Object>> result, String type){
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
            if(type.equals("area")){
                Department department = departmentService.getDepartment(achivementsDaily.getArea());
                map.put("name", department.getTitle());
            }
            if(type.equals("company")){
                Department department = departmentService.getDepartment(achivementsDaily.getCompany());
                map.put("name", department.getTitle());
            }
            if(type.equals("team")){
                Department department = departmentService.getDepartment(achivementsDaily.getTeam());
                map.put("name", department.getTitle());
            }
            if(type.equals("planner")){
                User user = userService.getUser(plannerService.getPlanner(achivementsDaily.getPlannerUid()).getUid());
                map.put("name", user.getRealname());
            }

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
    private List<Map<String, Object>> findMonthlyAchivement(Integer area, Integer subCompany, Integer team, String startDate, List<Map<String, Object>> result){
        Integer totalAmount = 0;

        if(area == 0){
            List<Department> departments = departmentService.findChildren(Const.ROOT_DEPT_ID);
            List<Integer> areas = new ArrayList<Integer>();
            for(Department department : departments){
                areas.add(department.getDepartmentId());
            }
            if(areas.size() > 0){
                List<PlannerAchivementsMonthly> achivementsMonthly = plannerAchivementsMonthlyService.findAchiveMonthlyByArea(areas, startDate);
                result = this.buildMonthlyResult(totalAmount, achivementsMonthly, startDate, result, "area");
            }
        }
        if(subCompany == 0){
            List<Department> departments = departmentService.findChildren(area);
            List<Integer> companys = new ArrayList<Integer>();
            for(Department department : departments){
                companys.add(department.getDepartmentId());
            }
            if(companys.size() > 0){
                List<PlannerAchivementsMonthly> achivementsMonthly = plannerAchivementsMonthlyService.findAchiveMonthlyByCompany(companys, startDate);
                result = this.buildMonthlyResult(totalAmount, achivementsMonthly, startDate, result, "company");
            }
        }
        if(team == 0){
            List<Department> departments = departmentService.findAllChildren(subCompany);
            List<Integer> teams = new ArrayList<Integer>();
            for(Department dept : departments){
                teams.add(dept.getDepartmentId());
            }
            if(teams.size() > 0){
                List<PlannerAchivementsMonthly> achivementsMonthly = plannerAchivementsMonthlyService.findAchiveMonthlyByTeam(teams, startDate);
                result = this.buildMonthlyResult(totalAmount, achivementsMonthly, startDate, result, "team");
            }
        }else{
            List<Integer> departments = departmentService.findAllChildrenIds(team);
            List<Integer> puIds = new ArrayList<Integer>();
            List<Integer> plannerIds = plannerService.findPlannerByDepartment(departments);
            for(Integer plannerId : plannerIds){
                puIds.add(plannerService.getPlanner(plannerId).getUid());
            }
            if(puIds.size() > 0) {
                List<PlannerAchivementsMonthly> achivementsMonthly = plannerAchivementsMonthlyService.findAchiveMonthlyByPlanner(puIds, startDate);
                result = this.buildMonthlyResult(totalAmount, achivementsMonthly, startDate, result, "planner");
            }
        }

        return result;
    }

    private List<Map<String, Object>> buildMonthlyResult(Integer totalAmount, List<PlannerAchivementsMonthly> achivementsMonthlies, String startDate, List<Map<String, Object>> result, String type){
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
            if(type.equals("area")){
                Department department = departmentService.getDepartment(achivementsMonthly.getArea());
                map.put("name", department.getTitle());
            }
            if(type.equals("company")){
                Department department = departmentService.getDepartment(achivementsMonthly.getCompany());
                map.put("name", department.getTitle());
            }
            if(type.equals("team")){
                Department department = departmentService.getDepartment(achivementsMonthly.getTeam());
                map.put("name", department.getTitle());
            }
            if(type.equals("planner")){
                User user = userService.getUser(plannerService.getPlannerByUid(achivementsMonthly.getPlannerUid()).getUid());
                map.put("name", user.getRealname());
            }
            result.add(map);
        }

        return result;
    }

    /**
     * 获得当前用户部门的理财师
     * @return
     */
    @RequestMapping(value = "/getPlanner", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getPlanners(){
        Admin admin = super.getCurrentUser();
        List<Department> departments = departmentService.findAllChildren(admin.getOrgan());
        List<Integer> deptIds = new ArrayList<Integer>();
        for(Department department : departments){
            deptIds.add(department.getDepartmentId());
        }
        List<Integer> plannersIds = plannerService.findPlannerByDepartment(deptIds);
        List<Planner> planners = new ArrayList<Planner>();
        for(Integer plannerId : plannersIds){
            Planner planner = plannerService.getPlanner(plannerId);
            User user = userService.getUser(planner.getUid());
            planner.setPlannerName(user.getRealname());
            planners.add(planner);
        }
        return new AjaxJson(true, planners);
    }

}
