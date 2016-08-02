package com.fhzc.app.system.controller.business;

import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DepartmentService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "business/contract")
public class ContractController extends BaseController {

    @Resource(name="contractService")
    private ContractService contractService;

    /**
     * 日表导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorProduct(){
        ModelAndView mav = new ModelAndView("business/contract/importor");
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
            result = contractService.importExcelFile(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }
//    public ModelAndView importExcel(MultipartFile multiFile){
//    	ModelAndView mav = new ModelAndView("business/contract/importor");
//        Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            result = contractService.importExcelFile(multiFile);
//            result.put("success", true);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            result.put("success", false);
//            e.printStackTrace();
//        }
//        mav.addObject("result", result);
//        return mav;
//    }
}
