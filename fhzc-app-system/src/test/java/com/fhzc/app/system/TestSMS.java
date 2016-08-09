package com.fhzc.app.system;

import com.fhzc.app.system.context.Base;
import com.fhzc.app.system.service.VerifyCodeService;
import org.junit.*;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/8/9 20:03
 */
public class TestSMS extends Base {

    @Resource
    private VerifyCodeService verifyCodeService;

    @org.junit.Test
    public void  test(){
        verifyCodeService.sendNewVerifyCode("13810465136");
    }
}
