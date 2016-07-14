package com.fhzc.app.api.service;

import com.fhzc.app.system.mybatis.model.User;

/**
 * Created by lihongde on 2016/7/14.
 */
public interface UserService {

    /**
     * 获得用户信息
     * @param userId
     * @return
     */
    User getUser(Integer userId);
}
