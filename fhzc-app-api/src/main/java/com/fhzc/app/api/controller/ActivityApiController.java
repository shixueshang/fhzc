package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ActivityApplyService;
import com.fhzc.app.api.service.ActivityService;
import com.fhzc.app.api.service.CustomerService;
import com.fhzc.app.api.service.FocusService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
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

    @Resource
    private CustomerService customerService;

    /**
     * 活动列表
     * @return
     */
    @RequestMapping(value = "/api/activity", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult activityList(){
        PageableResult<Activity> activityList = activityService.findPageActivies(page,size);
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
        Customer customer = customerService.getCustomerByUid(user.getUid());
        if(customer != null){
            ActivityApply apply = activityApplyService.getActivityIdByCustomerId(customer.getCustomerId(),activityId);
            result.put("activityResult", apply == null ? "" : apply.getResult());
            result.put("activityIsContact", apply == null ? "" : apply.getIsContact());
            result.put("activityIsSure", apply == null ? "" : apply.getIsSure());
            result.put("applyId", apply == null ? 0 : apply.getId());
        }

        Focus focus = focusService.getFocusByCond(user.getUid(),activityId,APIConstants.FocusType.Activity);
        result.put("focusStatus",focus == null ? "" :focus.getStatus());

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
