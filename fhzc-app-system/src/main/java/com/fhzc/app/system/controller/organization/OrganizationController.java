package com.fhzc.app.system.controller.organization;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.Dept;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/7/8 19:57
 */
@Controller
@RequestMapping(value = "/organization/department")
public class OrganizationController extends BaseController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 机构列表
     * @return
     */
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看机构列表")
    public ModelAndView listDepartments(){
        ModelAndView mav = new ModelAndView("organization/department/department");
        Admin admin = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());
        PageableResult<Dept> pageableResult =  departmentService.findPageDepartments(departments, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("depts", pageableResult.getItems());
        mav.addObject("deptsForAdd", JSON.toJSON(departmentService.getDepartmentTree()));
        mav.addObject("url", "organization/department");
        return mav;
    }

    /**
     * 添加或修改部门
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改机构")
    public String addOrUpdateDept(Department department){
        department.setCtime(new Date());
        department.setStatus(Const.Data_Status.DATA_NORMAL);

        //判断父级机构是否有子机构，如果没有则修改父级机构的leaf=0
        List<Department> list = departmentService.findChildren(department.getParentDeptId());
        if(list.size() == 0){
            Department parentDept = departmentService.getDepartment(department.getParentDeptId());
            parentDept.setLeaf(Const.YES_OR_NO.NO);
            departmentService.addOrUpdateDept(parentDept);
        }

        //根据父级的level设置自己level
        Department parent = departmentService.getDepartment(department.getParentDeptId());
        department.setLevel(parent.getLevel() + 1);
        department.setLeaf(Const.YES_OR_NO.YES);
        departmentService.addOrUpdateDept(department);

        return "redirect:/organization/department/department";
    }

    /**
     * 获得机构详情
     * @param id
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson edit(@PathVariable(value = "id") Integer id){

        return new AjaxJson(true, departmentService.getDepartment(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        departmentService.delete(id);
        return new AjaxJson(true);
    }

}
