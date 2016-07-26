package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.DepartmentMapper;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.DepartmentExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
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

        List<Map<String, Object>> result = this.buildDepts(list);

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
        Department department = this.getDeparent(id);
        department.setStatus(Const.Data_Status.DATA_DELETE);
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public List<Map<String, Object>> findDeptByParent(Integer parentId) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andParentDeptIdEqualTo(parentId);
        List<Department> parentDepts = departmentMapper.selectByExample(example);

        Department parent = this.getDeparent(parentId);

        return buildChildren(parentDepts, parent);
    }

    /**
     * 查询所有子机构，构建名称 父级-子级-孙子级
     * @param list
     * @return
     */
    private List<Map<String, Object>> buildChildren(List<Department> list, Department parent){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for(Department dept : list){
            Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
            resultMap.put("id", dept.getDepartmentId());

            if(dept.getLeaf() == Const.YES_OR_NO.YES){
                continue;
            }else{
                List<String> names = new ArrayList<String>();
                names.add(parent.getTitle());
                names.add(dept.getTitle());
                recurseChildren(dept, names, data, parent);
            }
            resultMap.put("name",parent.getTitle() + "-" + dept.getTitle());
            data.add(resultMap);
        }
        return data;
    }


    private List<String> recurseChildren(Department department, List<String> names, List<Map<String, Object>> data, Department pDept){
        List<Department> children = this.getChildrens(department.getDepartmentId());
        for(Department child : children){
            Map<String ,Object> map = new ConcurrentHashMap<String,Object>();
            map.put("id", child.getDepartmentId());
            if(child.getLeaf() == Const.YES_OR_NO.YES){
                Department root = this.findRootDept();
                if(root.getTitle().equals(pDept.getTitle())){
                    map.put("name",pDept.getTitle() + "-" + department.getTitle() + "-" + child.getTitle());
                }else{
                    map.put("name",root.getTitle() + "-" + pDept.getTitle() + "-" + department.getTitle() + "-" + child.getTitle());
                }

                names.add(child.getTitle());
                data.add(map);
            }else{
                names.add(child.getTitle());
                StringBuilder sb = new StringBuilder();
                for(String childName : names){
                    sb.append(childName).append("-");
                }
                map.put("name", sb.substring(0, sb.length() - 1));
                data.add(map);
                recurseChildren(child, names, data, department);
            }
        }
        return names;
    }

    private List<Department> getChildrens(Integer parentId){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andParentDeptIdEqualTo(parentId);
        return  departmentMapper.selectByExample(example);
    }

    /**
     * 构建名称 父级-子级-孙子级
     * @param list
     * @return
     */
    private List<Map<String, Object>> buildDepts(List<Department> list){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Department root = this.findRootDept();
        for(Department dept : list){
            Map<String, Object> resultMap = new ConcurrentHashMap<>();
            resultMap.put("id", dept.getDepartmentId());
            resultMap.put("title", dept.getTitle());

            if(dept.getParentDeptId() == null){
                resultMap.put("name", dept.getTitle());
            }else{
                List<String> names = new ArrayList<String>();
                List<String> reverseNames = recurseMapTree(dept, root, names);
                Collections.reverse(reverseNames);
                StringBuilder sb = new StringBuilder();
                for(String name : reverseNames){
                    sb.append(name).append("-");
                }
                resultMap.put("name", sb.substring(0, sb.length() - 1));
            }
            data.add(resultMap);
        }
        return data;
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
        Department parent = this.getDeparent(dept.getParentDeptId());
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
    @Override
    public Department findRootDept(){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andParentDeptIdIsNull();
        return departmentMapper.selectByExample(example).get(0);
    }

    @Override
    public List<Department> findChildren(Integer parentId) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andParentDeptIdEqualTo(parentId);
        return departmentMapper.selectByExample(example);
    }

    @Override
    public Department getDeparent(String name) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(name);
        return departmentMapper.selectByExample(example).get(0);
    }

}
