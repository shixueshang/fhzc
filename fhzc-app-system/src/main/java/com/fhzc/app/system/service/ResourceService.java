package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.SystemModule;
import com.fhzc.app.dao.mybatis.model.SystemRoleModule;

import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/25 12:06
 */
public interface ResourceService {

    /**
     * 查询所有资源
     * @return
     */
    List<Map<String, Object>> findAllResource();

    /**
     * 获得角色的权限
     * @param roleId
     * @return
     */
    List<SystemRoleModule> findModulesByRole(Integer roleId);

    /**
     * 查找指定级别的资源
     * @param level
     * @return
     */
    List<Map<String, Object>>  findModulesByLevel(Integer level);

    /**
     * 删除指定角色的权限
     * @param roleId
     */
    void deleteModuleByRole(Integer roleId);

    /**
     * 授权
     * @param roleModule
     */
    void addRoleModule(SystemRoleModule roleModule);
}
