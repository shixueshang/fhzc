package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.SystemAdminModuleMapper;
import com.fhzc.app.dao.mybatis.inter.SystemModuleMapper;
import com.fhzc.app.dao.mybatis.model.SystemAdminModule;
import com.fhzc.app.dao.mybatis.model.SystemAdminModuleExample;
import com.fhzc.app.dao.mybatis.model.SystemModule;
import com.fhzc.app.dao.mybatis.model.SystemModuleExample;
import com.fhzc.app.system.service.AdminModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:56
 */
@Service
public class AdminModuleServiceImpl implements AdminModuleService {

    @Resource
    private SystemAdminModuleMapper systemAdminModuleMapper;

    @Resource
    private SystemModuleMapper systemModuleMapper;

    @Override
    public SystemModule getSystemModule(Integer id) {
        return systemModuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemAdminModule> findModulesByAdminId(Integer uid) {
        SystemAdminModuleExample example = new SystemAdminModuleExample();
        SystemAdminModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAdminIdEqualTo(uid);
        return systemAdminModuleMapper.selectByExample(example);
    }

    @Override
    public List<SystemModule> findAllModules() {
        SystemModuleExample example = new SystemModuleExample();
        return systemModuleMapper.selectByExample(example);
    }
}
