package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.DeviceInfo;
import com.fhzc.app.dao.mybatis.model.PushToken;

import java.util.List;

/**
 * Created by lihongde on 2016/8/15 14:44
 */
public interface PushTokenService {
    /**
     * 获得用户绑定的设备列表
     * @param userId
     * @return
     */
    List<PushToken> getPushToken(Integer userId);

    /**
     * 删除绑定的设备
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 绑定设备
     * @param pushToken
     */
    void bindToken(PushToken pushToken);

    /**
     * 向用户推送消息
     * @param toUserId
     * @param content
     * @throws Exception
     */
    void pushMessageToUser(Integer toUserId, String content) throws Exception;

    /**
     * 收集设备信息
     * @param deviceInfo
     */
    void collectDeviceInfo(DeviceInfo deviceInfo);
}
