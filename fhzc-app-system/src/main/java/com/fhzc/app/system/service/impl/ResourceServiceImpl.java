package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.SystemModuleMapper;
import com.fhzc.app.dao.mybatis.inter.SystemRoleModuleMapper;
import com.fhzc.app.dao.mybatis.model.SystemModule;
import com.fhzc.app.dao.mybatis.model.SystemModuleExample;
import com.fhzc.app.dao.mybatis.model.SystemRoleModule;
import com.fhzc.app.dao.mybatis.model.SystemRoleModuleExample;
import com.fhzc.app.system.commons.util.TreeUtil;
import com.fhzc.app.system.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/25 12:07
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private SystemModuleMapper systemModuleMapper;

    @Resource
    private SystemRoleModuleMapper systemRoleModuleMapper;

    @Override
    public List<Map<String, Object>> findAllResource() {
        SystemModuleExample example = new SystemModuleExample();
        List<SystemModule> list = systemModuleMapper.selectByExample(example);

        List<Map<String, Object>> mapModules = new ArrayList<Map<String, Object>>();
        for(SystemModule module : list){
            Map<String ,Object> moduleMap = new HashMap<String, Object>();
            moduleMap.put("id", module.getId());
            moduleMap.put("name", module.getName());
            moduleMap.put("parent_id", module.getParentModuleId());
            moduleMap.put("url", module.getUrl());
            mapModules.add(moduleMap);
        }

        return TreeUtil.buildMapTree(mapModules, "id", "parent_id");
    }

    @Override
    public List<SystemRoleModule> findModulesByRole(Integer roleId) {
        SystemRoleModuleExample example = new SystemRoleModuleExample();
        SystemRoleModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAdminRoleIdEqualTo(roleId);
        return systemRoleModuleMapper.selectByExample(example);
    }
}
