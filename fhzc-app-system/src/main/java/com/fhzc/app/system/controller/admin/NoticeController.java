package com.fhzc.app.system.controller.admin;

import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihongde on 2016/8/17 14:35
 */
@Controller
@RequestMapping(value = "/system/notice")
public class NoticeController extends BaseController {

    @RequestMapping(value = "/pub", method = RequestMethod.GET)
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("system/notice/add");
        mav.addObject("url", "system/notice");
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("system/notice/list");

        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addOrUpdate(SystemNotice systemNotice){


        return new AjaxJson(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        return new AjaxJson(true);
    }


}
