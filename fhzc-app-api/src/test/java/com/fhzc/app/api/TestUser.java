package com.fhzc.app.api;

import com.fhzc.app.api.context.Base;
import com.fhzc.app.api.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/9/6 14:02
 */
public class TestUser extends Base {

    @Resource
    private UserService userService;

    @Test
    public void test(){
        userService.checkUserExists(1, "150404198809285618");
    }
}
