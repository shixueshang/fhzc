package com.fhzc.app.system.controller.admin;

import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.NoticeService;
import com.fhzc.app.system.service.PushTokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

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

    @RequestMapping(value = "/pub", method = RequestMethod.GET)
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("system/notice/add");
        mav.addObject("url", "system/notice");
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("system/notice/list");

        return mav;
    }

    /**
     * 发布消息
     * @param systemNotice
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addOrUpdate(SystemNotice systemNotice){

        if(systemNotice.getId() == null){
            preHandle(systemNotice);
        }else{
            //删除原来system_notice_record表的记录
            noticeService.deleteByNoticeId(systemNotice.getId());
            preHandle(systemNotice);
        }
        systemNotice.setPublishTime(new Date());
        noticeService.addOrUpdate(systemNotice);

        return new AjaxJson(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        return new AjaxJson(true);
    }


    private void preHandle(SystemNotice systemNotice){
        String channel = systemNotice.getPushChannel();
        String[] channelArr = channel.split(",");
        for(String cha : channelArr){
            if(cha.equals(Const.PUSH_CHANNEL.SYSTEM.toString())){
                doHandleSystemNotice(systemNotice);
            }
            if(cha.equals(Const.PUSH_CHANNEL.SMS.toString())){
                doHandleSystemSMS(systemNotice);
            }
            if(cha.equals(Const.PUSH_CHANNEL.MESSAGE.toString())){
                doHandleSystemMessage(systemNotice);
            }
        }
    }

    /**
     * 处理系统通知
     * @param systemNotice
     */
    private void doHandleSystemNotice(SystemNotice systemNotice){

    }

    /**
     * 处理发送短信
     * @param systemNotice
     */
    private void doHandleSystemSMS(SystemNotice systemNotice){

    }

    /**
     * 处理消息推送
     * @param systemNotice
     */
    private void doHandleSystemMessage(SystemNotice systemNotice){

    }

}
