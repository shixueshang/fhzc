package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.PlannerCustomerService;
import com.fhzc.app.dao.mybatis.inter.PlannerCustomerMapper;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.dao.mybatis.model.PlannerCustomer;
import com.fhzc.app.dao.mybatis.model.PlannerCustomerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
            return plannerCustomerMapper.selectByExampleWithBLOBs(example);
        }else{
            return null;
        }
    }

    @Override
    public List<PlannerCustomer> getCustomerPlannerList(Integer customer_id){
        PlannerCustomerExample example = new PlannerCustomerExample();
        PlannerCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customer_id);

        if(plannerCustomerMapper.countByExample(example) > 0){
            List<PlannerCustomer> list = plannerCustomerMapper.selectByExample(example);
            return list;
        }else{
            return null;
        }
    }

    @Override
    public int updatePlannerCustomer(PlannerCustomer plannerCustomer) {
        return plannerCustomerMapper.updateByPrimaryKeyWithBLOBs(plannerCustomer);
    }

    @Override
    public PlannerCustomer getRow(Integer id){
       return plannerCustomerMapper.selectByPrimaryKey(id);
    }

}
