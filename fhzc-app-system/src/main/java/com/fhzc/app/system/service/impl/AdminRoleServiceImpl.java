package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AdminRoleMapper;
import com.fhzc.app.dao.mybatis.model.AdminRole;
import com.fhzc.app.dao.mybatis.model.AdminRoleExample;
import com.fhzc.app.system.service.AdminRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:55
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Override
    public void findPageRole(int page, int size) {

    }

    @Override
    public List<AdminRole> getAllRoles() {
        AdminRoleExample example  = new AdminRoleExample();
        return adminRoleMapper.selectByExample(example);
    }

    @Override
    public AdminRole findRoleById(Integer id) {
        return null;
    }
}
