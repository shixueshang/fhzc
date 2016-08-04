package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;


import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.util.Const;
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

    @Resource
    private RightsService rightsService;

    @Resource
    private BannerService bannerService;

    @RequestMapping(value = "/api/index",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult indexList(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("product", productService.getRecommendProductList());
        map.put("activity", activityService.getRecommendActivityList());
        map.put("report", reportService.getRecommendReportList());

        map.put("banner_text",bannerService.get(Const.BANNER_TYPE.TEXT));//私行提示
        map.put("banner_pic",bannerService.get(Const.BANNER_TYPE.PIC));//私行提示下面的第一张图

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,map);
    }


    @RequestMapping(value = "/api/mybank",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult mybank(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("product", productService.getNewProductList());
        map.put("rights", rightsService.getRecommend());
        map.put("activity", activityService.getRecommendActivityList());
        map.put("report", reportService.getRecommendReportList());
        map.put("banner_pic",bannerService.get(Const.BANNER_TYPE.PIC));//私行提示下面的第一张图
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,map);
    }

}
