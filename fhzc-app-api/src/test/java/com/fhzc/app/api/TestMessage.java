package com.fhzc.app.api;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.api.context.Base;
import com.fhzc.app.api.service.MessageService;
import com.fhzc.app.api.service.UserService;
import com.fhzc.app.system.mybatis.model.ImMessage;
import com.fhzc.app.system.mybatis.model.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lihongde on 2016/7/14.
 */
public class TestMessage extends Base {

    @Resource
    private MessageService messageService;

    @Resource
    private UserService userService;

    @Test
    public void sendText(){
        ImMessage message = new ImMessage();
        message.setUserId(1);
        message.setToUserId(2);


        //查询对话历史,确定sessionId
        String sessionId = messageService.hasChatHistory(1, 2);
        if(sessionId == null){
            sessionId = UUID.randomUUID().toString();
        }
        message.setSessionId(sessionId);
        message.setMessageType("text");
        message.setContent("我是张三");
        message.setSendTime(new Date());

        ImMessage result = messageService.sendMessgeToSession(message);

    }

    @Test
    public void yapull(){
        Integer userId = 1;
        Map<String, Object> result = new ConcurrentHashMap<String, Object>();
        result.put("version", new Date().getTime() / 1000);

        List<Map<String ,Object>> newMessages = newMessages(userId, 1468505787);
        result.put("groups", newMessages);

        logger.debug("未读消息 :: " + JSON.toJSONString(result));

    }

    private List<Map<String ,Object>> newMessages(Integer userId, long version){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<ImMessage> messages = messageService.getUnreadMessages(userId, version);
        //找出有多少组,数据格式 sessionId=List<ImMessage>
        Map<String, List<ImMessage>> sessionMap = new ConcurrentHashMap<String, List<ImMessage>>();
        for(int i = 0; i < messages.size(); i++){
            String sessionId = messages.get(i).getSessionId();
            if(sessionMap.get(sessionId) != null){
                sessionMap.get(sessionId).add(messages.get(i));
            }else{
                List<ImMessage> lis = new ArrayList<ImMessage>();
                lis.add(messages.get(i));
                sessionMap.put(sessionId, lis);
            }
        }

        for(Map.Entry<String, List<ImMessage>> entry : sessionMap.entrySet()){
            Map<String, Object> groupMap = new ConcurrentHashMap<String, Object>();
            groupMap.put("sessionId", entry.getKey());
            List<ImMessage> msgInsession = entry.getValue();
            Integer[] party = new Integer[2];
            party[0] = msgInsession.get(0).getUserId();
            party[1] = msgInsession.get(0).getToUserId();
            groupMap.put("party", party);

            List<Map<String, Object>> groupInfo = new ArrayList<Map<String, Object>>();
            for(Integer uid : party){
                Map<String, Object> userMap = new ConcurrentHashMap<String, Object>();
                User user = userService.getUser(uid);
                userMap.put("uid", uid);
                userMap.put("name", user.getRealname());
                userMap.put("avatar", user.getAvatar());
                groupInfo.add(userMap);
            }
            groupMap.put("groupInfo", groupInfo);

            groupMap.put("messages", msgInsession);
            list.add(groupMap);
        }

        return list;
    }
}
