package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.CustomerService;
import com.fhzc.app.api.service.MessageService;
import com.fhzc.app.api.service.UserService;
import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.*;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.ImMessage;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Resource
    private UserService userService;

    @Resource
    private ProductService productService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ReportService reportService;

    @Resource
    private RightsService rightsService;

    @Resource
    private CustomerService customerService;

    @Resource
    private PushTokenService pushTokenService;

    private static final String SHARE = "share";

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
    public ApiJsonResult publishMessage(Integer toUserId, @RequestParam(required = false) String text, String type, @RequestParam(required = false) String duration) throws Exception {

            User user  = getCurrentUser();
            ImMessage message = new ImMessage();
            message.setUserId(user.getUid());
            message.setToUserId(toUserId);
            if(type.equals(APIConstants.Message_Type.Image)){
                String savePath = TextUtils.getConfig(Const.CONFIG_KEY_IMAGE_SAVE_PATH, this);
                List<String> imageList = FileUtils.saveFilesToDisk(request, savePath);
                if (imageList.size() > 0) {
                    text = basePath + savePath +  imageList.get(0);
                }
            }else if(type.equals(APIConstants.Message_Type.Audio)){
                message.setDuration(duration);
            }
            if(text == null) text = "";
            message.setContent(EmojiParser.parseToAliases(text));
            //查询对话历史,确定sessionId
            String sessionId = messageService.hasChatHistory(user.getUid(), toUserId);
            if(sessionId == null){
                    sessionId = UUID.randomUUID().toString();
            }
            message.setSessionId(sessionId);
            message.setMessageType(type);
            message.setSendTime(new Date());

            ImMessage result = messageService.sendMessgeToSession(message);

            pushTokenService.pushMessageToUser(toUserId, text);

            if(type.equals(APIConstants.Message_Type.Audio)){
                result.setContent("");
            }else{
                result.setContent(EmojiParser.parseToUnicode(result.getContent()));
            }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }

    /**
     * 轮询消息， 获得指定时间之后发给我的未读消息
     * @param version
     * @return
     */
    @RequestMapping(value = "/api/message/yapull",  method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult yapull(long version){
        User user = getCurrentUser();
        Map<String, Object> result = new ConcurrentHashMap<String, Object>();
        result.put("version", new Date().getTime() /1000);
        List<Map<String ,Object>> newMessages = newMessages(user.getUid(), version);
        result.put("groups", newMessages);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }

    private List<Map<String ,Object>> newMessages(Integer userId, long version){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<ImMessage> messages = messageService.getUnreadMessages(userId, version);
        //找出有多少组,数据格式 sessionId=List<ImMessage>
        Map<String, List<ImMessage>> sessionMap = new ConcurrentHashMap<String, List<ImMessage>>();
        for(ImMessage message : messages){
            if(message.getMessageType().equals(APIConstants.Message_Type.Audio)){
                message.setContent("");
            }else{
                message.setContent(EmojiParser.parseToUnicode(message.getContent()));
            }
            String sessionId = message.getSessionId();
            if(sessionMap.get(sessionId) != null){
                sessionMap.get(sessionId).add(message);
            }else{
                List<ImMessage> lis = new ArrayList<ImMessage>();
                lis.add(message);
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
                userMap.put("gender", user.getGender());
                userMap.put("role", user.getLoginRole());
                if(user.getLoginRole() != null && user.getLoginRole().equals(APIConstants.USER_ROIE.CUSTOMER)){
                    Customer customer = customerService.getCustomerByUid(user.getUid());
                    userMap.put("level", user.getLevel() == null ? "" : getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
                }
                userMap.put("name", user.getRealname());
                userMap.put("avatar", basePath + user.getAvatar());
                groupInfo.add(userMap);
            }
            groupMap.put("groupInfo", groupInfo);

            groupMap.put("messages", msgInsession);
            list.add(groupMap);
        }

        return list;
    }

    /**
     * 获得历史消息
     * @param sessionId 群组id
     * @param mid 小于mid的记录
     * @param count 获取记录数
     * @return
     */
    @RequestMapping(value = "/api/message/history", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult messageHistory(String sessionId, Integer mid, Integer count){
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, messageService.findHistoryMessages(sessionId, mid, count));
    }

    /**
     * 获得语音内容
     * @param messageId
     * @return
     */
    @RequestMapping(value = "/api/message/audioContent", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getAudioContent(Integer messageId){
        ImMessage message = messageService.getMessage(messageId);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, message.getContent());
    }

    /**
     * 存储分享信息
     * @param toUserId
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/api/share", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult share(@RequestParam(value="toUserId[]") Integer[] toUserId,
                               @RequestParam(value="id") Integer id,
                               @RequestParam(value="type") String type){
        for (Integer toUser : toUserId) {
            User user = getCurrentUser();
            ImMessage message = new ImMessage();
            message.setUserId(user.getUid());
            message.setToUserId(toUser);
            List<String> shareEle = new ArrayList<>();
            shareEle.add(this.SHARE);
            shareEle.add(type);
            shareEle.add(id.toString());
            shareEle.add(type);
            switch (type){
                case Const.FOCUS_TYPE.PRODUCT:
                    Product product = productService.getProduct(id);
                    shareEle.add(product.getName());
                    shareEle.add(product.getDesc());
                    break;
                case Const.FOCUS_TYPE.ACTIVITY:
                    Activity activity = activityService.getActivity(id);
                    shareEle.add(activity.getName());
                    shareEle.add(activity.getApplyEndTime().toString());
                    shareEle.add(activity.getAddress());
                    break;
                case Const.FOCUS_TYPE.REPORT:
                    Report report = reportService.getReport(id);
                    shareEle.add(report.getName());
                    shareEle.add(report.getSummary());
                    break;
                case Const.FOCUS_TYPE.RIGHTS:
                    Rights rights = rightsService.getRights(id);
                    shareEle.add(rights.getName());
                    shareEle.add(rights.getSpendScore().toString());
                    break;
            }
            String text = ListUtil.listToString(shareEle, Const.SEPRATOR);
            message.setContent(text);
            //查询对话历史,确定sessionId
            String sessionId = messageService.hasChatHistory(user.getUid(), toUser);
            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString();
            }
            message.setSessionId(sessionId);
            message.setMessageType("text");
            message.setSendTime(new Date());

            ImMessage result = messageService.sendMessgeToSession(message);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

}
