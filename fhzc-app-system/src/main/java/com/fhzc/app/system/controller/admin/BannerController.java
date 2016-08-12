package com.fhzc.app.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.Banner;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/8/12 15:28
 */
@Controller
@RequestMapping(value = "/system/banner")
public class BannerController extends BaseController {

    @Resource
    private BannerService bannerService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private ProductService productService;

    @Resource
    private ActivityService activityService;

    @Resource
    private RightsService rightsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("system/banner/list");
        PageableResult<Banner> pageableResult = bannerService.findPageBanners(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("banners", pageableResult.getItems());
        mav.addObject("fromTypes", dictionaryService.findDicByType(Const.DIC_CAT.SCORE_FROM_TYPE));
        mav.addObject("url", "system/banner");
        return mav;
    }

    @RequestMapping(value = "/pub")
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("system/banner/add");
        mav.addObject("fromTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.SCORE_FROM_TYPE)));
        mav.addObject("url", "system/banner");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrUpdate(Banner banner, MultipartFile coverFile){

        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            banner.setCover(coverPath + coverName);
        }

        banner.setStatus(Const.Data_Status.DATA_NORMAL);
        bannerService.addOrUpdateBanner(banner);

        return "redirect:/system/banner/list";
    }

    @RequestMapping(value = "/findFromId", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson findFromId(String fromType){
        return new AjaxJson(true, this.getFromByType(fromType));
    }

    private List<Map<String, Object>> getFromByType(String fromType){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        switch (fromType){
            case Const.FROM_TYPE.PRODUCT:
                List<Product> products = productService.findAllProduct();
                for(Product product : products){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", product.getPid());
                    map.put("name", product.getName());
                    result.add(map);
                }
                break;
            case Const.FROM_TYPE.ACTIVITY:
                List<Activity> activities = activityService.getAllActivities();
                for(Activity activity : activities){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", activity.getId());
                    map.put("name", activity.getName());
                    result.add(map);
                }
                break;
            case Const.FROM_TYPE.RIGHTS:
                List<Rights> rightses = rightsService.getAllRights();
                for(Rights rights : rightses){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", rights.getId());
                    map.put("name", rights.getName());
                    result.add(map);
                }
                break;
            case Const.FROM_TYPE.OTHER:
                break;

        }
        return result;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("system/banner/add");
        Banner banner = bannerService.getBanner(id);
        mav.addObject("banner", banner);
        mav.addObject("fromTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.SCORE_FROM_TYPE)));
        mav.addObject("fromIds", JSON.toJSON(this.getFromByType(banner.getFromType())));
        mav.addObject("url", "system/banner");
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        bannerService.delete(id);
        return new AjaxJson(true);
    }
}
