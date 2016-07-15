package com.fhzc.app.system.controller.business;

import com.fhzc.app.system.commons.page.PageHelper;
import com.fhzc.app.system.commons.page.PageableResult;
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
        return mav;
    }

    /**
     * 产品发布
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addProduct(Product product){
        ModelAndView mav = new ModelAndView("business/product/list");
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
