package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ActivityService;
import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.api.service.ReportService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;

import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by freeman on 16/7/20.
 */
@Controller
public class IndexApiController {

    @Resource
    private ProductService productService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/api/index",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult indexList(){

        Map<String, Object> map = new HashMap<String,Object>();
        PageableResult<Product> productList =  productService.getRecommendProductList();
        PageableResult<Activity> activityList =  activityService.getRecommendActivityList();
        PageableResult<Report> reportServiceList =  reportService.getRecommendReportList();
        map.put("product", productList);
        map.put("activity", activityList);
        map.put("report", reportServiceList);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,map);
    }

}
