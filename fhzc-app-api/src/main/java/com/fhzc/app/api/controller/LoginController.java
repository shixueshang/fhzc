package com.fhzc.app.api.controller;

import com.fhzc.app.api.exception.NeedLoginRequestException;
import com.fhzc.app.api.service.UserService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/20 17:21
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 未验证身份用户登录
     * @param identity  证件类型
     * @param identityNum   证件号码
     * @param phoneNum  手机号
     * @param verifyCode    验证码
     * @return
     */
    @RequestMapping(value = "/api/auth/login/newuser", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult loginWithoutPwd(Integer identity, String identityNum, String phoneNum, String verifyCode){
        User user = userService.checkUserExists(identity, identityNum);
        if(user != null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, identityNum);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "没有找到用户信息");
    }

    /**
     * 已注册用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/api/auth/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ApiJsonResult login(String username, String password) {

        String method = request.getMethod();
        if("GET".equals(method.toUpperCase())){
            throw  new NeedLoginRequestException(APIConstants.MESSAGE_NEED_LOGIN);
        }

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "用户名/密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            subject.login(token);
            User user = userService.getUserByLogin(username);
            Session session = subject.getSession(true);
            session.setAttribute("user", user);

            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
        } catch (UnknownAccountException e) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "账号不存在");
        } catch (IncorrectCredentialsException e) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "密码错误");
        } catch (RuntimeException e) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "未知错误,请联系管理员");
        }
    }

    /**
     * 设置密码
     * @param identity
     * @param identityNum
     * @param password
     * @param confirmPwd
     * @return
     */
    @RequestMapping(value = "/api/auth/login/setPwd", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult setPassword(Integer identity, String identityNum, String password, String confirmPwd){
        User user = userService.checkUserExists(identity, identityNum);
        user.setPassword(DigestUtils.md5Hex(password));
        userService.updateUser(user);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }
}
