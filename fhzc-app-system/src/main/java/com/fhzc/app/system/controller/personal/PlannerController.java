package com.fhzc.app.system.controller.personal;

import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;


import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.system.service.AreasService;
import com.fhzc.app.system.service.DepartmentService;
import com.fhzc.app.system.service.PlannerService;

import com.fhzc.app.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
        mav.addObject("departments", departmentService.findDeptByParent(1));
        mav.addObject("areas", areasService.getAllAreas());
        mav.addObject("url", "personal/planner");
        return mav;
    }


    /**
     * 理财师导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importPlanner(){
        ModelAndView mav = new ModelAndView("personal/planner/importor");
        return mav;
    }

    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
//    public Map<String, Object> importExcel(MultipartFile multiFile){
//        Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            result = plannerService.importExcelFile(multiFile);
//            result.put("success", true);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            result.put("success", false);
//            e.printStackTrace();
//        }
//        return result;
//    }
    public ModelAndView importExcel(MultipartFile multiFile){
    	ModelAndView mav = new ModelAndView("personal/planner/importor");
//    	Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            result = plannerService.importExcelFile(multiFile);
//            result.put("success", true);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            result.put("success", false);
//            e.printStackTrace();
//        }
//        mav.addObject("result", result);
    	
    	Map<String,Map<String,Object>> result = new HashMap<String,Map<String,Object>>();
	      try {
	      result = plannerService.importExcel(multiFile);
//	      result.put("success", true);
	      } catch (Exception e) {
	      logger.error("导入失败");
//	      result.put("success", false);
	      e.printStackTrace();
	      }
	      mav.addObject("result", result);
        return mav;
    }

}