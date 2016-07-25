package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.AdminRole;

import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:13
 */
public interface AdminRoleService {
    /**
     * 查询角色列表
     *
     * @param page
     * @param size
     */
    void findPageRole(int page, int size);

    List<AdminRole> getAllRoles();

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    AdminRole findRoleById(Integer id);
}
