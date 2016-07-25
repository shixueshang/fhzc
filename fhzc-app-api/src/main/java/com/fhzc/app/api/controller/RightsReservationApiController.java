package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.RightsReservationService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiajitao on 2016/7/22.
 */
@Controller
public class RightsReservationApiController {
    @Resource
    private RightsReservationService rightsReservationService;

    @RequestMapping(value = "/api/rights/exchange",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult rightsReservation(Integer customerId    ,Integer plannerId,Integer markDate,String detail,Integer scoreCost,String memo){
        RightsReservation rightsReservation =new RightsReservation();
        rightsReservation.setCustomerId(customerId);
        rightsReservation.setPlannerId(plannerId);//理财师id
        SimpleDateFormat sbf=new SimpleDateFormat("yyyyMMdd");
        Date markDateForm=null;
        try {
            markDateForm=sbf.parse(markDate.toString());
        } catch (ParseException e) {

        }
        if(markDateForm==null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED,"date formart is error");
        }else {
            rightsReservation.setMarkDate(markDateForm);
        }
        try {
            rightsReservation.setDetail(URLDecoder.decode(detail, "utf-8"));
            rightsReservation.setMemo(URLDecoder.decode(memo,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        rightsReservation.setScoreCost(scoreCost);

        rightsReservation.setCtime(new Date());
        rightsReservation.setStatus(APIConstants.RightsOrderStatus.ORDER_SUCCESS);
        rightsReservationService.addOrUpdateProductReservation(rightsReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    @RequestMapping(value = "/api/rights/exchange/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult rightsReservationExchange(Integer id  ){
        RightsReservation rightsReservation =rightsReservationService.getRightsReservation(id);
        rightsReservation.setStatus(APIConstants.RightsOrderStatus.ORDER_CANCEL);
        rightsReservationService.addOrUpdateProductReservation(rightsReservation);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    public static void main(String args[]){
        try {
            System.out.println(URLDecoder.decode("%E5%A4%87%E6%B3%A8","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
