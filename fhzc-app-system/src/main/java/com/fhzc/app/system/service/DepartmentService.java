package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.Dept;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.Tree;

import java.util.List;

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
    PageableResult<Dept> findPageDepartments(int page, int size);

    /**
     * 获得机构树
     * @return
     */
    List<Tree> getDepartmentTree();

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
    Department getDepartment(Integer id);

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
    List<Tree> findDeptByParent(Integer parentId);

    /**
     * 获得顶级机构
     * @return
     */
    Department findRootDept();

    /**
     * 根据父级id查询下级机构
     * @param parentId
     * @return
     */
    List<Department> findChildren(Integer parentId);

    /**
     * 根据父级查询所有子级机构
     * @param parentId
     * @return
     */
    List<Department> findAllChildren(Integer parentId);

    /**
     * 获得机构id
     * @param name
     * @return
     */
    Department getDeparent(String name);
    
    /**
     * 获得所有部门
     * @return
     */
    List<Department> findAllDepartment();
}
