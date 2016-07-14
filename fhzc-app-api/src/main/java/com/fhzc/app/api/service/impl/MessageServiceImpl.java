package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.MessageService;
import com.fhzc.app.system.mybatis.inter.ImMessageMapper;
import com.fhzc.app.system.mybatis.model.ImMessage;
import com.fhzc.app.system.mybatis.model.ImMessageExample;
import org.apache.ibatis.session.RowBounds;
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
    public ImMessage sendMessgeToSession(ImMessage message) {
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
        imMessageMapper.insertSelective(message);

        return message;
    }

    @Override
    public List<ImMessage> getUnreadMessages(Integer userId, Date lastSyncDate) {
        ImMessageExample example = new ImMessageExample();
        ImMessageExample.Criteria criteria  = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andSendTimeGreaterThanOrEqualTo(lastSyncDate);
        example.setOrderByClause("id desc");
        return imMessageMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<ImMessage> findHistoryMessages(String sessionId, Integer mid, Integer limit) {
        ImMessageExample example = new ImMessageExample();
        ImMessageExample.Criteria criteria  = example.createCriteria();
        criteria.andSessionIdEqualTo(sessionId);
        criteria.andMidLessThan(mid);
        RowBounds rowBounds = new RowBounds(0, limit);
        example.setOrderByClause("id desc");

        return imMessageMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
    }

    @Override
    public String hasChatHistory(Integer sendUserId, Integer toUserId) {
        ImMessageExample example = new ImMessageExample();
        ImMessageExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(sendUserId);
        criteria.andToUserIdEqualTo(toUserId);

        ImMessageExample.Criteria criteria2 = example.createCriteria();
        criteria2.andUserIdEqualTo(toUserId);
        criteria2.andToUserIdEqualTo(sendUserId);
        example.or(criteria2);

        if(imMessageMapper.countByExample(example) > 0){
            return imMessageMapper.selectByExample(example).get(0).getSessionId();
        }
        return null;
    }
}
