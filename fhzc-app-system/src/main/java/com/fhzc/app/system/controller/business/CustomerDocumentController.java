package com.fhzc.app.system.controller.business;



import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.CustomerDocumentService;

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
@RequestMapping(value = "business/customerdocument")
public class CustomerDocumentController extends BaseController {

    @Resource
    private CustomerDocumentService customerDocumentService;


    /**
     * 个人客户导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorCustomerDocument(){
        ModelAndView mav = new ModelAndView("business/customerdocument/importor");
        return mav;
    }
    
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
//    public Map<String, Map<String, Object>> importExcel(MultipartFile multiFile){
//       	Map<String,Map<String,Object>> result = new HashMap<String,Map<String,Object>>();
//       	Map<String,Object> map = new HashMap<String, Object>();
//        try {
//            result = customerDocumentService.importExcel(multiFile);
//            map.put("flag", true);
//            result.put("success", map);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            map.put("flag", false);
//            result.put("success", map);
//            e.printStackTrace();
//        }
//        return result;
//    }
    public Map<String, Object> importExcelSpecial(MultipartFile multiFile){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = customerDocumentService.importExcelFilePersonal(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }

}
