package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.PushToken;

import java.util.List;

/**
 * Created by lihongde on 2016/8/17 18:48
 */
public interface PushTokenService {

    /**
     * 获得所有登陆过的设备
     * @return
     */
    List<PushToken> getAllTokens();

    /**
     * 向用户推送消息
     * @param toUserId
     * @param content
     * @throws Exception
     */
    public void pushMessageToUser(Integer toUserId, String content) throws Exception;
}
