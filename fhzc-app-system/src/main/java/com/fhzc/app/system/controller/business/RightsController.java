package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.system.service.RightsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lihongde on 2016/7/18 15:38
 */
@Controller
@RequestMapping(value = "business/rights")
public class RightsController extends BaseController{

    @Resource
    private RightsService rightsService;

    /**
     * 权益列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listRights(){
        ModelAndView mav = new ModelAndView("business/rights/list");
        PageableResult<Rights> pageableResult = rightsService.findPageRights(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("rights", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/pub")
    public String preAdd(){
        return "business/rights/add";
    }

    /**
     * 添加权益
     * @param rights
     * @param coverFile
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView addRight(Rights rights, MultipartFile coverFile){
        ModelAndView mav = new ModelAndView("business/rights/list");
        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            rights.setCover(coverPath + coverName);
        }
        rights.setCtime(new Date());
        rightsService.addOrUpdateRights(rights);

        PageableResult<Rights> pageableResult = rightsService.findPageRights(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("rights", pageableResult.getItems());
        return mav;
    }

    /**
     * 权益编辑
     * @param id
     * @return
     */
    @RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("business/rights/add");
        mav.addObject("right", rightsService.getRights(id));
        return mav;
    }

    /**
     * 权益预约列表
     * @return
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public ModelAndView listRightsOrder(){
        ModelAndView mav = new ModelAndView("business/rights/order/list");

        return mav;
    }
}
