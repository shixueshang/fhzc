package com.fhzc.app.system.controller.business;



import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.PayMentService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Double_J on 2016/7/22
 */
@Controller
@RequestMapping(value = "business/payment")
public class PayMentController extends BaseController {

    @Resource
    private PayMentService payMentService;

    /**
     * 普通产品导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorPayMent(){
        ModelAndView mav = new ModelAndView("business/payment/importor");
        return mav;
    }
    
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(MultipartFile multiFile){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = payMentService.importExcelFile(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 鑫丰母基金导入页面
     * @return
     */
    @RequestMapping(value = "/importorspecial", method = RequestMethod.GET)
    public ModelAndView importorSpecialPayMent(){
        ModelAndView mav = new ModelAndView("business/payment/importorspecial");
        return mav;
    }
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importspecial", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcelSpecial(MultipartFile multiFile){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = payMentService.importExcelFileSpecial(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }

}
