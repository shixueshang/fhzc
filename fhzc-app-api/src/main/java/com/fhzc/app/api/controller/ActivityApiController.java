package com.fhzc.app.api.controller;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.mybatis.model.Activity;
import com.fhzc.app.system.mybatis.model.Product;
import com.fhzc.app.system.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/7/19.
 */
@Controller
public class ActivityApiController {

    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/api/activity", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult activityList(){
        PageableResult<Activity> activityList = activityService.findPageActivies(0,100);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,activityList);
    }

    @RequestMapping(value = "/api/activity/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult activityDetail(Integer activityId){
        Activity activity = activityService.getActivity(activityId);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,activity);
    }
}
