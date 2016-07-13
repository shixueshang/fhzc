package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.MessageService;
import com.fhzc.app.system.mybatis.inter.ImMessageMapper;
import com.fhzc.app.system.mybatis.model.ImMessage;
import com.fhzc.app.system.mybatis.model.ImMessageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/7/13.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private ImMessageMapper imMessageMapper;

    @Override
    public void sendMessgeToSession(ImMessage message) {
        Integer mid = 0; //每个聊天组每一条消息对应一个mid
        ImMessageExample example = new ImMessageExample();
        ImMessageExample.Criteria criteria  = example.createCriteria();
        criteria.andSessionIdEqualTo(message.getSessionId());
        example.setOrderByClause("id desc");
        List<ImMessage> messageList = imMessageMapper.selectByExample(example);
        if (messageList.size() > 0) {
            mid = messageList.get(0).getMid() + 1;
        }
        message.setMid(mid);
        imMessageMapper.insert(message);
    }

    @Override
    public List<ImMessage> getUnreadMessages(Integer userId, Date lastSyncDate) {
        ImMessageExample example = new ImMessageExample();
        ImMessageExample.Criteria criteria  = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andSendTimeGreaterThanOrEqualTo(lastSyncDate);
        example.setOrderByClause("id desc");
        return imMessageMapper.selectByExample(example);
    }
}
