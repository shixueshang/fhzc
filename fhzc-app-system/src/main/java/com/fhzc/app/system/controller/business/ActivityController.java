package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.page.PageHelper;
import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.system.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lihongde on 2016/7/18 15:38
 */
@Controller
@RequestMapping(value = "business/activity")
public class ActivityController extends BaseController {

    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listActivities(){
        ModelAndView mav = new ModelAndView("business/activity/list");
        PageableResult<Activity> pageableResult = activityService.findPageActivies(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activities", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/pub")
    public String preAdd(){
        return "business/activity/add";
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
        return mav;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("business/activity/add");
        mav.addObject("activity", activityService.getActivity(id));
        return mav;
    }
}
