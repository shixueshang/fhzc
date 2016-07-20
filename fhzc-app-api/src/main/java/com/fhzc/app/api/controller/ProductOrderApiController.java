package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by jiajitao on 2016/7/20.
 */
@Controller
public class ProductOrderApiController {


    private ProductReservationService productReservationService;

    @RequestMapping(value = "/api/product/order",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult productOrder(Integer productId,String name,Integer amount){
        ProductReservation productReservation =new ProductReservation();
        productReservation.setProductId(productId);
        productReservationService.addOrUpdateProductReservation(productReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

}
