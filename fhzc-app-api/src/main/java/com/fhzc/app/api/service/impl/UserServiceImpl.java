package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.UserService;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.UserExample;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongde on 2016/7/14.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(Integer userId) {
        return decryptUser(userMapper.selectByPrimaryKey(userId));
    }

    @Override
    public User checkUserExists(Integer identity, String identityNum) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPassportTypeIdEqualTo(identity);
        try {
            identityNum = EncryptUtils.encryptToDES(identityNum.substring(identityNum.length() - 8), identityNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        criteria.andPassportCodeEqualTo(identityNum);
        if(userMapper.countByExample(example) > 0){
            return decryptUser(userMapper.selectByExample(example).get(0));
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
            return decryptUser(userMapper.selectByExample(example).get(0));
        }else{
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        String passport = user.getPassportCode();
        String key = passport.substring(passport.length() - 8);
        user.setSalt(key);
        try {
            user.setPassportCode(EncryptUtils.encryptToDES(key, user.getPassportCode()));
            if(user.getMobile() != null){
                user.setMobile(EncryptUtils.encryptToDES(key, user.getMobile()));
            }
            if(user.getEmail() != null){
                user.setEmail(EncryptUtils.encryptToDES(key, user.getEmail()));
            }
        } catch (Exception e) {
            logger.error("加密失败");
            e.printStackTrace();
        }
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public boolean checkMobileExists(String mobile, User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        try {
            mobile = EncryptUtils.encryptToDES(user.getSalt(), mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        criteria.andMobileEqualTo(mobile);
        return userMapper.countByExample(example) > 0;
    }

    /**
     * 解密
     * @param list
     * @return
     */
    private List<User> decryptUser(List<User> list){
        List<User> result = new ArrayList<User>();
        for(User user : list){
            result.add(this.decryptUser(user));
        }
        return result;
    }

    /**
     * 单个用户解密
     * @param user
     * @return
     */
    private User decryptUser(User user){
        String key = user.getSalt();
        if(key == null){
            return user;
        }
        try {
            user.setPassportCode(EncryptUtils.decryptByDES(key, user.getPassportCode()));
            if(user.getMobile() != null){
                user.setMobile(EncryptUtils.decryptByDES(key, user.getMobile()));
            }
            if(user.getEmail() != null){
                user.setEmail(EncryptUtils.decryptByDES(key, user.getEmail()));
            }
            if(user.getPhone() != null){
                user.setPhone(EncryptUtils.decryptByDES(key, user.getPhone()));
            }
            if(user.getPhoneExt() != null){
                user.setPhoneExt(EncryptUtils.decryptByDES(key, user.getPhoneExt()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
