package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ActivityApplyService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by jiajitao on 2016/7/23.
 */
@Controller
public class ActivityApplyApiController extends BaseController {

    @Resource
    private ActivityApplyService activityApplyService;

    @RequestMapping(value = "/api/activity/join",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult activityApplyJoin(ActivityApply activityApply){

        //如果理财师id为空则活动为自己申请
        String plannerId = activityApply.getPlannerId();
        if("0".equals(plannerId) || StringUtils.isEmpty(plannerId)){
            activityApply.setPlannerId("0");
            activityApply.setType(APIConstants.ActivityApply.TYPE_SELF);
        }else{
            //todo 有理财师则说明活动 为其他人申请的 需要有手机验证码
        }
        activityApply.setCtime(new Date());
        activityApply.setResult(APIConstants.ActivityApply.RESULT_YES);
        activityApplyService.addOrUpdateActivityApply(activityApply);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    @RequestMapping(value = "/api/activity/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult activityApplyCancel(Integer activityApplyId){

        ActivityApply activityApply=activityApplyService.getActivityApply(activityApplyId);
        activityApply.setResult(APIConstants.ActivityApply.RESULT_NO);
        activityApplyService.addOrUpdateActivityApply(activityApply);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }
}
