package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.User;

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

    /**
     * 查询指定证件类型证件号的用户是否存在
     * @param identity
     * @param identityNum
     * @return
     */
    User checkUserExists(Integer identity, String identityNum);

    /**
     * 根据用户名或者手机号查询用户
     * @param loginName
     * @return
     */
    User getUserByLogin(String loginName);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询手机号是否存在
     * @param mobile
     * @param user
     * @return
     */
    public boolean checkMobileExists(String mobile, User user);
}
