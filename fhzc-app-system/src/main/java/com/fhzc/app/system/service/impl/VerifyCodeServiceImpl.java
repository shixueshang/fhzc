package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.VerifyCodeMapper;
import com.fhzc.app.dao.mybatis.model.VerifyCode;
import com.fhzc.app.dao.mybatis.model.VerifyCodeExample;
import com.fhzc.app.dao.mybatis.thirdparty.sms.SMSTemplate;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.service.VerifyCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/9 19:38
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private static final long VERIFY_CODE_EXPRIRE_SECONDS = 5 * 60 * 1000;

    @Resource
    private VerifyCodeMapper verifyCodeMapper;

    @Override
    public VerifyCode sendNewVerifyCode(String mobile) {
        VerifyCode verifyCode = null;

        VerifyCodeExample example = new VerifyCodeExample();
        VerifyCodeExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        Date date = new Date(new Date().getTime() - VERIFY_CODE_EXPRIRE_SECONDS);
        criteria.andSendDateGreaterThanOrEqualTo(date);

        //重用前5分钟的验证码
        List<VerifyCode> verifyCodeList = verifyCodeMapper.selectByExample(example);
        if (verifyCodeList.size() == 0) {
            // 如果没有，创建新的验证码
            verifyCode = new VerifyCode();
            verifyCode.setMobile(mobile);
            verifyCode.setCodeValue(TextUtils.generateRandomString(6));
            verifyCode.setSendDate(new Date());
            verifyCodeMapper.insertSelective(verifyCode);
        } else {
            verifyCode = verifyCodeList.get(0);
        }

        String content = "您好，您的验证码是: "+verifyCode.getCodeValue()+"【复华资产】,在5分钟之内有效。如非本人操作请忽略本短信。";
        SMSTemplate smsTemplate = new SMSTemplate(TextUtils.getConfig(Const.SMS_PARAM.SMS_USERNAME, this),TextUtils.getConfig(Const.SMS_PARAM.SMS_PASSWORD, this), TextUtils.getConfig(Const.SMS_PARAM.SMS_APPIKEY, this), content);
        smsTemplate.sendTemplateSMS(mobile);
        return verifyCode;
    }

    @Override
    public boolean checkVerifyCode(String mobile, String verifyCode) {
        VerifyCodeExample example = new VerifyCodeExample();
        VerifyCodeExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        criteria.andCodeValueEqualTo(verifyCode);
        Date date = new Date(new Date().getTime() - VERIFY_CODE_EXPRIRE_SECONDS);
        criteria.andSendDateGreaterThanOrEqualTo(date);
        return verifyCodeMapper.countByExample(example) > 0;
    }
}
