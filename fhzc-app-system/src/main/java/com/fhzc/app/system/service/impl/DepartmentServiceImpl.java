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
import java.util.List;

/**
 * Created by lihongde on 2016/7/21 13:09
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public PageableResult<Department> findPageDepts(int page, int size) {
        DepartmentExample example = new DepartmentExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Department> list = departmentMapper.selectByExampleWithRowbounds(example, rowBounds);

        List<Department> result = new ArrayList<Department>();
        //构建名称 父级-子级-孙子级
        Department root = this.findRootDept();
        for(Department dept : list){
            if(dept.getParentDeptId() == null){
                continue;
            }

        }
        return null;
    }

    private Department findRootDept(){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andParentDeptIdIsNull();
        return departmentMapper.selectByExample(example).get(0);
    }

    private List<Department> findChildrenByParent(Integer parentId){
        return  departmentMapper.findChildrenByParent(parentId);
    }
}
