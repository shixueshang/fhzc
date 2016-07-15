package com.fhzc.app.api.service;

import com.fhzc.app.system.mybatis.model.ImMessage;

import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/7/13.
 */
public interface MessageService {

    /**
     * 发送消息
     * @param message
     */
     ImMessage sendMessgeToSession(ImMessage message);

    /**
     * 获得在指定之间之后所有发给我的未读消息
     * @param userId
     * @param lastSyncDate
     * @return
     */
     List<ImMessage> getUnreadMessages(Integer userId, Date lastSyncDate);

    /**
     * 获得指定mid之前的历史消息
     * @param sessionId 群组id
     * @param mid
     * @param limit 条数
     * @return
     */
    List<ImMessage> findHistoryMessages(String sessionId, Integer mid, Integer limit);

    /**
     * 判断是否有聊天记录,如果有则返回sessionId
     * @param sendUserId
     * @param toUserId
     * @return
     */
    String hasChatHistory(Integer sendUserId, Integer toUserId);

    /**
     * 根据id获得消息记录
     * @param messageId
     * @return
     */
    ImMessage getMessage(Integer messageId);
}
