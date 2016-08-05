package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.PlannerCustomer;

import java.util.List;

/**
 * Created by freeman on 16/8/2.
 */
public interface PlannerCustomerService {
    List<PlannerCustomer> getPlannerCustomerList(Integer planner_id);

    List<PlannerCustomer> getCustomerPlannerList(Integer customer_id);
}
