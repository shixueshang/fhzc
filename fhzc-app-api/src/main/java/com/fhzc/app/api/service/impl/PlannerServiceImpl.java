package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.PlannerService;
import com.fhzc.app.dao.mybatis.inter.PlannerMapper;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerCustomerExample;
import com.fhzc.app.dao.mybatis.model.PlannerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/8/8.
 */
@Service
public class PlannerServiceImpl implements PlannerService{

    @Resource
    PlannerMapper plannerMapper;

    @Override
    public Planner getPlanner(Integer plannerId) {
        return plannerMapper.selectByPrimaryKey(plannerId);
    }

    @Override
    public Planner getPlannerByUid(Integer userId) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(userId);
        if (plannerMapper.countByExample(example) > 0) {
            return plannerMapper.selectByExample(example).get(0);
        }
        return null;
    }
}
