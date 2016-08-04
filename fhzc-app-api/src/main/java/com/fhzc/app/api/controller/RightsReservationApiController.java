package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.RightsReservationService;
import com.fhzc.app.api.service.RightsService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jiajitao on 2016/7/22.
 */
@Controller
public class RightsReservationApiController extends BaseController {
    @Resource
    private RightsReservationService rightsReservationService;

    @Resource
    private RightsService rightsService;

    public static long LONGHOUR = 24;

    public static String LESS_THEN_LONGHOUR_MESSAGE = "未超过24小时不能取消";

    public static String GETSTATUS_FAIL_MESSAGE = "获取状态错误";

    /**
     * 权益兑换
     * @param rightsReservation
     * @return
     */
    @RequestMapping(value = "/api/rights/exchange",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult rightsReservation(RightsReservation rightsReservation){
        Rights rights = rightsService.getRights(rightsReservation.getRightsId());
        rightsReservation.setCtime(new Date());
        rightsReservation.setScoreCost(rights.getSpendScore());
        rightsReservation.setStatus(APIConstants.RightsOrderStatus.ORDER_ING);
        rightsReservationService.addOrUpdateRightsReservation(rightsReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 取消权益兑换
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/api/rights/exchange/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult rightsReservationExchange(Integer id ,HttpServletResponse response ){

        RightsReservation rightsReservation =rightsReservationService.getRightsReservation(id);
        //如果状态为预约中（即未审核过的）可以直接取消
        if(APIConstants.RightsOrderStatus.ORDER_ING==rightsReservation.getStatus()){
            rightsReservation.setStatus(APIConstants.RightsOrderStatus.ORDER_CANCEL);
         //如果状态为预约成功了 则
        }else if(APIConstants.RightsOrderStatus.ORDER_SUCCESS==rightsReservation.getStatus()){
            rightsReservation.getCtime().getTime();
            //预约时间如果是24小时内 则直接取消  LONGHOUR=24小时
            long diff=System.currentTimeMillis() - rightsReservation.getCtime().getTime();
            if(LONGHOUR<(diff / (1000 * 60 * 60 ))){
                rightsReservation.setStatus(APIConstants.RightsOrderStatus.ORDER_CANCEL);
            }else{
                    return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED,LESS_THEN_LONGHOUR_MESSAGE);
            }
        }else{
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED,GETSTATUS_FAIL_MESSAGE);
        }
        rightsReservationService.addOrUpdateRightsReservation(rightsReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 我预约的权益
     * @param customer_id
     * @return
     */
    @RequestMapping(value = "/api/personal/right/order",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalRightOrder(Integer customer_id){
        List<RightsReservation> reservationList = rightsReservationService.getUserRightsList(customer_id);
        List<Map> result = new ArrayList<>();
        for(RightsReservation reserv : reservationList) {
            Rights rights = rightsService.getRights(reserv.getRightsId());
            Map map = new HashMap();

            map.put("id",reserv.getId());
            map.put("rightsId",reserv.getRightsId());
            map.put("cover",rights.getCover());
            map.put("markDate",reserv.getMarkDate());
            map.put("status",reserv.getStatus());
            result.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
