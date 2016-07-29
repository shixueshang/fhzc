package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.bo.ActivityApplyBo;
import com.fhzc.app.dao.mybatis.model.ActivityApplyQuery;
import com.fhzc.app.dao.mybatis.model.Level;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.system.service.ActivityService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.LevelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private LevelService levelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listActivities(){
        ModelAndView mav = new ModelAndView("business/activity/list");
        PageableResult<Activity> pageableResult = activityService.findPageActivies(page, size);
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
    public ModelAndView addActivity(Activity activity, MultipartFile coverFile){
        ModelAndView mav = new ModelAndView("business/activity/list");

        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            activity.setCover(coverPath + coverName);
        }
        activity.setCtime(new Date());
        activityService.addOrUpdateActivity(activity);

        PageableResult<Activity> pageableResult = activityService.findPageActivies(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activities", pageableResult.getItems());
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        return mav;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("business/activity/add");
        mav.addObject("activity", activityService.getActivity(id));
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        return mav;
    }

    @RequestMapping(value = "/registers", method = RequestMethod.GET)
    public ModelAndView listActivityApplies(String activityName, String identityId, Date startTime, Date endTime){
        ModelAndView mav = new ModelAndView("business/activity/registerList");
        ActivityApplyQuery query = new ActivityApplyQuery();
        if (StringUtils.isNotBlank(activityName)){

        }

        if (StringUtils.isNotBlank(identityId)){
            query.setCustomerIdCardNum(identityId);
        }

        if (startTime != null){
            query.setStartDate(startTime);
        }

        if (endTime != null){
            query.setEndDate(endTime);
        }

        PageableResult<ActivityApplyBo> pageableResult = activityService.findPageActivityApplies(query, page, size);
        List<ActivityApplyBo> bos = pageableResult.getItems();
        if (!CollectionUtils.isEmpty(bos)){
            Map<Long, String> activityMap = new HashMap<Long, String>();
            Map<Long, String> levelMap = new HashMap<Long, String>();
            for (ActivityApplyBo bo : bos){
                long aid = bo.getActivityId();
                if (activityMap.containsKey(aid)){
                    bo.setActivityName(activityMap.get(aid));
                } else {
                    Activity activity = activityService.getActivity((int)aid);
                    if (activity != null){
                        bo.setActivityName(activity.getName());
                        activityMap.put(aid, activity.getName());
                    }
                }

                long lid = bo.getLevelId();
                if (levelMap.containsKey(lid)){
                    bo.setClientLevel(levelMap.get(lid));
                } else {
                    Level level = levelService.findLevelById((int)lid);
                    if (level != null){
                        String levelDis = level.getName() + "-" + level.getValue();
                        bo.setClientLevel(levelDis);
                        levelMap.put(lid, levelDis);
                    }
                }
            }
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activityApplies", pageableResult.getItems());
        return mav;
    }
}
