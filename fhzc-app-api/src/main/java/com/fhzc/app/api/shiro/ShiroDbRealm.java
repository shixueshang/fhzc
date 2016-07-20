package com.fhzc.app.api.shiro;

import com.fhzc.app.api.service.UserService;
import com.fhzc.app.dao.mybatis.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * shiro权限认证
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.getUserByLogin(token.getUsername());

        // 认证缓存信息
       return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }
}
