package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.UserService;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.UserExample;
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

    @Override
    public User checkUserExists(Integer identity, String identityNum) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPassportTypeIdEqualTo(identity);
        criteria.andPassportCodeEqualTo(identityNum);
        if(userMapper.countByExample(example) > 0){
            return userMapper.selectByExample(example).get(0);
        }
        return null;
    }

    @Override
    public User getUserByLogin(String loginName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginEqualTo(loginName);

        UserExample.Criteria criteria1 = example.createCriteria();
        criteria1.andMobileEqualTo(loginName);
        example.or(criteria1);
        if(userMapper.countByExample(example) > 0){
            return userMapper.selectByExample(example).get(0);
        }else{
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
