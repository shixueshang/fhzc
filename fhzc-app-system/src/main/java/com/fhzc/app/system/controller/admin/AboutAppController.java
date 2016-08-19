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

    /**
     * 关于App
     * @return
     */
    @RequestMapping(value = "/app/pub", method = RequestMethod.GET)
    public ModelAndView about(){
        ModelAndView mav = new ModelAndView("system/about/about");
        mav.addObject("about", aboutAppService.getAboutApp());
        mav.addObject("url", "system/about");
        return mav;
    }

    /**
     * 新增
     * @param aboutApp
     * @return
     */
    @RequestMapping(value = "/app/add", method = RequestMethod.POST)
    public String addOrUpdate(AboutApp aboutApp){

        aboutApp.setIsUsing(Const.YES_OR_NO.YES);
        aboutAppService.addOrUpdate(aboutApp);
        if(Const.About_App.ABOUT_APP.equals(aboutApp.getType())){
            return "redirect:/system/about/app/pub";
        }
        return "redirect:/system/about/contact/pub";
    }

    /**
     * 联系我们
     * @return
     */
    @RequestMapping(value = "/contact/pub", method = RequestMethod.GET)
    public ModelAndView contact(){
        ModelAndView mav = new ModelAndView("system/about/contact");
        mav.addObject("contact", aboutAppService.getContactUs());
        mav.addObject("url", "system/about");
        return mav;
    }


}
