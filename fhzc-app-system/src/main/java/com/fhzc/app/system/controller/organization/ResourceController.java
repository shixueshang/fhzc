package com.fhzc.app.system.controller.organization;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/8 19:57
 */
@Controller
@RequestMapping(value = "/organization")
public class ResourceController extends BaseController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 机构列表
     * @return
     */
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public ModelAndView listResources(){
        ModelAndView mav = new ModelAndView("organization/department/department");
        PageableResult<Map<String, Object>> pageableResult =  departmentService.findPageDepts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("depts", pageableResult.getItems());
        mav.addObject("deptsForAdd", JSON.toJSON(pageableResult.getItems()));
        return mav;
    }

    /**
     * 添加或修改部门
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addOrUpdateDept(Department department){
        ModelAndView mav = new ModelAndView("organization/department/department");

        departmentService.addOrUpdateDept(department);
        PageableResult<Map<String, Object>> pageableResult =  departmentService.findPageDepts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("depts", pageableResult.getItems());
        mav.addObject("deptsForAdd", JSON.toJSON(pageableResult.getItems()));
        return mav;
    }
}
