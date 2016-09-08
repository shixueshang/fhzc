package com.fhzc.app.api.controller;

import  com.fhzc.app.api.exception.BadRequestException;
import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.util.Const;
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

    @Resource
    private UserService userService;

    @Resource
    private CustomerService customerService;

    /**
     * 报名活动  为他人报名的理财师id为0
     * @param activityApply
     * @return
     */
    @RequestMapping(value = "/api/activity/join",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult activityApplyJoin(ActivityApply activityApply){

        Map<String, Object> result = new HashMap<String, Object>();
        //判断该客户是否已经报名
        ActivityApply apply = activityApplyService.getActivityIdByCustomerId(activityApply.getCustomerId(), activityApply.getActivityId());
        if(apply != null){
            activityApply.setId(apply.getId());
        }

        Integer plannerId = activityApply.getPlannerId();
        if(plannerId == 0){

            if(activityApply.getPhone() == null || activityApply.getPhone().length() == 0){
                throw new BadRequestException("手机号不能为空");
            }

            if(!verifyCodeService.checkVerifyCode(activityApply.getPhone(), activityApply.getVerifyCode())){
                throw new BadRequestException("验证码输入错误");
            }
        }
        Activity activity = activityService.getActivity(activityApply.getActivityId());
        Integer status = activityService.getActivityStatus(activity);
        if (status.equals(Const.ACTIVITY_STATUS.GOING)) {
            activityApply.setPlannerId(plannerId);
            activityApply.setType(plannerId == 0 ? APIConstants.ActivityApply.TYPE_INVITE : APIConstants.ActivityApply.TYPE_SELF);
            activityApply.setCtime(new Date());
            activityApply.setPersonNum(activityApply.getPersonNum());
            Customer customer = customerService.getCustomer(activityApply.getCustomerId());
            activityApply.setPersonName(userService.getUser(customer.getUid()).getRealname());
            activityApply.setResult(APIConstants.ActivityApply.RESULT_YES);
            activityApplyService.addOrUpdateActivityApply(activityApply);

            result.put("type", plannerId == 0 ? APIConstants.ActivityApply.TYPE_INVITE : APIConstants.ActivityApply.TYPE_SELF);
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
        }
        if (status.equals(Const.ACTIVITY_STATUS.WILL)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"未到活动报名时间,敬请期待");
        }

        if (status.equals(Const.ACTIVITY_STATUS.ACT_OVER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"活动已结束");
        }

        if (status.equals(Const.ACTIVITY_STATUS.APP_OVER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"活动报名已经结束,不能报名");
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"发生未知错误");
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
