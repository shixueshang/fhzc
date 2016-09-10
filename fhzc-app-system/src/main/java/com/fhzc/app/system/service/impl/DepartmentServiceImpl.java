package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.DepartmentMapper;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.DepartmentExample;
import com.fhzc.app.dao.mybatis.model.Dept;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.Tree;
import com.fhzc.app.system.service.DepartmentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongde on 2016/7/21 13:09
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public PageableResult<Dept> findPageDepartments(List<Integer> departments, int page, int size) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        if(departments != null){
            criteria.andDepartmentIdIn(departments);
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Department> list = departmentMapper.selectByExampleWithRowbounds(example, rowBounds);

        List<Dept> result = new ArrayList<Dept>();
        for(Department department : list){
            Dept dept = new Dept();
            dept.setParentId(department.getParentDeptId());
            dept.setId(department.getDepartmentId());
            dept.setName(department.getTitle());
            if(department.getLevel() == 1){
                dept.setHead(department.getTitle());
            }else if(department.getLevel() == 2){
                dept.setArea(department.getTitle());
            }else if(department.getLevel() == 3){
                dept.setArea(this.getDepartment(department.getParentDeptId()).getTitle());
                dept.setCompany(department.getTitle());
            }else{
                dept.setArea(this.getDepartment(this.getDepartment(department.getParentDeptId()).getParentDeptId()).getTitle());
                dept.setCompany(this.getDepartment(department.getParentDeptId()).getTitle());
                dept.setTeam(department.getTitle());
            }
            result.add(dept);
        }

        return new PageableResult<Dept>(page, size, departmentMapper.countByExample(example), result);
    }

    @Override
    public List<Tree> getDepartmentTree() {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Const.Data_Status.DATA_NORMAL);
        return this.buildTree(departmentMapper.selectByExample(example));
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
    public Department getDepartment(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        Department department = this.getDepartment(id);
        department.setStatus(Const.Data_Status.DATA_DELETE);
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public List<Tree> findDeptByParent(Integer parentId) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        if(parentId != Const.ROOT_DEPT_ID){
            criteria.andParentDeptIdEqualTo(parentId);
        }
        return this.buildTree(departmentMapper.selectByExample(example));
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
    public List<Department> findAllChildren(Integer parentId) {
        List<Department> result = new ArrayList<Department>();
        List<Department> departments = this.findChildren(parentId);
        recurseTree(result, departments);
        return result;
    }

    @Override
    public List<Integer> findAllChildrenIds(Integer parentId) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(parentId);
        List<Department> departments = this.findAllChildren(parentId);
        for(Department department : departments){
            result.add(department.getDepartmentId());
        }
        return result;
    }

    private void recurseTree(List<Department> rs, List<Department> departments) {
        for (Department node : departments) {
            rs.add(node);
            if (node.getLeaf() == Const.YES_OR_NO.NO) {
                List<Department> children = this.findChildren(node.getDepartmentId());
                recurseTree(rs, children);
            }
        }
    }

    @Override
    public Department getDeparent(String name) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(name);
        return departmentMapper.selectByExample(example).get(0);
    }
    
    /**
     * 获取所有部门
     */
    @Override
    public List<Department> findAllDepartment() {
        DepartmentExample example = new DepartmentExample();
        return departmentMapper.selectByExample(example);
    }

    private List<Tree> buildTree(List<Department> departments){
        List<Tree> trees = new ArrayList<Tree>();
        for(Department department : departments){
            Tree tree = new Tree();
            tree.setId(department.getDepartmentId());
            tree.setName(department.getTitle());
            tree.setpId(department.getParentDeptId());
            if(department.getParentDeptId() == null){
                tree.setOpen(true);
            }
            tree.setLeaf(department.getLeaf() == 1 ? true : false);
            trees.add(tree);
        }

        return trees;
    }
}
