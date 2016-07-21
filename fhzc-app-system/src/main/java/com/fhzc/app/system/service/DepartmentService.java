package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/7/21 13:07
 */
public interface DepartmentService {

    /**
     * 查询所有部门信息
     * @param page
     * @param size
     * @return
     */
    PageableResult<Department> findPageDepts(int page, int size);
}
