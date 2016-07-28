package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ActivityApply;

import java.util.List;

/**
 * Created by jiajitao on 2016/7/23.
 */
public interface ActivityApplyService {

    public void addOrUpdateActivityApply(ActivityApply activityApply);

    public ActivityApply getActivityApply(Integer id) ;

    List<ActivityApply> getActivityApplyList(Integer customer_id);
}
