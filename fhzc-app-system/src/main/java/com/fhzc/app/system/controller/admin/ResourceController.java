package com.fhzc.app.system.controller.admin;

import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/25 11:58
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {

    @Resource
    private ResourceService resourceService;

    @RequestMapping(value = "/list")
    public ModelAndView listResource(){
        ModelAndView mav = new ModelAndView("system/resource/list");
        mav.addObject("resources", resourceService.findAllResource());
        return mav;
    }
}
