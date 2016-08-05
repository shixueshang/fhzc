package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private AssetsService assetsService;

    @Resource
    private PlannerCustomerService plannerCustomerService;

    /**
     * 产品列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/api/product",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productList(Integer userId){
        PageableResult<Product> productList =  productService.getProductList(0,0, false);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    /**
     * 精选基金列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/api/product/select",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productListSelect(Integer userId){
        PageableResult<Product> productList =  productService.getProductList(0,0,true);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    /**
     * 产品详情
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/product/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult productDetail(Integer productId) throws Exception {
        Product product = productService.getProduct(productId);
        Map result = ObjUtils.objectToMap(product);
        User user = super.getCurrentUser();
        ProductReservation reservation = productReservationService.getUserProductReservation(user.getUid(),productId);
        if(reservation != null) {
            result.put("reservationId", reservation.getId());
            result.put("reservationResult", reservation.getResult());
        }

        Focus focus = focusService.getFocusByCond(user.getUid(),productId,APIConstants.FocusType.Product);
        if(focus != null){
            result.put("focusStatus",focus.getStatus());
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }

    /**
     * 我的财富
     * @param customer_id
     * @return
     */
    @RequestMapping(value = "/api/personal/assets",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult personalAssets(Integer customer_id) {
        User user = getCurrentUser();

        //校验是否是登陆理财师的客户请求
        if(user.getLoginRole() == Const.USER_ROLE.PLANNER) {
            List<PlannerCustomer> plannerCustomers= plannerCustomerService.getPlannerCustomerList(user.getUid());
            if(plannerCustomers == null){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST);
            }
            boolean isCustomer = false;
            for (PlannerCustomer pl: plannerCustomers){
                if(pl.getCustomerId() == customer_id){
                    isCustomer = true;
                    break;
                }
            }
            if(!isCustomer){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST);
            }
        }

        List<AssetsHistory> assetsHistoryList = assetsService.getHistory(customer_id);
        List<Map> result = new ArrayList<>();
        if(assetsHistoryList == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
        }
        for (AssetsHistory asset:assetsHistoryList){
            Map map = new HashMap();
            Product product = productService.getProduct(asset.getProductId());
            if(product == null) continue;

            map.put("productId",asset.getProductId());
            map.put("serial",asset.getSerial());
            map.put("assetType",asset.getType());
            map.put("amount",asset.getAmount());
            map.put("amountUsd",asset.getAmountUsd());
            map.put("amountRMB",asset.getAmountRmb());
            map.put("period",asset.getPeriod());

            map.put("name",product.getName());
            map.put("found_day",product.getFoundDay());
            map.put("value_day",product.getValueDay());
            map.put("redeem_day",product.getRedeemDay());
            map.put("dividend_day",product.getDividendDay());
            map.put("dead_date",asset.getDeadDate());//截止日期

            result.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
