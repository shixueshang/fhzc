package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;


import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DepartmentService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.ProductService;
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
 * Created by lihongde on 2016/7/7 15:22
 */
@Controller
@RequestMapping(value = "business/product")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DepartmentService departmentService;

    /**
     * 产品列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProduct(){
        ModelAndView mav = new ModelAndView("business/product/list");
        PageableResult<Product> pageableResult = productService.findPageProducts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("products", pageableResult.getItems());
        mav.addObject("productTypes", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE));
        mav.addObject("productStatus", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS));
        mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
        mav.addObject("yesNo", dictionaryService.findDicByType(Const.DIC_CAT.YES_NO));
        return mav;
    }

    /**
     * 产品发布页面
     * @return
     */
    @RequestMapping(value = "/pub")
    public ModelAndView pubProduct(){
        ModelAndView mav = new ModelAndView("business/product/add");
        mav.addObject("productTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE)));
        mav.addObject("productStatus", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS)));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("productIssueType", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_ISSUE_TYPE)));
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("riskLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL)));
        return mav;
    }


    /**
     * 产品发布
     * @param product 产品信息
     * @param coverFile 产品封面
     * @param proveFile 备案证明
     * @param noticeFile 产品成立公告
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addOrUpdateProduct(Product product, MultipartFile coverFile, MultipartFile proveFile, MultipartFile noticeFile){
        ModelAndView mav = new ModelAndView("business/product/list");

        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            product.setCover(coverPath + coverName);
        }

        if(!proveFile.isEmpty()){
            String proveUrlName = FileUtil.generatePictureName(proveFile);
            String proveUrlPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(proveUrlPath, proveUrlName, proveFile);
            product.setProveUrl(proveUrlPath + proveUrlName);
        }

        if(product.getInvestThreshold() != null && product.getInvestThreshold().compareTo(new BigDecimal(0)) == 1){
            product.setInvestThreshold(product.getInvestThreshold().multiply(new BigDecimal(10000)));
        }
        product.setCtime(new Date());

        productService.addOrUpdateProduct(product);

        PageableResult<Product> pageableResult = productService.findPageProducts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("products", pageableResult.getItems());
        return mav;
    }

    /**
     * 产品编辑
     * @param pid
     * @return
     */
    @RequestMapping(value="/detail/{pid}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "pid") Integer pid){
        ModelAndView mav = new ModelAndView("business/product/add");
        mav.addObject("product", productService.getProduct(pid));
        mav.addObject("productTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE)));
        mav.addObject("productStatus", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS)));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(1)));
        mav.addObject("productIssueType", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_ISSUE_TYPE)));
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("riskLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL)));
        return mav;
    }

    @RequestMapping(value = "/isNameExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isNameExists(String name){
        boolean flag = productService.isNameExists(name);
        return !flag;
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
            result = productService.importExcelFile(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }

}
