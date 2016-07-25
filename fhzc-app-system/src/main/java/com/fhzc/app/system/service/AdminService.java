package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/7/6 20:08
 */
public interface AdminService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Admin findAdminByLoginName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param uid
     * @return
     */
    Admin findAdminById(Integer uid);

    /**
     * 用户列表
     *
     * @param page
     * @param size
     * @return
     */
    PageableResult<Admin> findPageAdmins(int page, int size);

    /**
     * 修改密码
     *
     * @param uid
     * @param pwd
     */
    void updatePwdById(Integer uid, String pwd);
}
