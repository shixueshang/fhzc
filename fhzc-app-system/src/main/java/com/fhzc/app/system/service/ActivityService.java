package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.bo.ActivityApplyBo;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityApplyQuery;
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
    PageableResult<ActivityApplyBo> findPageActivityApplies(ActivityApplyQuery query, int page, int size);
}
