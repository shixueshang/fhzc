package com.fhzc.app.api.controller;

import com.fhzc.app.api.controller.BaseController;
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

    @RequestMapping(value = "/api/personal/score",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalScore(){
        User user = super.getCurrentUser();
        Integer uid = user.getUid();
        Integer available = scoreService.sumScore(scoreService.getAvailableList(uid));

        Integer frozen= scoreService.sumScore(scoreService.getFrozen(uid));

        Integer expired = scoreService.sumScore(scoreService.getWillExpired(uid));

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("yours",available + frozen);
        map.put("available",available);
        map.put("frozen",frozen);
        map.put("expired",expired);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, map);
    }

    @RequestMapping(value = "/api/personal/score/detail",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalScoreDetail(String type, Date start, Date end){
        User user = super.getCurrentUser();
        Integer uid = user.getUid();
        List<ScoreHistory>  scoreHistory = null;
        switch (type){
            case "frozen":
                scoreHistory = scoreService.getFrozen(uid);
                break;
            default:
                break;
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, scoreHistory);
    }

}
