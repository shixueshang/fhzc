package com.fhzc.app.system.controller.business;

import com.fhzc.app.system.commons.page.PageHelper;
import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.mybatis.model.Product;
import com.fhzc.app.system.mybatis.model.Report;
import com.fhzc.app.system.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 投研报告
 * Created by lihongde on 2016/7/15.
 */
@Controller
@RequestMapping(value = "business/report")
public class ReportController extends BaseController {

    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listReport(){
        ModelAndView mav = new ModelAndView("business/report/list");
        PageableResult<Report> pageableResult = reportService.findPageReports(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reports", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addReport(Report report){
        ModelAndView mav = new ModelAndView("business/report/list");
        reportService.addReport(report);
        return mav;
    }
}
