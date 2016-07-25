package com.fhzc.app.system.controller.admin;

import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AdminRoleService;
import com.fhzc.app.system.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listAdmin(){
        ModelAndView mav = new ModelAndView("system/admin/list");
        PageableResult<Admin> pageableResult = adminService.findPageAdmins(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("admins", pageableResult.getItems());
        mav.addObject("roles", adminRoleService.getAllRoles());
        return mav;
    }


}
