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

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private CustomerService customerService;

    @Resource
    private PlannerService plannerService;

    /**
     * 产品列表
     * @return
     */
    @RequestMapping(value = "/api/product",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productList(){
        PageableResult<Product> productList =  productService.getProductList(0,0, false);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    /**
     * 精选基金列表
     * @return
     */
    @RequestMapping(value = "/api/product/select",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productListSelect(){
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
        Customer customer = customerService.getCustomerByUid(user.getUid());
        ProductReservation reservation = productReservationService.getUserProductReservation(customer.getCustomerId(),productId);
        if(reservation != null) {
            result.put("reservationId", reservation.getId());
            result.put("reservationResult", reservation.getResult());
        }

        Focus focus = focusService.getFocusByCond(user.getUid(),productId,APIConstants.FocusType.Product);
        if(focus != null){
            result.put("focusStatus",focus.getStatus());
        }

        result.put("riskLevel", super.getLevelName(product.getRisk()));
        result.put("customerLevel", super.getLevelName(product.getLevel()));
        result.put("issueType", super.getProductIssueType(product.getIssueType()));
        result.put("productStatus", super.getProductStatusName(Integer.valueOf(product.getStatus())));

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
        if(user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            Planner planner = plannerService.getPlannerByUid(user.getUid());
            List<PlannerCustomer> plannerCustomers= plannerCustomerService.getPlannerCustomerList(planner.getId());
            if(plannerCustomers == null){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"登陆理财师与请求客户无对应关系");
            }
            boolean isCustomer = false;
            for (PlannerCustomer pl: plannerCustomers){
                if(pl.getCustomerId() == customer_id){
                    isCustomer = true;
                    break;
                }
            }
            if(!isCustomer){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"非客户发起的查看资产请求");
            }
        }else{
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"非理财师不得查看客户资料");
        }

        List<AssetsHistory> assetsHistoryList = assetsService.getHistory(customer_id);
        List<Map> result = new ArrayList<>();
        if(assetsHistoryList == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"无资产数据信息");
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
            map.put("foundDay",product.getFoundDay());
            map.put("valueDay",product.getValueDay());
            map.put("redeemDay",product.getRedeemDay());
            map.put("dividendDay",product.getDividendDay());
            map.put("annualYield",product.getAnnualYield());//年化收益率
            map.put("buyDay",product.getBuyDay());//开放申购日
            map.put("deadDate",asset.getDeadDate());//截止日期
            map.put("paymentDate",asset.getPaymentDate());//预期到账时间
            map.put("buyTime",asset.getBuyTime());//开放认购时间


            map.put("notice",product.getNotice());//产品成立公告
            map.put("proveUrl",product.getProveUrl());//备案证明

            result.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }

    /**
     * 资产配置建议
     * @return
     */
    @RequestMapping(value = "/api/suggest/assets", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult suggestAssets() {
        List<Dictionary> dictionaryList = dictionaryService.findDicByType(Const.DIC_CAT.ASSET_CONFIG);
        Map map = new HashMap();
        List<Map> result = new ArrayList<>();
        if (dictionaryList != null) {
            for (Dictionary dictionary : dictionaryList) {
                map.put("key", dictionary.getKey());
                map.put("value", dictionary.getValue());
                result.add(map);
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }


    }
