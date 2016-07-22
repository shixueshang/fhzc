package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/21 13:07
 */
public interface DepartmentService {

    /**
     * 查询所有机构信息
     * @param page
     * @param size
     * @return
     */
    PageableResult<Map<String, Object>> findPageDepts(int page, int size);

    /**
     * 添加或修改机构
     * @param department
     */
    void addOrUpdateDept(Department department);

    /**
     * 获得机构信息
     * @param id
     * @return
     */
    Department getDeparent(Integer id);

    /**
     * 删除(逻辑)
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据父级id获得所有下级机构
     * @param parentId
     * @return
     */
    List<Map<String, Object>> findDeptByParent(Integer parentId);

}
