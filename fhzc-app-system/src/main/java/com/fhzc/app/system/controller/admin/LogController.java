package com.fhzc.app.system.controller.admin;

import com.fhzc.app.dao.mybatis.model.SystemLog;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AdminService;
import com.fhzc.app.system.service.SystemLogService;
import com.fhzc.app.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/21 13:55
 */
@Controller
@RequestMapping(value = "/system/log")
public class LogController extends BaseController {

    @Resource
    private SystemLogService systemLogService;

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("system/log/list");
        mav.addObject("url", "system/log");
        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(Date operationDate){
        ModelAndView mav = new ModelAndView("system/log/list");
        PageableResult<SystemLog> pageableResult = systemLogService.findPageLogs(operationDate, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));

        List<SystemLog> logs = new ArrayList<SystemLog>();
        for(SystemLog log : pageableResult.getItems()){
            log.setUserName(adminService.findAdminById(log.getUserId()).getRealname());
            logs.add(log);
        }
        mav.addObject("logs", logs);
        mav.addObject("url", "system/log");
        return mav;
    }
}
