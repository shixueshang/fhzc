package com.fhzc.app.api.controller;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.mybatis.model.Product;
import com.fhzc.app.system.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/7/19.
 */
@Controller
public class ProductApiController extends BaseController {

    @Resource
    private ProductService productService;

    @RequestMapping(value = "/api/product",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productList(Integer userId){
        PageableResult<Product> productList =  productService.getProductList(0,0);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    @RequestMapping(value = "/api/product/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult productDetail(Integer productId){
        Product product = productService.getProduct(productId);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,product);
    }
}
