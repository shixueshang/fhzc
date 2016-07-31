package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.User;

/**
 * Created by lihongde on 2016/7/15.
 */
public interface CustomerService {
    /**
     * 根据电话获取用户信息
     * @param phoneNum
     * @return
     */
    User getUserByPhone(String phoneNum);

    /**
     * 根据用户id获取customer
     * @param userId
     * @return
     */
    Customer getCustomerByPhone(Integer userId);
}
