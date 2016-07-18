package com.fhzc.app.system.controller.business;

import com.fhzc.app.system.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihongde on 2016/7/18 15:38
 */
@Controller
@RequestMapping(value = "business/activity")
public class ActivityController extends BaseController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listRights(){
        ModelAndView mav = new ModelAndView("business/activity/list");

        return mav;
    }

    @RequestMapping(value = "/pub")
    public String preAdd(){
        return "business/activity/add";
    }

    @RequestMapping(value = "/add")
    public ModelAndView addActivity(){
        ModelAndView mav = new ModelAndView("business/activity/list");

        return mav;
    }
}
