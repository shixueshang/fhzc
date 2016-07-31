package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ActivityApply;

import java.util.List;

/**
 * Created by jiajitao on 2016/7/23.
 */
public interface ActivityApplyService {

    void addOrUpdateActivityApply(ActivityApply activityApply);

    ActivityApply getActivityApply(Integer id) ;

    List<ActivityApply> getActivityApplyList(Integer customer_id);

    ActivityApply getByUidActivityId(Integer uid, Integer activityId);
}
