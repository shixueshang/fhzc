package com.fhzc.app.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.PushToken;
import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.thirdparty.sms.SMSTemplate;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.NoticeService;
import com.fhzc.app.system.service.PushTokenService;
import com.fhzc.app.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/17 14:35
 */
@Controller
@RequestMapping(value = "system/notice")
public class NoticeController extends BaseController {

    @Resource
    private NoticeService noticeService;

    @Resource
    private PushTokenService pushTokenService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/pub", method = RequestMethod.GET)
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("system/notice/add");
        mav.addObject("url", "system/notice");
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询消息列表")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("system/notice/list");
        PageableResult<SystemNotice> pageableResult = noticeService.findPageNotices(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        List<SystemNotice> list = new ArrayList<SystemNotice>();
        for(SystemNotice  systemNotice : pageableResult.getItems()){
            String channel = systemNotice.getPushChannel();
            String[] channels = channel.split(",");
            StringBuilder sb = new StringBuilder();
            for(String ch : channels){
                String chName = super.getDicName(Integer.parseInt(ch), Const.DIC_CAT.PUSH_CHANNEL);
                sb.append(chName).append(",");
            }
            systemNotice.setPushChannel(sb.substring(0, sb.length() - 1));
            list.add(systemNotice);
        }
        mav.addObject("notices", list);
        mav.addObject("url", "system/notice");
        return mav;
    }

    /**
     * 发布消息
     * @param systemNotice
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "发布或编辑消息")
    public AjaxJson addOrUpdate(SystemNotice systemNotice){
        systemNotice.setPublishTime(new Date());
        noticeService.addOrUpdate(systemNotice);
        if(systemNotice.getId() != null){
            noticeService.deleteRecordByNoticeId(systemNotice.getId());
        }
        preHandle(systemNotice);
        return new AjaxJson(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "删除消息")
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        noticeService.deleteRecordByNoticeId(id);
        noticeService.delete(id);
        return new AjaxJson(true);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("system/notice/add");
        mav.addObject("notice", JSON.toJSON(noticeService.getSystemNotice(id)));
        mav.addObject("url", "system/notice");
        return mav;
    }

    private void preHandle(SystemNotice systemNotice){
        String channel = systemNotice.getPushChannel();
        String[] channelArr = channel.split(",");
        for(String cha : channelArr){
            if(cha.equals(Const.PUSH_CHANNEL.SYSTEM.toString())){
                this.doHandleSystemNotice(systemNotice, Const.PUSH_CHANNEL.SYSTEM);
            }
            if(cha.equals(Const.PUSH_CHANNEL.SMS.toString())){
                this.doHandleSystemSMS(systemNotice, Const.PUSH_CHANNEL.SMS);
            }
            if(cha.equals(Const.PUSH_CHANNEL.MESSAGE.toString())){
                this.doHandleSystemMessage(systemNotice, Const.PUSH_CHANNEL.MESSAGE);
            }
        }
    }

    /**
     * 处理系统通知
     * @param systemNotice
     * @param channel
     */
    private void doHandleSystemNotice(SystemNotice systemNotice, Integer channel){
        List<PushToken> list = pushTokenService.getAllTokens();
        for(PushToken pushToken : list){
            this.addNoticeRecord(systemNotice, channel, pushToken.getUserId());
        }
    }

    /**
     * 处理发送短信
     * @param systemNotice
     * @param channel
     */
    private void doHandleSystemSMS(SystemNotice systemNotice, Integer channel){
        List<User> list = userService.findAllUsers();
        for(User user : list){
            this.addNoticeRecord(systemNotice, channel, user.getUid());
        }
    }

    /**
     * 处理消息推送
     * @param systemNotice
     * @param channel
     */
    private void doHandleSystemMessage(SystemNotice systemNotice, Integer channel){
        List<PushToken> list = pushTokenService.getAllTokens();
        for(PushToken pushToken : list){
            this.addNoticeRecord(systemNotice, channel, pushToken.getUserId());
        }
    }

    private void addNoticeRecord(SystemNotice systemNotice, Integer channel, Integer userId){
        SystemNoticeRecord record = new SystemNoticeRecord();
        record.setNoticeId(systemNotice.getId());
        record.setUserId(userId);
        record.setContent(systemNotice.getContent());
        record.setPushChannel(channel);
        record.setPushStatus(systemNotice.getPushStatus());
        noticeService.addOrUpdateNoticeRecord(record);
    }


    /**
     * 手动推送
     * @return
     */
    @RequestMapping(value = "/push/{id}", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "推送消息")
    public AjaxJson push(@PathVariable(value = "id") Integer noticeId) {
        List<SystemNoticeRecord> list = noticeService.findRecordByNoticeId(noticeId);
        try{
            for(SystemNoticeRecord record : list){
                if(record.getPushChannel() == Const.PUSH_CHANNEL.SYSTEM){
                    continue;
                }
                if(record.getPushChannel() == Const.PUSH_CHANNEL.MESSAGE){
                    pushTokenService.pushMessageToUser(record.getUserId(), record.getContent());
                }
                if(record.getPushChannel() == Const.PUSH_CHANNEL.SMS){
                    User user = userService.getUser(record.getUserId());
                    SMSTemplate smsTemplate = new SMSTemplate(TextUtils.getConfig(Const.SMS_PARAM.SMS_USERNAME, this),TextUtils.getConfig(Const.SMS_PARAM.SMS_PASSWORD, this), TextUtils.getConfig(Const.SMS_PARAM.SMS_APPIKEY, this), record.getContent());
                    smsTemplate.sendTemplateSMS(user.getMobile());
                }
                if(record.getPushChannel() == Const.PUSH_CHANNEL.EMAIL){
                    continue;
                }

                //更新状态为已推送
                record.setPushStatus(Const.PUSH_STATUS.PUSHED);
                noticeService.addOrUpdateNoticeRecord(record);
                SystemNotice notice = noticeService.getSystemNotice(noticeId);
                notice.setPushStatus(Const.PUSH_STATUS.PUSHED);
                noticeService.addOrUpdate(notice);

            }
        }catch (Exception e){
            logger.error("推送失败!");
            e.printStackTrace();
        }

        return new AjaxJson(true);
    }

}
