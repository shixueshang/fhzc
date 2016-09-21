package com.fhzc.app.system.controller.admin;

import javax.annotation.Resource;

import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.TextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fhzc.app.dao.mybatis.model.Suggest;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.SuggestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Double_J on 2016/9/7
 */
@Controller
@RequestMapping(value = "/system/suggest")
public class SuggestController extends BaseController{
	
    @Resource
    private SuggestService suggestService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询意见列表")
    public ModelAndView listSuggest(){
        ModelAndView mav = new ModelAndView("system/suggest/list");
        PageableResult<Suggest> pageableResult = suggestService.findPageSuggests(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        List<Suggest> list = new ArrayList<Suggest>();
        for(Suggest suggest : pageableResult.getItems()){
            String images = suggest.getImgs();
            if(StringUtils.isNotEmpty(images)){
                StringBuilder sb = new StringBuilder();
                String[] imgArr = images.split(",");
                for(String img : imgArr){
                    String imgTag = "<img atl='' src="+ basePath + TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this) + img +" />";
                    sb.append(imgTag);
                }
                suggest.setImgs(sb.toString());
            }
            list.add(suggest);
        }
        mav.addObject("suggests", pageableResult.getItems());
        mav.addObject("url", "system/suggest");
        return mav;
    }

}
