package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ActivityApplyService;
import com.fhzc.app.dao.mybatis.inter.ActivityApplyMapper;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import com.fhzc.app.dao.mybatis.model.ActivityApplyExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<ActivityApply> getActivityApplyList(Integer customer_id) {
        ActivityApplyExample example = new ActivityApplyExample();
        ActivityApplyExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customer_id);
        return activityApplyMapper.selectByExample(example);
    }
}
