package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    /**
     * 积分记录
     * @return
     */
    @RequestMapping(value = "/api/personal/score",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalScore(){
        User user = super.getCurrentUser();
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("yours", scoreService.getTotalScore(user.getUid()));
        map.put("available", scoreService.getAvailableScore(user.getUid()));
        map.put("frozen", scoreService.getFrozenScore(user.getUid()));
        map.put("expired", scoreService.getExpiredScore(user.getUid()));

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
    public ApiJsonResult personalScoreDetail(String type, Date start, Date end){
        User user = super.getCurrentUser();
        Integer uid = user.getUid();
        List<ScoreHistory>  scoreHistory = null;

        switch (type){
            case "all":
                scoreHistory = scoreService.getAllScoreList(uid, start, end);
                break;
            case "available":
                scoreHistory = scoreService.getAvailableScore(uid, start, end);
                break;
            case "frozen":
                scoreHistory = scoreService.getFrozenScore(uid,start,end);
                break;
            case "will":
                scoreHistory = scoreService.getWillExpiredScore(uid, start, end);
                break;
            default:
                break;
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, scoreHistory);
    }

}
