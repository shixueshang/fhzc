package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by lihongde on 2016/7/18 15:38
 */
@Controller
@RequestMapping(value = "business/activity")
public class ActivityController extends BaseController {

    @Resource
    private ActivityService activityService;

    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private FocusService focusService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询活动列表")
    public ModelAndView listActivities(){
        ModelAndView mav = new ModelAndView("business/activity/list");
        PageableResult<Activity> pageableResult = activityService.findPageActivies(page, size);
        for (Activity activity : pageableResult.getItems()) {
        	List<Focus> focuses = focusService.findFocusByType(Const.FOCUS_TYPE.ACTIVITY, activity.getId());
        	activity.setFocusNum(focuses.size() > 0 ? focuses.size() : 0);
        	List<ActivityApply> orders = activityService.findSuccessOrdersById(activity.getId(), 1);
        	int personNum = 0;
        	if(orders.size() > 0){
            	for (ActivityApply activityApply : orders) {
    				personNum = personNum+activityApply.getPersonNum();
    			}
        	}
        	activity.setOrderNum(personNum > 0 ? personNum : 0);
		}
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activities", pageableResult.getItems());
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        mav.addObject("url", "business/activity");
        return mav;
    }

    @RequestMapping(value = "/pub")
    public ModelAndView preAdd(){
        ModelAndView mav = new ModelAndView("business/activity/add");
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        mav.addObject("url", "business/activity");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改活动")
    public String addActivity(Activity activity, MultipartFile coverFile){

        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            activity.setCover(coverPath + coverName);
        }
        activity.setCtime(new Date());
        activityService.addOrUpdateActivity(activity);

        return "redirect:/business/activity/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("business/activity/add");
        mav.addObject("activity", activityService.getActivity(id));
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        return mav;
    }

    @RequestMapping(value = "/registers", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看活动报名列表")
    public ModelAndView listActivityApplies(String activityName, Date startTime, Date endTime){
        ModelAndView mav = new ModelAndView("business/activity/registerList");
        ActivityApplyQuery query = new ActivityApplyQuery();

        if(StringUtils.isNotEmpty(activityName)){
            Activity activity = activityService.findActivityByName(activityName);
            if(activity != null){
                query.setActivityId(activity.getId());
            }
        }
        query.setActivityName(activityName);

        if (startTime != null){
            query.setStartDate(startTime);
        }

        if (endTime != null){
            query.setEndDate(endTime);
        }

        PageableResult<ActivityApply> pageableResult = activityService.findPageActivityApplies(query, page, size);
        List<ActivityApply> list = new ArrayList<ActivityApply>();
        for(ActivityApply apply : pageableResult.getItems()){
            apply.setActivityName(activityService.getActivity(apply.getActivityId()).getName());
            Customer customer = customerService.getCustomer(apply.getCustomerId());
            apply.setLevel(super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
            apply.setPersonName(userService.getUser(customer.getUid()).getRealname());
            apply.setMobile(userService.getUser(customer.getUid()).getMobile());
            list.add(apply);
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activityApplies", list);
        mav.addObject("url", "business/activity");
        return mav;
    }


}
