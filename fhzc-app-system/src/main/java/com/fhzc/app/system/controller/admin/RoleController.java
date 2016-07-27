package com.fhzc.app.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.AdminRole;
import com.fhzc.app.dao.mybatis.model.SystemRoleModule;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AdminRoleService;
import com.fhzc.app.system.service.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/26 11:29
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listRole(){
        ModelAndView mav = new ModelAndView("system/role/list");
        PageableResult<AdminRole> pageableResult = adminRoleService.findPageRole(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("roles", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/pub")
    public String pub(){
       return "system/role/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(AdminRole role){
        ModelAndView mav = new ModelAndView("system/role/list");
        adminRoleService.addOrUpdateRole(role);
        PageableResult<AdminRole> pageableResult = adminRoleService.findPageRole(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("roles", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Integer roleId){
        ModelAndView mav = new ModelAndView("system/role/add");
        mav.addObject("role", JSON.toJSON(adminRoleService.findRoleById(roleId)));
        return mav;
    }


    /**
     * 给角色分配权限
     * @return
     */
    @RequestMapping(value = "/authorization/{id}", method = RequestMethod.GET)
    public ModelAndView authorization(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("system/role/authorization");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resources = resourceService.findAllResource();
        List<SystemRoleModule> roleModules = resourceService.findModulesByRole(id);
        for(Map<String, Object> resourceMap : resources){
            for(SystemRoleModule roleModule : roleModules){
                if(Integer.parseInt(resourceMap.get("id").toString()) == roleModule.getModuleId()){
                    resourceMap.put("checkbox", "checked");
                }
            }
            resourceMap.put("level_px", (Integer)resourceMap.get("level") * 10 + 5);
            result.add(resourceMap);
        }

        List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> childrenSource = resourceService.findModulesByLevel(3);
        for(Map<String, Object> childMap : childrenSource){
            for(SystemRoleModule roleModule : roleModules){
                if(Integer.parseInt(childMap.get("id").toString()) == roleModule.getModuleId()){
                    childMap.put("checkbox", "checked");
                }
            }
            children.add(childMap);
        }

        mav.addObject("role", adminRoleService.findRoleById(id));
        mav.addObject("resources", result);
        mav.addObject("children", children);
        return mav;
    }

    /**
     * 提交权限
     * @return
     */
    @RequestMapping(value = "/authorization/confirm", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson confirm(@RequestBody List<SystemRoleModule> roleModules){

        //删除该角色以前的权限
        resourceService.deleteModuleByRole(roleModules.get(0).getAdminRoleId());

        //重新授权
        for(SystemRoleModule roleModule : roleModules){
            roleModule.setMode(Const.READ_WRITE.READ_AND_WRITE);
            resourceService.addRoleModule(roleModule);
        }
        return new AjaxJson(true);
    }

}
