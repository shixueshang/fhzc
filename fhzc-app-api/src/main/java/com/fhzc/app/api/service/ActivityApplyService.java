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

    ActivityApply getActivityIdByCustomerId(Integer customerId, Integer activityId);
    
    ActivityApply getActivityIdByPersonName(String phone, String personName);
}
