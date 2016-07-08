package com.fhzc.app.system.controller.admin;

import com.fhzc.app.system.controller.BaseController;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by lihongde on 2016/7/6 12:16
 */
@Controller
public class LoginController extends BaseController {

    /**
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView loginPost(String username, String password) {
        ModelAndView mav = new ModelAndView();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            mav.setViewName("index");
            mav.addObject("error", "用户名/密码不能为空");
            return mav;
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
            mav.setViewName("system/home");
            return mav;
        } catch (UnknownAccountException e) {
            mav.setViewName("index");
            mav.addObject("error", "账号不存在");
            return mav;
        } catch (IncorrectCredentialsException e) {
            mav.setViewName("index");
            mav.addObject("error", "密码错误");
            return mav;
        } catch (RuntimeException e) {
            mav.setViewName("index");
            mav.addObject("error", "未知错误，请联系管理员");
            return mav;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
