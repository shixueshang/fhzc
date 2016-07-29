package com.fhzc.app.system.controller.personal;

import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;


import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.system.service.PlannerService;

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

    @Resource()
    private PlannerService plannerService;

    /**
     * 理财师列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProduct(){
        ModelAndView mav = new ModelAndView("personal/planner/import");
        PageableResult<Planner> pageableResult = plannerService.findPagePlanners(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("planners", pageableResult.getItems());
        return mav;
    }


    /**
     * 理财师导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorProduct(){
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
