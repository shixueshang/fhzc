package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.SystemModule;
import com.fhzc.app.dao.mybatis.model.SystemRoleModule;

import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:53
 */
public interface AdminModuleService {

    SystemModule getSystemModule(Integer id);

    /**
     * 根据角色查询权限
     * @param roleId
     * @return
     */
    List<SystemRoleModule> findModulesByAdminRole(Integer roleId);

    List<SystemModule> findAllModules();
}
