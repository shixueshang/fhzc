package com.fhzc.app.system;

import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.Dept;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.Tree;
import com.fhzc.app.system.context.Base;
import com.fhzc.app.system.service.DepartmentService;
import org.junit.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by lihongde on 2016/7/21 19:03
*/
public class DepartmentTest extends Base {

    @Resource
    private DepartmentService departmentService;

    @org.junit.Test
    public void list(){
       PageableResult<Dept> pageDepts = departmentService.findPageDepartments(null, 0, 20);

        for(Dept department : pageDepts.getItems()){
            logger.info(department.getId() + " : " + department.getName());
        }
    }

    @org.junit.Test
    public void getChildren(){
       List<Tree> list =  departmentService.findDeptByParent(1);

        for(Tree tree : list){
            logger.info(tree.getId() + " : " + tree.getName());
        }
    }

    @org.junit.Test
    public void findAllChildren(){

        List<Department> list = departmentService.findAllChildren(7);
        for(Department department : list){
            logger.info(department.getDepartmentId() + " : " + department.getTitle());
        }
    }
}
