package com.fhzc.app.api.controller;

import  com.fhzc.app.api.exception.BadRequestException;
import com.fhzc.app.api.service.ActivityApplyService;
import com.fhzc.app.api.service.ActivityService;
import com.fhzc.app.api.service.VerifyCodeService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by jiajitao on 2016/7/23.
 */
@Controller
public class ActivityApplyApiController extends BaseController {

    @Resource
    private ActivityApplyService activityApplyService;

    @Resource
    private ActivityService activityService;

    @Resource
    private VerifyCodeService verifyCodeService;

    /**
     * 报名活动  为他人报名的理财师id为0
     * @param activityApply
     * @return
     */
    @RequestMapping(value = "/api/activity/join",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult activityApplyJoin(ActivityApply activityApply){

        Integer plannerId = activityApply.getPlannerId();
        if(plannerId == 0){

            if(activityApply.getPhone() == null || activityApply.getPhone().length() == 0){
                throw new BadRequestException("手机号不能为空");
            }

            if(!verifyCodeService.checkVerifyCode(activityApply.getPhone(), activityApply.getVerifyCode())){
                throw new BadRequestException("验证码输入错误");
            }
        }
        activityApply.setPlannerId(plannerId);
        activityApply.setType(APIConstants.ActivityApply.TYPE_SELF);
        activityApply.setCtime(new Date());
        activityApply.setResult(APIConstants.ActivityApply.RESULT_YES);
        activityApplyService.addOrUpdateActivityApply(activityApply);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 取消活动报名
     * @param activityApplyId
     * @return
     */
    @RequestMapping(value = "/api/activity/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult activityApplyCancel(Integer activityApplyId){

        ActivityApply activityApply=activityApplyService.getActivityApply(activityApplyId);
        activityApply.setResult(APIConstants.ActivityApply.RESULT_NO);
        activityApplyService.addOrUpdateActivityApply(activityApply);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 我报名的活动
     * @param customer_id
     * @return
     */
    @RequestMapping(value = "/api/personal/activity/apply",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalActivityApply(Integer customer_id){
        List<ActivityApply> activityApplyList = activityApplyService.getActivityApplyList(customer_id);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if(activityApplyList != null){
            for(ActivityApply apply : activityApplyList) {
                Activity activity = activityService.getActivity(apply.getActivityId());
                Map<String, Object> map = new HashMap<String, Object>();
                if(activity != null) {
                    map.put("activityApplyId", apply.getId());
                    map.put("activityId", apply.getActivityId());
                    map.put("name", activity.getName());
                    map.put("cover", activity.getCover());
                    map.put("applyEndTime", activity.getApplyEndTime());
                    map.put("status", activityService.getActivityStatus(activity));
                    result.add(map);
                }
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
