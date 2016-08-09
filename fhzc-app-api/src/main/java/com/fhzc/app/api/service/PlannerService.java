package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Planner;

/**
 * Created by freeman on 16/8/8.
 */
public interface PlannerService {
   Planner getPlanner(Integer planner_id);
}
