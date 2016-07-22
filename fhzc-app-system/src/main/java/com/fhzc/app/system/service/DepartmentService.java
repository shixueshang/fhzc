package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.Map;

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
    PageableResult<Map<String, Object>> findPageDepts(int page, int size);

    /**
     * 添加或修改部门
     * @param department
     */
    void addOrUpdateDept(Department department);
}
