package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by jiajitao on 2016/7/20.
 */
@Controller
public class ProductOrderApiController extends BaseController{

    @Resource
    private ProductReservationService productReservationService;

    @RequestMapping(value = "/api/product/order",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult productOrder(Integer productId,Integer customerId, Integer plannerId,Integer amount){
        ProductReservation productReservation =new ProductReservation();
        productReservation.setProductId(productId);
        productReservation.setCustomerId(customerId);
        productReservation.setPlannerId(plannerId);
        productReservation.setAmount(amount);
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

}
