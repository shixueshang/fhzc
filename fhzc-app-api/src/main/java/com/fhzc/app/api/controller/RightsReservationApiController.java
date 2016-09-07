package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.RightsReservationService;
import com.fhzc.app.api.service.RightsService;
import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @Resource
    private ScoreService scoreService;

    public static long LONGHOUR = 24;

    public static String LESS_THEN_LONGHOUR_MESSAGE = "不能取消,请联系客服";

    /**
     * 权益兑换
     * @param rightsReservation
     * @return
     */
    @RequestMapping(value = "/api/rights/exchange",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult rightsReservation(RightsReservation rightsReservation){

        User user  = super.getCurrentUser();
        Integer userScore = scoreService.getAvailableScore(user.getUid());
        Rights rights = rightsService.getRights(rightsReservation.getRightsId());
        if (userScore < rights.getSpendScore()) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED,"积分可用余额不足");
        }
        rightsReservation.setCtime(new Date());
        rightsReservation.setScoreCost(rights.getSpendScore());
        rightsReservation.setStatus(Const.RIGHTS_STATUS.ORDER_ING);
        rightsReservationService.addOrUpdateRightsReservation(rightsReservation);

        ScoreHistory scoreHistory = new ScoreHistory();
        Integer uid = getCurrentUser().getUid();
        scoreHistory.setUid(uid);
        scoreHistory.setScore(rightsReservation.getScoreCost() * -1);
        scoreHistory.setEventId(rightsReservation.getRightsId());
        scoreHistory.setStatus(Const.Score.FROZEN);
        scoreHistory.setOperatorId(uid);
        scoreHistory.setOperatorType("user");
        scoreHistory.setDetail("用户主动预约权益兑换");
        scoreHistory.setFromType(Const.FROM_TYPE.RIGHTS);
        scoreHistory.setCtime(new Date());
        scoreHistory.setIsVaild(Const.SCORE_VAILD.IS_VAILD);
        scoreHistory.setIsApprove(Const.APPROVE_STATUS.APPROVED);


        scoreService.add(scoreHistory);

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
    public ApiJsonResult rightsReservationExchange(Integer id, HttpServletResponse response) {
        Integer uid = getCurrentUser().getUid();
        RightsReservation rightsReservation = rightsReservationService.getRightsReservation(id);

        //在指定预约时间24小时之内不允许取消
        long diff = rightsReservation.getMarkDate().getTime() - System.currentTimeMillis();
        if (LONGHOUR < (diff / (1000 * 60 * 60))) {
            rightsReservation.setStatus(Const.RIGHTS_STATUS.ORDER_CANCEL);
            //取消后恢复积分冻结状态,即删除冻结记录
            scoreService.delete(uid, id, Const.FROM_TYPE.RIGHTS);
        } else {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED, LESS_THEN_LONGHOUR_MESSAGE);
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
            map.put("name",rights.getName());
            map.put("cover",rights.getCover());
            map.put("spendScore",rights.getSpendScore());
            map.put("level",rights.getLevel());
            map.put("levelName",super.getDicName(rights.getLevel(), Const.DIC_CAT.CUSTOMER_LEVEL));
            map.put("markDate",reserv.getMarkDate());
            map.put("status",reserv.getStatus());
            result.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
