package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.system.commons.page.PageHelper;
import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.commons.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.mybatis.model.Product;
import com.fhzc.app.system.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 产品管理
 * Created by lihongde on 2016/7/7 15:22
 */
@Controller
@RequestMapping(value = "business/product")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

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
        logger.info("##@##@# : " +  JSON.toJSONString(pageableResult.getItems().get(0)));
        return mav;
    }

    /**
     * 产品发布页面
     * @return
     */
    @RequestMapping(value = "/pub")
    public String pubProduct(){
        return "business/product/pub";
    }

    /**
     * 产品导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorProduct(){
        ModelAndView mav = new ModelAndView("business/product/importor");
        return mav;
    }

    /**
     * 产品发布
     * @param product 产品信息
     * @param coverFile 产品封面
     * @param proveFile 备案证明
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addProduct(Product product, MultipartFile coverFile, MultipartFile proveFile){
        ModelAndView mav = new ModelAndView("business/product/list");
        String coverName = FileUtil.generatePictureName(coverFile);
        String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
        FileUtil.transferFile(coverPath, coverName, coverFile);
        product.setCover(coverPath + coverName);

        String proveUrlName = FileUtil.generatePictureName(proveFile);
        String proveUrlPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
        FileUtil.transferFile(proveUrlPath, proveUrlName, proveFile);
        product.setProveUrl(proveUrlPath + proveUrlName);

        product.setInvestThreshold(product.getInvestThreshold().multiply(new BigDecimal(10000)));
        product.setCtime(new Date());

        productService.addProduct(product);

        PageableResult<Product> pageableResult = productService.findPageProducts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("products", pageableResult.getItems());
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
