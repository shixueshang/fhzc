package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.VerifyCode;

/**
 * Created by lihongde on 2016/8/9 19:36
 */
public interface VerifyCodeService {

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    VerifyCode sendNewVerifyCode(String mobile);

    /**
     * 校验验证码是否正确
     * @param mobile
     * @param verifyCode
     * @return
     */
    boolean checkVerifyCode(String mobile, String verifyCode);
}
