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

    /**
     * 活动列表
     * @return
     */
    @RequestMapping(value = "/api/activity", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult activityList(){
        PageableResult<Activity> activityList = activityService.findPageActivies(0,100);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,activityList);
    }

    /**
     * 活动详情
     * @param activityId
     * @return
     * @throws Exception
     */
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
        }else{
            result.put("activityResult", "");
            result.put("activityIsContact", "");
            result.put("activityIsSure", "");
        }

        Focus focus = focusService.getFocusByCond(user.getUid(),activityId,APIConstants.FocusType.Activity);
        if(focus != null){
            result.put("focusStatus",focus.getStatus());
        }else{
            result.put("focusStatus","");
        }
        ActivityApply apply= activityApplyService.getByUidActivityId(user.getUid(),activityId);
        if(apply!= null) {
            result.put("applyId", apply.getId());
        }else{
            result.put("applyId", 0);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
