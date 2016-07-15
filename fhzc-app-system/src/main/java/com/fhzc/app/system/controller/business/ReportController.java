package com.fhzc.app.system.controller.business;

import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.mybatis.model.Report;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 投研报告
 * Created by lihongde on 2016/7/15.
 */
@Controller
@RequestMapping(value = "business/report")
public class ReportController extends BaseController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listReport(){
        ModelAndView mav = new ModelAndView("business/report/list");

        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addReport(Report report){
        ModelAndView mav = new ModelAndView("business/report/add");
        return mav;
    }
}
