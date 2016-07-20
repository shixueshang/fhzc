package com.fhzc.app.api.controller;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lihongde on 2016/7/20 17:21
 */
@Controller
public class LoginController extends BaseController {

    /**
     * @return
     */
    @RequestMapping(value = "/api/auth/")
    public ApiJsonResult loginWithoutPwd(){

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiJsonResult loginPost(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "用户名/密码不能为空");
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
        } catch (UnknownAccountException e) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "账号不存在");
        } catch (IncorrectCredentialsException e) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "密码错误");
        } catch (RuntimeException e) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "未知错误,请联系管理员");
        }
    }
}
