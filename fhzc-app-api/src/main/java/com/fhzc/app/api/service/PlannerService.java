package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Planner;

/**
 * Created by freeman on 16/8/8.
 */
public interface PlannerService {

   Planner getPlanner(Integer planner_id);

    /**
     * 根据用户id获得理财师
     * @param userId
     * @return
     */
   Planner getPlannerByUid(Integer userId);
}
