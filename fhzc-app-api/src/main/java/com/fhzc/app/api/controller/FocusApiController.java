package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by freeman on 16/7/28.
 */
@Controller
public class FocusApiController extends BaseController {

    @Resource
    private FocusService focusService;

    @Resource
    private ProductService productService;

    @Resource
    private ReportService reportService;

    @Resource
    private ActivityService activityService;

    @Resource
    private RightsService rightsService;

    /*
     * uid 用户id
     * fid 被关注的资源id,可能是产品id、活动id等
     * ftype 关注类型
     * status 关注状态
     */
    @RequestMapping(value = "/api/focus", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult focusOn(Integer uid, Integer fid, String ftype, Integer status){
        Focus focus = new Focus();
        List<Focus> focusList = focusService.getFocusByCond(uid,fid,ftype);
        if (focusList.size() > 0) {
            focus.setId(focusList.get(0).getId());
        }

        focus.setCtime(new Date());
        focus.setUid(uid);
        focus.setFid(fid);
        focus.setFtype(ftype);
        focus.setStatus(status);
        focusService.addOrUpdateFocus(focus);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    @RequestMapping(value = "/api/focus/status", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult focusStatus(Integer uid, Integer fid, String ftype){
        List<Focus> focusList = focusService.getFocusByCond(uid,fid,ftype);
        Integer status = focusList.get(0).getStatus();

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,status);
    }

    @RequestMapping(value = "/api/focus/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult focusList(Integer uid){
        List<Focus> focuslist = focusService.getFocusList(uid);
        List<Map> productList = new ArrayList<>();
        List<Map> reportList = new ArrayList<>();
        List<Map> activityList = new ArrayList<>();
        List<Map> rightsList = new ArrayList<>();
        Map list = new HashMap();
        for(Focus focus : focuslist){
            String ftype = focus.getFtype();
            Integer fid = focus.getFid();
            Map map = new HashMap();
            map.put("id",fid);
            switch (ftype){
                case "product":
                    Product product = productService.getProduct(fid);
                    if(product == null) continue;
                    map.put("name",product.getName());
                    map.put("expected_min",product.getExpectedMin());
                    map.put("expected_max",product.getExpectedMax());
                    map.put("renew_deadline",product.getRenewDeadline());
                    map.put("invest_threshold",product.getInvestThreshold());
                    map.put("collect_start",product.getCollectStart());
                    map.put("collect_end",product.getCollectEnd());
                    map.put("cover",product.getCover());
                    map.put("status",product.getStatus());
                    productList.add(map);
                    break;
                case "report":
                    Report report = reportService.getReport(fid);
                    if(report == null) continue;
                    map.put("name",report.getName());
                    map.put("cover",report.getCover());
                    map.put("summary",report.getSummary());
                    map.put("ctime",report.getCtime());
                    reportList.add(map);
                    break;
                case "activity":
                    Activity activity = activityService.getActivity(fid);
                    if(activity == null) continue;
                    map.put("name",activity.getName());
                    map.put("cover",activity.getCover());
                    map.put("status",activity.getStatus());
                    map.put("apply_end_time",activity.getApplyEndTime());
                    activityList.add(map);
                    break;
                case "rights":
                    Rights rights = rightsService.getRights(fid);
                    if(rights == null) continue;
                    map.put("name",rights.getName());
                    map.put("cover",rights.getCover());
                    map.put("summary",rights.getSummary());
                    rightsList.add(map);
                    break;
            }
        }
        list.put("product",productList);
        list.put("report",reportList);
        list.put("activity",activityList);
        list.put("rights",rightsList);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,list);
    }
}
