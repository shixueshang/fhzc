package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by jiajitao on 2016/7/20.
 */
@Controller
public class ProductOrderApiController extends BaseController{

    @Resource
    private ProductReservationService productReservationService;

    @Resource
    private ProductService productService;

    @RequestMapping(value = "/api/product/order",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult productOrder(ProductReservation productReservation){
        productReservation.setCtime(new Date());
        productReservation.setResult(APIConstants.OrderResult.Success);
        productReservationService.addOrUpdateProductReservation(productReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    @RequestMapping(value = "/api/product/order/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult productOrderCancel(Integer id){
        //todo 先写个id
        ProductReservation productReservation=productReservationService.getProductReservation(id);
        productReservation.setResult(APIConstants.OrderResult.Cancel);
        productReservationService.addOrUpdateProductReservation(productReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    @RequestMapping(value = "/api/personal/product/order",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalProductOrder(Integer customer_id){
        List<ProductReservation> reservationList = productReservationService.getUserProductList(customer_id);
        List<Product> result = new ArrayList<Product>();
        for(ProductReservation reserv : reservationList) {
            Product product = productService.getProduct(reserv.getProductId());
            result.add(product);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
