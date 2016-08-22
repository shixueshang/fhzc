package com.fhzc.app.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AdminRoleService;
import com.fhzc.app.system.service.AdminService;
import com.fhzc.app.system.service.AreasService;
import com.fhzc.app.system.service.DepartmentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/25 15:04
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private AreasService areasService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询管理员")
    public ModelAndView listAdmin(){
        ModelAndView mav = new ModelAndView("system/admin/list");
        PageableResult<Admin> pageableResult = adminService.findPageAdmins(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("admins", pageableResult.getItems());
        mav.addObject("roles", adminRoleService.getAllRoles());
        mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
        mav.addObject("areas", areasService.getAllAreas());
        mav.addObject("url", "system/admin");
        return mav;
    }

    @RequestMapping(value = "/pub", method = RequestMethod.GET)
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("system/admin/add");
        mav.addObject("roles", JSON.toJSON(adminRoleService.getAllRoles()));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("areas", JSON.toJSON(areasService.getAllAreas()));
        mav.addObject("url", "system/admin");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改管理员")
    public String add(Admin admin){
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        admin.setLoginIp(request.getRemoteAddr());
        adminService.addOrUpdateAdmin(admin);

        return "redirect:/system/admin/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("system/admin/add");
        mav.addObject("adminUser", adminService.findAdminById(id));
        mav.addObject("roles", JSON.toJSON(adminRoleService.getAllRoles()));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("areas", JSON.toJSON(areasService.getAllAreas()));
        mav.addObject("url", "system/admin");
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "删除管理员")
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        adminService.delete(id);
        return new AjaxJson(true);
    }
}
