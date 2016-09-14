package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.FocusService;
import com.fhzc.app.api.service.ReportService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.api.tools.TextUtils;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by freeman on 16/7/19.
 */
@Controller
public class ReportApiController extends BaseController{

    @Resource
    private ReportService reportService;

    @Resource
    private FocusService focusService;

    /**
     * 投研报告
     * @return
     */
    @RequestMapping(value = "/api/report",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult reportList(){
        PageableResult<Report> reportList =  reportService.findPageReports(page, size);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,reportList);
    }

    /**
     * 报告详情
     * @param reportId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/report/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult reportDetail(Integer reportId) throws Exception {
        Report report = reportService.getReport(reportId);
        report.setUrl(report.getUrl());
        Map result = ObjUtils.objectToMap(report);
        User user = super.getCurrentUser();
        Focus focus = focusService.getFocusByCond(user.getUid(),reportId,APIConstants.FocusType.Product);
        if(focus != null){
            result.put("focusStatus",focus.getStatus());
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,report);
    }
}
