package com.fhzc.app.system.service;

import com.fhzc.app.system.commons.util.Tree;
import com.fhzc.app.dao.mybatis.model.AdminRole;

import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:13
 */
public interface AdminRoleService {
    /**
     * 查询权限列表
     *
     * @param page
     * @param size
     */
    void findDataGrid(int page, int size);

    /**
     * 查询权限树
     *
     * @return
     */
    List<Tree> findTree();

    /**
     * 根据id查询权限
     *
     * @param id
     * @return
     */
    AdminRole findRoleById(Integer id);
}
