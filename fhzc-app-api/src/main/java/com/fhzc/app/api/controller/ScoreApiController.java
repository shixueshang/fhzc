package com.fhzc.app.api.controller;

import com.fhzc.app.api.controller.BaseController;
import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/7/29.
 */
@Controller
public class ScoreApiController extends BaseController {

    @Resource
    private ScoreService scoreService;

    @RequestMapping(value = "/api/personal/score",method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult personalScore(){

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }
}
