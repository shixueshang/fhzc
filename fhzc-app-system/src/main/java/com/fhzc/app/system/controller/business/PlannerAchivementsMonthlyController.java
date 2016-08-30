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
        return mav;
    }
    
    
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
            mav.addAllObjects(result);
            return mav;

        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
            return mav;
        }

    }
}
