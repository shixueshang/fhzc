/**
 * 
 */
package com.fhzc.app.system.controller.business;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.PlannerAchivementsMonthlyService;

/**
 * @author xiaoqiang 2016-07-22
 *
 */

@Controller
@RequestMapping(value = "business/plannerachivementsmonthly")
public class PlannerAchivementsMonthlyController   extends BaseController  {


    @Resource
    private PlannerAchivementsMonthlyService plannerAchivementsMonthlyService;

    /**
     * 业绩日报导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorProduct(){
        ModelAndView mav = new ModelAndView("business/plannerachivementsmonthly/importor");
        PageableResult<PlannerAchivementsMonthly> pageableResult = plannerAchivementsMonthlyService.findPagePlannerAchivementsDaily(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("plannerAchivementsMonthlys", pageableResult.getItems());

        return mav;
    }
    
//    /**
//     * excel导入
//     * @param multiFile
//     * @return
//     */
//    @RequestMapping(value = "/import", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> importExcel(MultipartFile multiFile){
//        Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            result = plannerAchivementsMonthlyService.importDailyExcelFile(multiFile);
//            
//            result.put("success", true);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            result.put("success", false);
//            e.printStackTrace();
//        }
//        return result;
//    }
//    
    
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView importExcel(MultipartFile multiFile){
    	Map<String, Object> result = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("business/plannerachivementsmonthly/importor");
        try {
            result = plannerAchivementsMonthlyService.importDailyExcelFile(multiFile);
            result.put("success", true);
            	
            PageableResult<PlannerAchivementsMonthly> pageableResult = plannerAchivementsMonthlyService.findPagePlannerAchivementsDaily(page, size);
            mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
            mav.addObject("plannerAchivementsMonthlys", pageableResult.getItems());
            
            mav.addAllObjects(result);

            return mav;

        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            mav.addAllObjects(result);
           // e.printStackTrace();
            return mav;
        }

    }
}
