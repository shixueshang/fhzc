package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.MessageService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.FileUtils;
import com.fhzc.app.api.tools.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.mybatis.model.ImMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lihongde on 2016/7/13.
 */
@Controller
public class MessageController extends BaseController {

    @Resource
    private MessageService messageService;

    /**
     * 发送消息
     * @param toUserId  接收人
     * @param text  内容
     * @param type  消息类型
     * @param duration  语音长度
     * @return
     */
    @RequestMapping(value = "/api/message/publish", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult publishMessage(Integer toUserId, String text, String type, String duration){

        Integer userId = 1;
            ImMessage message = new ImMessage();
            message.setUserId(userId);
            message.setToUserId(toUserId);
            if(type.equals(APIConstants.Message_Type.Image)){
                String savePath = TextUtils.getConfig(APIConstants.CONFIG_KEY_IMAGE_SAVE_PATH, this);
                List<String> imageList = FileUtils.saveFilesToDisk(request, savePath);
                if (imageList.size() > 0) {
                    text = basePath + savePath +  imageList.get(0);
                }
            }else if(type.equals(APIConstants.Message_Type.Audio)){
                message.setDuration(duration);
            }
            message.setSessionId(UUID.randomUUID().toString());
            message.setMessageType(type);
            message.setContent(text);
            message.setSendTime(new Date());
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, message);
    }

    /**
     * 轮询消息， 获得指定时间之后发给我的未读消息
     * @param version
     * @return
     */
    @RequestMapping(value = "/api/message/yapull",  method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult yapull(long version){
        Integer userId = 1;
        Map<String, Object> result = new ConcurrentHashMap<String, Object>();
        result.put("version", new Date().getTime());
        Date lastSyncDate = new Date(version * 1000L);

        List<Map<String ,Object>> newMessages = newMessages(userId, lastSyncDate);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    private List<Map<String ,Object>> newMessages(Integer userId, Date lastSyncDate){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<ImMessage> messages = messageService.getUnreadMessages(userId, lastSyncDate);

        return list;
    }
}
