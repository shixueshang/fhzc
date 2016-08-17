package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.*;


import com.fhzc.app.dao.mybatis.model.AboutApp;
import com.fhzc.app.dao.mybatis.model.Suggest;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private AboutAppService aboutAppService;

    @Resource
    private SuggestService suggestService;

    /**
     * 首页-精选
     * @return
     */
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

    /**
     * 私行服务
     * @return
     */
    @RequestMapping(value = "/api/mybank",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult mybank(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("product", productService.getNewProductList());
        map.put("rights", rightsService.getLatestRights());
        map.put("activity", activityService.getRecommendActivityList());
        map.put("report", reportService.getRecommendReportList());
        map.put("banner_pic",bannerService.get(Const.BANNER_TYPE.PIC));//私行提示下面的第一张图
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,map);
    }

    /**
     * 关于App
     * @return
     */
    @RequestMapping(value = "/api/aboutApp",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult aboutApp(@RequestParam(value = "version") String version){
        AboutApp aboutApp = aboutAppService.getAppByVersion(version);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,aboutApp);
    }


    /**
     * 取得最新版本app信息
     * @return
     */
    @RequestMapping(value = "/api/latestApp",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult latestApp(@RequestParam(value = "version") String version){
        AboutApp currentAboutApp = aboutAppService.getAppByVersion(version);
        AboutApp latestAboutApp = aboutAppService.getLatestApp();
        if(currentAboutApp == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,latestAboutApp);
        }
        if(currentAboutApp.getId() < latestAboutApp.getId()){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,latestAboutApp);
        }else{
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,false);
        }
    }

    /**
     * 联系我们
     *
     * @return
     */
    @RequestMapping(value = "/api/aboutUs", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult aboutUs() {
        AboutApp aboutApp = aboutAppService.getContactUs();
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,aboutApp);
    }

    /**
     * 提交反馈
     * @return
     */
    @RequestMapping(value = "/api/suggest/add", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult suggestAdd(@RequestParam(value = "type") String type,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "mobile") String mobile) {

        Suggest suggest = new Suggest();
        suggest.setType(type);
        suggest.setMobile(mobile);
        suggest.setContent(content);
        String savePath = TextUtils.getConfig(Const.CONFIG_KEY_IMAGE_SAVE_PATH, this);
        String imgs = "";
        List<String> imageList = FileUtils.saveFilesToDisk(request, savePath);
        if (imageList.size() > 0) {
            imgs = ListUtil.listToString(imageList, Const.SEPRATOR);
        }
        suggest.setImgs(imgs);
        Integer result = suggestService.add(suggest);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }

}
