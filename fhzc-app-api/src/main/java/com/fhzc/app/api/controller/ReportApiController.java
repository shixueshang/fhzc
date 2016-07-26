package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ReportService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/7/19.
 */
@Controller
public class ReportApiController extends BaseController{

    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/api/report",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult reportList(){
        PageableResult<Report> reportList =  reportService.findPageReports(0,100);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,reportList);
    }

    @RequestMapping(value = "/api/report/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult reportDetail(Integer reportId){
        Report report = reportService.getReport(reportId);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,report);
    }
}
