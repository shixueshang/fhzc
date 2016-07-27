package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Planner;

/**
 * Created by lihongde on 2016/7/15.
 */
public interface PlannerService {
    /**
     * 获得理财师信息
     * @param id
     * @return
     */
    Planner getPlanner(Integer id);
}
