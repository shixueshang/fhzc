package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lihongde on 2016/8/15 16:05
 */
public class PersonalController extends BaseController {

    @Resource
    private PlannerService plannerService;

    @Resource
    private AchievementService achievementService;

    @Resource
    private AssetsService assetsService;

    @Resource
    private PlannerCustomerService plannerCustomerService;

    @Resource
    private CustomerService customerService;

    @Resource
    private ProductService productService;

    /**
     * 我的工作
     * @return
     */
    @RequestMapping(value = "/api/achievement", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult achievement(){
        User user = super.getCurrentUser();
        if (!user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"非理财师用户");
        }
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        Map map = new HashMap();

        Calendar cal = Calendar.getInstance();
        Integer currentYear = cal.get(Calendar.YEAR);
        Integer currentMonth = cal.get(Calendar.MONTH)+1;

        map.put("monthSale", achievementService.getMonthSale(planner.getId(),currentYear,currentMonth));
        map.put("yearSale", achievementService.getYearSale(planner.getId(),currentYear));

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, map);
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
                if(pl.getCustomerId().equals(customer_id)){
                    isCustomer = true;
                    break;
                }
            }
            if(!isCustomer){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"您不是此客户的理财师");
            }
        }

        if(user.getLoginRole().equals(Const.USER_ROLE.CUSTOMER)) {
            Customer customer = customerService.getCustomerByUid(user.getUid());
            if(!customer.getCustomerId().equals(customer_id)){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"不得其他查看客户资料");
            }
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

}
