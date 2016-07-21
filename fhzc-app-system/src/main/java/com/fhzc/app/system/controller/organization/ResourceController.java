package com.fhzc.app.system.controller.organization;

import com.fhzc.app.system.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihongde on 2016/7/8 19:57
 */
@Controller
@RequestMapping(value = "/organization")
public class ResourceController extends BaseController {

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public ModelAndView listResources(){
        ModelAndView mav = new ModelAndView("organization/department");

        return mav;
    }
}
