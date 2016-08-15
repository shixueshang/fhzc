package com.fhzc.app.system.controller.admin;

import com.fhzc.app.dao.mybatis.model.AboutApp;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AboutAppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/8/15 11:51
 */
@Controller
@RequestMapping(value = "/system/about")
public class AboutAppController extends BaseController {

    @Resource
    private AboutAppService aboutAppService;

    @RequestMapping(value = "/pub", method = RequestMethod.GET)
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("system/about/about");
        mav.addObject("about", aboutAppService.getAboutApp());
        mav.addObject("url", "system/about");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrUpdate(AboutApp aboutApp){

        aboutApp.setIsUsing(Const.Data_Status.DATA_NORMAL);
        aboutAppService.addOrUpdate(aboutApp);
        return "redirect:/system/about/pub";
    }
}
