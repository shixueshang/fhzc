package com.fhzc.app.api.controller;

import com.fhzc.app.api.exception.BadRequestException;
import com.fhzc.app.api.exception.NeedLoginRequestException;
import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.TextUtils;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/20 17:21
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PlannerService plannerService;

    @Resource
    private CustomerService customerService;

    @Resource
    private PushTokenService pushTokenService;

    @Resource
    private VerifyCodeService verifyCodeService;


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

        if(!verifyCodeService.checkVerifyCode(phoneNum, verifyCode)){
            throw new BadRequestException("验证码输入错误");
        }

        User user = userService.checkUserExists(identity, identityNum);
        if(user == null){
            throw new BadRequestException("没有找到用户信息");
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, identityNum);
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid",user.getUid());
            map.put("realname",user.getRealname());
            map.put("role",user.getLoginRole());
            map.put("pollingTime", TextUtils.getConfig(Const.MESSAGE_POLLING_TIME, this));

            if (user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
                Planner planner = plannerService.getPlannerByUid(user.getUid());
                map.put("plannerId", planner.getId());
            }

            if (user.getLoginRole().equals(Const.USER_ROLE.CUSTOMER)) {
                Customer customer= customerService.getCustomerByUid(user.getUid());
                map.put("customerId", customer.getCustomerId());
            }


                return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, map);
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

    /**
     * 修改密码之前校验原密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/api/password/preModify", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult preModify(String password){
        User user = getCurrentUser();
        String md5Pwd = DigestUtils.md5Hex(password);
        if(!user.getPassword().equals(md5Pwd)){
            throw new BadRequestException("密码输入错误");
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }


    /**
     * 修改密码
     * @param newPwd  新密码
     * @param confirmPwd  确认密码
     * @return
     */
    @RequestMapping(value = "/api/password/reset", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult modify(String newPwd, String confirmPwd) {

        User user = getCurrentUser();

        if(!newPwd.equals(confirmPwd)){
            throw new BadRequestException("两次输入的密码不一致");
        }
        user.setPassword(DigestUtils.md5Hex(newPwd));
        userService.updateUser(user);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }


    /**
     * 忘记密码
     * @param login    登录名
     * @param newPwd    新密码
     * @param confirmPwd    确认新密码
     * @return
     */
    @RequestMapping(value = "/api/password/forget", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult forget(String login, String newPwd, String confirmPwd){
        User user = userService.getUserByLogin(login);
        if(!newPwd.equals(confirmPwd)){
            throw new BadRequestException("两次输入的密码不一致");
        }
        user.setPassword(DigestUtils.md5Hex(newPwd));
        userService.updateUser(user);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 登陆后绑定设备
     * @param token 设备唯一标识
     * @param allowPush 是否允许推送消息 1允许 0不允许
     * @param deviceType 设备类型
     * @return
     */
    @RequestMapping(value = "/api/user/binding", method =  RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult bindToken(String token, Integer allowPush, String deviceType){

        User user = getCurrentUser();
        //判断是否绑定了其他设备，如果是则解绑
        List<PushToken> list = pushTokenService.getPushToken(user.getUid());
        for(PushToken pushToken : list){
            pushTokenService.deleteByUserId(pushToken.getUserId());
        }

        PushToken record = new PushToken();
        record.setUserId(user.getUid());
        record.setDeviceType(deviceType);
        record.setDeviceToken(token);
        record.setAllowPush(allowPush);
        record.setAllowSound(Const.YES_OR_NO.YES);
        record.setBindDate(new Date());
        record.setIsDelete(Const.Data_Status.DATA_NORMAL);
        pushTokenService.bindToken(record);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @param login 登录名，只在忘记密码是需要传
     * @return
     */
    @RequestMapping(value="/api/auth/sms",method =  RequestMethod.POST )
    @ResponseBody ApiJsonResult sendSmsCode(String mobile, String login)  {

        User user = getCurrentUser();
        if(mobile == null || mobile.length() == 0){
            throw new BadRequestException("手机号不能为空");
        }

        if(user != null){
            if(!userService.checkMobileExists(mobile, user)){
                throw new BadRequestException("该手机号不存在");
            }
        }

        if(login != null){
            User u = userService.getUserByLogin(login);
            if(u == null){
                throw new BadRequestException("登录名输入错误");
            }
            if(!mobile.equals(u.getMobile())){
                throw new BadRequestException("该手机号不存在");
            }
        }

        VerifyCode verifyCode = verifyCodeService.sendNewVerifyCode(mobile);

        logger.debug("mobile=" + mobile + " verifyCode=" + verifyCode.getCodeValue());

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, verifyCode.getCodeValue());
    }

    /**
     * 获得设备信息
     * @return
     */
    @RequestMapping(value = "/api/device/info", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult deviceInfo(DeviceInfo deviceInfo){

        User user = super.getCurrentUser();
        deviceInfo.setUserId(user.getUid());
        deviceInfo.setCreateTime(new Date());
        pushTokenService.collectDeviceInfo(deviceInfo);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }
}
