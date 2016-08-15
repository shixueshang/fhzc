package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.PushTokenService;
import com.fhzc.app.dao.mybatis.inter.PushTokenMapper;
import com.fhzc.app.dao.mybatis.model.PushToken;
import com.fhzc.app.dao.mybatis.model.PushTokenExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/15 14:46
 */
@Service
public class PushTokenServiceImpl implements PushTokenService {

    @Resource
    private PushTokenMapper pushTokenMapper;

    @Override
    public List<PushToken> getPushToken(Integer userId) {
        PushTokenExample example = new PushTokenExample();
        PushTokenExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return pushTokenMapper.selectByExample(example);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        PushTokenExample example = new PushTokenExample();
        PushTokenExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        pushTokenMapper.deleteByExample(example);
    }

    @Override
    public void bindToken(PushToken pushToken) {
        pushTokenMapper.insert(pushToken);
    }


}
