package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Customer;

/**
 * Created by lihongde on 2016/7/28 17:29
 */
public interface CustomerService {

    /**
     * 根据uid获得客户信息
     * @param uid
     * @return
     */
    Customer getCustomerByUid(Integer uid);
}
