package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ActivityApplyService;
import com.fhzc.app.api.service.ActivityService;
import com.fhzc.app.api.service.FocusService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.model.Activity;
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
public class ActivityApiController extends BaseController{

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityApplyService activityApplyService;

    @Resource
    private FocusService focusService;

    @RequestMapping(value = "/api/activity", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult activityList(){
        PageableResult<Activity> activityList = activityService.findPageActivies(0,100);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,activityList);
    }

    @RequestMapping(value = "/api/activity/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult activityDetail(Integer activityId) throws Exception {
        Activity activity = activityService.getActivity(activityId);
        Map result = ObjUtils.objectToMap(activity);
        User user = super.getCurrentUser();

        ActivityApply activityApply = activityApplyService.getByUidActivityId(user.getUid(),activityId);
        if(activityApply != null) {
            result.put("activityResult", activityApply.getResult());
            result.put("activityIsContact", activityApply.getIsContact());
            result.put("activityIsSure", activityApply.getIsSure());
        }

        Focus focus = focusService.getFocusByCond(user.getUid(),activityId,APIConstants.FocusType.Activity);
        if(focus != null){
            result.put("focusStauts",focus.getStatus());
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,activity);
    }
}
