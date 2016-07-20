package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.RightsService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;

import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/7/20.
 */
@Controller
public class RightsApiController {

    @Resource
    private RightsService rightsService;

    @RequestMapping(value = "/api/rights",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rightsList(){
        PageableResult<Rights> productList =  rightsService.findPageRights(0,0);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,productList);
    }

    @RequestMapping(value = "/api/rights/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult rightsDetail(Integer rightsId){
        Rights rights = rightsService.getRights(rightsId);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,rights);
    }
}
