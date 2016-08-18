package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.PushTokenMapper;
import com.fhzc.app.dao.mybatis.model.PushToken;
import com.fhzc.app.dao.mybatis.model.PushTokenExample;
import com.fhzc.app.system.service.PushTokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/17 18:49
 */
@Service
public class PushTokenServiceImpl implements PushTokenService {

    @Resource
    private PushTokenMapper pushTokenMapper;

    @Override
    public List<PushToken> getAllTokens() {
        PushTokenExample example = new PushTokenExample();
        return pushTokenMapper.selectByExample(example);
    }
}
