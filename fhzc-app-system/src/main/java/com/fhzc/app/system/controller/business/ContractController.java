package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;


import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Double_J on 2016/7/7 15:22
 */
@Controller
@RequestMapping(value = "business/contract")
public class ContractController extends BaseController {

    @Resource(name="contractService")
    private ContractService contractService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DepartmentService departmentService;

    /**
     * 日表合同列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listContract(){
        ModelAndView mav = new ModelAndView("business/contract/list");
        PageableResult<Contract> pageableResult = contractService.findPageContracts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("contracts", pageableResult.getItems());
        mav.addObject("contractTypes", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE));
        mav.addObject("contractStatus", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS));
        mav.addObject("yesNo", dictionaryService.findDicByType(Const.DIC_CAT.YES_NO));
        return mav;
    }

    /**
     * 日表合同编辑
     * @param pid
     * @return
     */
    @RequestMapping(value="/detail/{pid}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "pid") Integer pid){
        ModelAndView mav = new ModelAndView("/business/contract/add");
        return mav;
    }
    
    /**
     * 日表合同判断
     * @param pid
     * @return
     */
    @RequestMapping(value = "/isNameExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isNameExists(String name){
        boolean flag = contractService.isNameExists(name);
        return !flag;
    }

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
//    public Map<String, Object> importExcel(MultipartFile multiFile){
//        Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            result = contractService.importExcelFile(multiFile);
//            result.put("success", true);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            result.put("success", false);
//            e.printStackTrace();
//        }
//        return result;
//    }
    public ModelAndView importExcel(MultipartFile multiFile){
    	ModelAndView mav = new ModelAndView("business/contract/importor");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = contractService.importExcelFile(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        mav.addObject("result", result);
        return mav;
    }
}
