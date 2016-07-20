package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.UserService;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.dao.mybatis.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
