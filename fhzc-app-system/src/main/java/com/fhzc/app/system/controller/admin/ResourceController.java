package com.fhzc.app.system.controller.admin;

import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lihongde on 2016/7/25 11:58
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {

    @RequestMapping(value = "/list")
    public AjaxJson listResource(){
        return new AjaxJson(true);
    }
}
