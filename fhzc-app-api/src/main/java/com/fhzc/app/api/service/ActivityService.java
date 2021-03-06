package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Activity;
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
     * 获得推荐活动列表
     * @return
     */
    List<Activity> getRecommendActivityList();


    /**
     * 获得活动信息
     * @param id
     * @return
     */
    Activity getActivity(Integer id);

    /**
     * 计算活动状态
     * @param activity
     * @return
     */
    Integer getActivityStatus(Activity activity);

    /**
     * 给活动列表批量修改状态
     * @param list
     * @return
     */
    List<Activity> setActivityListStatus(List<Activity> list);
}
