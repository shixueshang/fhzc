package com.fhzc.app.api.controller;

import com.fhzc.app.api.exception.BadRequestException;
import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.util.Const;
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

    /**
     * 预约产品
     * @param productReservation
     * @return
     */
    @RequestMapping(value = "/api/product/order",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult productOrder(ProductReservation productReservation){

        Product product = productService.getProduct(productReservation.getProductId());
        if(product.getStatus() != Const.PRODUCT_STATUS.PREHEAT && product.getStatus() != Const.PRODUCT_STATUS.COLLECTING){
            return  new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "产品不在募集期");
        }

        productReservation.setApplyTime(new Date());
        productReservation.setCtime(new Date());
        productReservation.setResult(APIConstants.OrderResult.Success);
        productReservationService.addOrUpdateProductReservation(productReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 取消预约产品
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/product/order/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult productOrderCancel(Integer id){
        //todo 先写个id
        ProductReservation productReservation=productReservationService.getProductReservation(id);
        productReservation.setResult(APIConstants.OrderResult.Cancel);
        productReservationService.addOrUpdateProductReservation(productReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 客户预约的产品
     * @param customer_id
     * @return
     */
    @RequestMapping(value = "/api/personal/product/order",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalProductOrder(Integer customer_id) throws Exception {
        List<ProductReservation> reservationList = productReservationService.getUserProductList(customer_id);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for(ProductReservation reserv : reservationList) {
            Product product = productService.getProduct(reserv.getProductId());
            if(product != null){
                Map map = ObjUtils.objectToMap(product);
                map.put("investAmount",reserv.getAmount());
                result.add(map);
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
