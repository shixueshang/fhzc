package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.AdminRole;
import com.fhzc.app.dao.mybatis.page.PageableResult;

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
    PageableResult<AdminRole> findPageRole(int page, int size);

    List<AdminRole> getAllRoles();

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    AdminRole findRoleById(Integer id);

    /**
     * 添加或修改角色
     * @param role
     */
    void addOrUpdateRole(AdminRole role);

    /**
     * 删除(禁用)
     * @param id
     */
    void delete(Integer id);
    
    /**
     * 查询角色名称是否存在
     * @param name
     * @return
     */
    boolean isRoleNameExists(String roleName);
}
