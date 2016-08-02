package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.PlannerCustomerService;
import com.fhzc.app.dao.mybatis.inter.PlannerCustomerMapper;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerCustomer;
import com.fhzc.app.dao.mybatis.model.PlannerCustomerExample;
import com.fhzc.app.dao.mybatis.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/8/2.
 */
@Service
public class PlannerCustomerServiceImpl implements PlannerCustomerService {

    @Resource
    PlannerCustomerMapper plannerCustomerMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public List<PlannerCustomer> getPlannerCustomerList(Integer planner_id){
        PlannerCustomerExample example = new PlannerCustomerExample();
        PlannerCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(planner_id);

        if(plannerCustomerMapper.countByExample(example) > 0){
            List<PlannerCustomer> list = plannerCustomerMapper.selectByExample(example);
            return list;
        }else{
            return null;
        }
    }
}
