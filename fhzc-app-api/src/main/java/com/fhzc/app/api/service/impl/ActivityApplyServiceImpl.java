package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ActivityApplyService;
import com.fhzc.app.dao.mybatis.inter.ActivityApplyMapper;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jiajitao on 2016/7/23.
 */
@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {
    @Resource
    private ActivityApplyMapper activityApplyMapper;
    @Override
    public void addOrUpdateActivityApply(ActivityApply activityApply) {
        Integer id= activityApply.getId();
        if(id == null){
            activityApplyMapper.insert(activityApply);
        }else{
            activityApplyMapper.updateByPrimaryKey(activityApply);
        }
    }

    @Override
    public ActivityApply getActivityApply(Integer id) {
        return activityApplyMapper.selectByPrimaryKey(id);
    }
}
