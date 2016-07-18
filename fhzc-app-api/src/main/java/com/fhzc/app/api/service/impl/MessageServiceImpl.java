package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.MessageService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.system.mybatis.inter.ImMessageMapper;
import com.fhzc.app.system.mybatis.model.ImMessage;
import com.fhzc.app.system.mybatis.model.ImMessageExample;
import com.vdurmont.emoji.EmojiParser;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<ImMessage> getUnreadMessages(Integer userId, long version) {
        ImMessageExample example = new ImMessageExample();
        ImMessageExample.Criteria criteria  = example.createCriteria();

        //如果version=0,则返回所有发给我的以及我发过的消息
        if(version != 0){
            Date lastSyncDate = new Date(version * 1000L);
            criteria.andToUserIdEqualTo(userId);
            criteria.andSendTimeGreaterThanOrEqualTo(lastSyncDate);
        }

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

        List<ImMessage> list = new ArrayList<ImMessage>();
        List<ImMessage> historyMsg = imMessageMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        for(ImMessage message : historyMsg){
            if(message.getMessageType().equals(APIConstants.Message_Type.Audio)){
                message.setContent(EmojiParser.parseToUnicode(message.getContent()));
                list.add(message);
            }
        }

        return list;
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

    @Override
    public ImMessage getMessage(Integer messageId) {
        return imMessageMapper.selectByPrimaryKey(messageId);
    }
}
