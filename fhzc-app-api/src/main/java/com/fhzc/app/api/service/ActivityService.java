package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.page.PageableResult;

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
    PageableResult<Activity> findPageActivies(int page, int size);

    /**
     * 获得推荐活动列表
     * @return
     */
    PageableResult<Activity> getRecommendActivityList();


    /**
     * 获得活动信息
     * @param id
     * @return
     */
    Activity getActivity(Integer id);
}
