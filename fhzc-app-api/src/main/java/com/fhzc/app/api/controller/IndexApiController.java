package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.ActivityService;
import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.api.service.ReportService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by freeman on 16/7/20.
 */
@Controller
public class IndexApiController extends BaseController {

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
        map.put("product", productService.getRecommendProductList());
        map.put("activity", activityService.getRecommendActivityList());
        map.put("report", reportService.getRecommendReportList());
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,map);
    }

}
