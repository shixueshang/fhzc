package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.PushTokenMapper;
import com.fhzc.app.dao.mybatis.model.PushToken;
import com.fhzc.app.dao.mybatis.model.PushTokenExample;
import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.AndroidNotification;
import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.PushClient;
import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.android.AndroidUnicast;
import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.ios.IOSUnicast;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.service.PushTokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/17 18:49
 */
@Service
public class PushTokenServiceImpl implements PushTokenService {

    @Resource
    private PushTokenMapper pushTokenMapper;

    private PushClient client = new PushClient();

    @Override
    public List<PushToken> getAllTokens() {
        PushTokenExample example = new PushTokenExample();
        return pushTokenMapper.selectByExample(example);
    }

    @Override
    public void pushMessageToUser(Integer toUserId, String content) throws Exception {
        PushTokenExample pushTokenExample = new PushTokenExample();
        PushTokenExample.Criteria criteria = pushTokenExample.createCriteria();
        criteria.andUserIdEqualTo(toUserId);
        criteria.andAllowPushEqualTo(Const.YES_OR_NO.YES);

        List<PushToken> list = pushTokenMapper.selectByExample(pushTokenExample);
        for (PushToken pushToken : list) {
            if(Const.DEVICE_TYPE.IOS.equals(pushToken.getDeviceType())){
                IOSUnicast unicast = new IOSUnicast(Const.UMENG_PUSH_APPKEY,Const.UMENG_PUSH_APP_MASTER_SECRET);
                unicast.setDeviceToken(pushToken.getDeviceToken());
                unicast.setAlert(content);
                unicast.setBadge(1);
                if(pushToken.getAllowSound() == Const.YES_OR_NO.YES){
                    unicast.setSound("default");
                }
                unicast.setProductionMode();
                client.send(unicast);

            }else if(Const.DEVICE_TYPE.ANDROID.equals(pushToken.getDeviceType())){
                AndroidUnicast unicast = new AndroidUnicast(Const.UMENG_PUSH_APPKEY_ANDROID,Const.UMENG_PUSH_APP_MASTER_SECRET_ANDROID);
                unicast.setDeviceToken(pushToken.getDeviceToken());
                unicast.setTitle(Const.SYSTEM_NAME);
                unicast.setText(content);
                unicast.setTicker(Const.SYSTEM_NAME);
                unicast.goAppAfterOpen();
                unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
                unicast.setProductionMode();
                client.send(unicast);
            }
        }
    }
}
