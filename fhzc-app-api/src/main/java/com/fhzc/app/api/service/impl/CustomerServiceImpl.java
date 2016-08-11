package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.CustomerService;
import com.fhzc.app.dao.mybatis.inter.CustomerMapper;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.CustomerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/28 17:30
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Customer getCustomerByUid(Integer uid) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        if(customerMapper.countByExample(example) > 0){
            return customerMapper.selectByExample(example).get(0);
        }
        return null;
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }
}
