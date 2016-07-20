package com.fhzc.app.system.commons.shiro;

import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.model.SystemAdminModule;
import com.fhzc.app.dao.mybatis.model.SystemModule;
import com.fhzc.app.system.service.AdminModuleService;
import com.fhzc.app.system.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro权限认证
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Resource
    private AdminService adminService;

    @Resource
    private AdminModuleService adminModuleService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Admin admin = adminService.findAdminByLoginName(token.getUsername());
        // 账号不存在
        if (admin == null) {
            return null;
        }

        // 认证缓存信息
        return new SimpleAuthenticationInfo(admin, admin.getPassword().toCharArray(), getName());

    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Admin shiroAdmin = (Admin) principals.getPrimaryPrincipal();
        Set<String> urlSet = new HashSet<String>();
        //超级管理员获得所有权限
        if(shiroAdmin.getLogin().equals("admin")){
            List<SystemModule> modules = adminModuleService.findAllModules();
            for(SystemModule module : modules){
                if (StringUtils.isNoneBlank(module.getUrl())) {
                    urlSet.add(module.getUrl());
                }
            }
        }else{
            List<SystemAdminModule> roleResourceList = adminModuleService.findModulesByAdminId(shiroAdmin.getId());
            for (SystemAdminModule systemAdminModule : roleResourceList) {
                SystemModule module = adminModuleService.getSystemModule(systemAdminModule.getModuleId());
                if (StringUtils.isNoneBlank(module.getUrl())) {
                    urlSet.add(module.getUrl());
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;
    }
}
