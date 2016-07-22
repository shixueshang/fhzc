package com.fhzc.app.system.controller.dictionary;

import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/22 14:04
 */
@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController extends BaseController {

    @Resource
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/findDicByType", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson findDicByType(String cat){

        return new AjaxJson(true, dictionaryService.findDicByType(cat));
    }
}
