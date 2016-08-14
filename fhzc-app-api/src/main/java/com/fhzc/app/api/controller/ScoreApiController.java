package com.fhzc.app.api.controller;

import com.fhzc.app.api.controller.BaseController;
import com.fhzc.app.api.service.CustomerService;
import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/7/29.
 */
@Controller
public class ScoreApiController extends BaseController {

    @Resource
    private ScoreService scoreService;

    @Resource
    private CustomerService customerService;

    /**
     * 积分记录
     * @return
     */
    @RequestMapping(value = "/api/personal/score",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalScore(){
        User user = super.getCurrentUser();
        Customer customer = customerService.getCustomerByUid(user.getUid());
        Integer customerId = customer.getCustomerId();

        Integer available = scoreService.sumScore(scoreService.getAvailableList(customerId));
        Integer frozen= scoreService.sumScore(scoreService.getFrozen(customerId));
        Integer expired = scoreService.sumScore(scoreService.getWillExpired(customerId));

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("yours",available + frozen);
        map.put("available",available);
        map.put("frozen",frozen);
        map.put("expired",expired);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, map);
    }

    /**
     * 积分详情查询
     * @param type
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/api/personal/score/detail",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalScoreDetail(String type, String start, String end){
        User user = super.getCurrentUser();
        Customer customer = customerService.getCustomerByUid(user.getUid());
        Integer customerId = customer.getCustomerId();
        List<ScoreHistory>  scoreHistory = null;

        SimpleDateFormat sbf=new SimpleDateFormat("yyyy-MM-dd");
        Date scoreStart=null;
        Date scoreEnd=null;
        try {
            scoreStart=sbf.parse(start);
            scoreEnd=sbf.parse(end);
        } catch (ParseException e) {

        }
        if(scoreStart==null || scoreEnd==null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED,"date formart is error");
        }else {
            switch (type){
                case "all":
                    scoreHistory = scoreService.getAllList(customerId,scoreStart,scoreEnd);
                    break;
                case "available":
                    scoreHistory = scoreService.getAvailableList(customerId,scoreStart,scoreEnd);
                    break;
                case "frozen":
                    scoreHistory = scoreService.getFrozen(customerId,scoreStart,scoreEnd);
                    break;
                case "will":
                    scoreHistory = scoreService.getWillExpired(customerId,scoreStart,scoreEnd);
                    break;
                default:
                    break;
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, scoreHistory);
    }

}
