package com.fhzc.app.system.service.impl;

import com.fhzc.app.system.commons.util.Tree;
import com.fhzc.app.system.mybatis.model.AdminRole;
import com.fhzc.app.system.service.AdminRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:55
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Override
    public void findDataGrid(int page, int size) {

    }

    @Override
    public List<Tree> findTree() {
        return null;
    }

    @Override
    public AdminRole findRoleById(Integer id) {
        return null;
    }
}
