package com.fhzc.app.system.service;

import com.fhzc.app.system.mybatis.model.SystemAdminModule;
import com.fhzc.app.system.mybatis.model.SystemModule;

import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:53
 */
public interface AdminModuleService {

    SystemModule getSystemModule(Integer id);

    /**
     * 查询用户权限
     * @param uid
     * @return
     */
    List<SystemAdminModule> findModulesByAdminId(Integer uid);

    List<SystemModule> findAllModules();
}
