package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.FocusService;
import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by freeman on 16/7/19.
 */
@Controller
public class ProductApiController extends BaseController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductReservationService productReservationService;

    @Resource
    private FocusService focusService;

    @RequestMapping(value = "/api/product",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productList(Integer userId){
        PageableResult<Product> productList =  productService.getProductList(0,0, false);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    @RequestMapping(value = "/api/product/select",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productListSelect(Integer userId){
        PageableResult<Product> productList =  productService.getProductList(0,0,true);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    @RequestMapping(value = "/api/product/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult productDetail(Integer productId) throws Exception {
        Product product = productService.getProduct(productId);
        Map result = ObjUtils.objectToMap(product);
        User user = super.getCurrentUser();
        ProductReservation reservation = productReservationService.getUserProductReservation(user.getUid(),productId);
        if(reservation != null) {
            result.put("reservationResult", reservation.getResult());
        }

        Focus focus = focusService.getFocusByCond(user.getUid(),productId,APIConstants.FocusType.Product);
        if(focus != null){
            result.put("focusStauts",focus.getStatus());
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
