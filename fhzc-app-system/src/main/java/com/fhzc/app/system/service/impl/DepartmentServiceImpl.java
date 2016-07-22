package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.DepartmentMapper;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.DepartmentExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.DepartmentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lihongde on 2016/7/21 13:09
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public PageableResult<Map<String, Object>> findPageDepts(int page, int size) {
        DepartmentExample example = new DepartmentExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Department> list = departmentMapper.selectByExampleWithRowbounds(example, rowBounds);

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //构建名称 父级-子级-孙子级
        Department root = this.findRootDept();
        for(Department dept : list){
            Map<String, Object> resultMap = new ConcurrentHashMap<>();
            resultMap.put("id", dept.getDepartmentId());

            if(dept.getParentDeptId() == null){
                resultMap.put("name", dept.getTitle());
                result.add(resultMap);
                continue;
            }

            List<String> names = new ArrayList<String>();
            List<String> reverseNames = recurseMapTree(dept, root, names);
            Collections.reverse(reverseNames);
            StringBuilder sb = new StringBuilder();
            for(String name : reverseNames){
                sb.append(name).append("-");
            }
            resultMap.put("name", sb.substring(0, sb.length() - 1));
            result.add(resultMap);
        }
        return new PageableResult<Map<String, Object>>(page, size, result.size(), result);
    }

    @Override
    public void addOrUpdateDept(Department department) {
        Integer id = department.getDepartmentId();
        if(id == null){
            departmentMapper.insert(department);
        }else{
            departmentMapper.updateByPrimaryKey(department);
        }
    }

    @Override
    public Department getDeparent(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 递归父级部门
     * @param dept
     * @param root
     * @param names
     * @return
     */
    private List<String> recurseMapTree(Department dept, Department root, List<String> names){
        //找父级部门
        Department parent = this.findParent(dept.getParentDeptId());
        if(parent.getParentDeptId() == null){
            names.add(dept.getTitle());
            names.add(parent.getTitle());
        }else {
            names.add(dept.getTitle());
            recurseMapTree(parent, root, names);
        }
        return names;
    }

    /**
     * 获得顶级部门
     * @return
     */
    private Department findRootDept(){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andParentDeptIdIsNull();
        return departmentMapper.selectByExample(example).get(0);
    }

    /**
     * 获得父级部门
     * @param parentId
     * @return
     */
    private Department findParent(Integer parentId){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(parentId);
        return departmentMapper.selectByExample(example).get(0);
    }

    private List<Department> findChildrenByParent(Integer parentId){
        return  departmentMapper.findChildrenByParent(parentId);
    }
}
