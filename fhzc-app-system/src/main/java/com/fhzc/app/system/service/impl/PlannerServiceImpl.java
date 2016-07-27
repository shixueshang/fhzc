package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.PlannerMapper;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.system.service.PlannerService;

import javax.annotation.Resource;

/**
 * Created by menghq on 2016/7/26.
 */
public class PlannerServiceImpl implements PlannerService {
    @Resource
    PlannerMapper plannerMapper;

    /**
     * 获得理财师信息
     * @param id
     * @return
     */
    public Planner getPlanner(Integer id) {
        return plannerMapper.selectByPrimaryKey(id);
    }
}
