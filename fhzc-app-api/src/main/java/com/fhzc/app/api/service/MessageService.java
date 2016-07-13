package com.fhzc.app.api.service;

import com.fhzc.app.system.mybatis.model.ImMessage;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public interface MessageService {

    /**
     * 发送消息
     * @param message
     */
    public void sendMessgeToSession(ImMessage message);

    /**
     * 获得在指定之间之后所有发给我的未读消息
     * @param userId
     * @param lastSyncDate
     * @return
     */
    public List<ImMessage> getUnreadMessages(Integer userId, Date lastSyncDate);
}
