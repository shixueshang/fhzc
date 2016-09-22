package com.fhzc.app.system.controller.admin;

import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AdminService;
import com.fhzc.app.system.service.SystemLogService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * Created by lihongde on 2016/7/6 12:16
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private AdminService adminService;

    @Resource
    private SystemLogService systemLogService;

    /**
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost(String username, String password) {
        ModelAndView mav = new ModelAndView();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            mav.setViewName("index");
            mav.addObject("error", "用户名/密码不能为空");
            return mav;
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            subject.login(token);
            Admin admin = adminService.findAdminByLoginName(username);
            if(admin != null){
            	if(admin.getStatus().equals(Const.SCORE_VAILD.NOT_VAILD)){
        		   mav.setViewName("index");
                   mav.addObject("error", "账号被冻结，请联系管理员");
                   return mav;
            	}
            }
            Session session = subject.getSession(true);
            session.setAttribute("admin", admin);
            mav.setViewName("system/home");
            systemLogService.addByDescription("登陆", admin);
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
        Admin admin = super.getCurrentUser();
        if(admin == null){
            return "index";
        }
        systemLogService.addByDescription("退出登陆", admin);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
