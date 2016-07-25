package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ActivityApply;

/**
 * Created by jiajitao on 2016/7/23.
 */
public interface ActivityApplyService {

    public void addOrUpdateActivityApply(ActivityApply activityApply);

    public ActivityApply getActivityApply(Integer id) ;

}
