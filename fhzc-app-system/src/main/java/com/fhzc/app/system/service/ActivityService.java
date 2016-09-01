package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import com.fhzc.app.dao.mybatis.model.ActivityApplyQuery;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;

/**
 * Created by lihongde on 2016/7/19 14:30
 */
public interface ActivityService {

    /**
     * 获得活动列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Activity> findPageActivies(List<Integer> departments, int page, int size);

    /**
     * 添加或者修改活动信息
     * @param activity
     */
    void addOrUpdateActivity(Activity activity);

    /**
     * 获得活动信息
     * @param id
     * @return
     */
    Activity getActivity(Integer id);

    /**
     * 获得活动报名列表
     * @param query 过滤条件
     * @param page
     * @param size
     * @return
     */
    PageableResult<ActivityApply> findPageActivityApplies(ActivityApplyQuery query, int page, int size);

    List<Activity> getAllActivities();
    
    /**
     * 根据活动id获得预约成功的活动
     * @param id
     * @param result
     * @return
     */
    List<ActivityApply>findSuccessOrdersById(Integer id, Integer result);

    /**
     * 根据活动名称获取活动
     * @param name
     * @return
     */
    Activity findActivityByName(String name);
    
    /**
     * 根据活动类型获取活动
     * @param typeId
     * @return
     */
    List<Activity> getActivityByType(String typeId);
    
}
