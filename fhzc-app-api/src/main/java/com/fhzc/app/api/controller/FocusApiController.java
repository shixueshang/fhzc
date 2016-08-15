package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Resource
    private DictionaryService dictionaryService;

    /**
     * uid 用户id
     * fid 被关注的资源id,可能是产品id、活动id等
     * ftype 关注类型
     * status 关注状态
     */
    @RequestMapping(value = "/api/focus", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult focusOn(Integer uid, Integer fid, String ftype, Integer status){
        Focus focus = new Focus();
        Focus focusExist = focusService.getFocusByCond(uid,fid,ftype);
        if (focusExist != null) {
            focus.setId(focusExist.getId());
        }

        focus.setCtime(new Date());
        focus.setUid(uid);
        focus.setFid(fid);
        focus.setFtype(ftype);
        focus.setStatus(status);
        focusService.addOrUpdateFocus(focus);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 批量取消关注
     * @param productIds
     * @param reportIds
     * @param activityIds
     * @param rightsIds
     * @return
     */
    @RequestMapping(value = "/api/nofocus", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult nofocus(@RequestParam(value="productIds[]") Integer[] productIds,
                                 @RequestParam(value="reportIds[]") Integer[] reportIds,
                                 @RequestParam(value="activityIds[]") Integer[] activityIds,
                                 @RequestParam(value="rightsIds[]") Integer[] rightsIds
                                 ) {
        Integer uid = super.getCurrentUser().getUid();
        if(productIds != null) for (Integer productId: productIds) {
            Focus focus = new Focus();
            Focus focusExist = focusService.getFocusByCond(uid,productId, Const.FOCUS_TYPE.PRODUCT);
            if (focusExist != null) {
                focus.setId(focusExist.getId());
            }

            focus.setCtime(new Date());
            focus.setUid(uid);
            focus.setFid(productId);
            focus.setFtype(Const.FOCUS_TYPE.PRODUCT);
            focus.setStatus(Const.FOCUS_STATUS.OFF);
            focusService.addOrUpdateFocus(focus);
        }

        if(activityIds!= null) for (Integer activityId: activityIds) {
            Focus focus = new Focus();
            Focus focusExist = focusService.getFocusByCond(uid,activityId, Const.FOCUS_TYPE.ACTIVITY);
            if (focusExist != null) {
                focus.setId(focusExist.getId());
            }

            focus.setCtime(new Date());
            focus.setUid(uid);
            focus.setFid(activityId);
            focus.setFtype(Const.FOCUS_TYPE.ACTIVITY);
            focus.setStatus(Const.FOCUS_STATUS.OFF);
            focusService.addOrUpdateFocus(focus);
        }

        if(reportIds!= null) for (Integer reportId: reportIds) {
            Focus focus = new Focus();
            Focus focusExist = focusService.getFocusByCond(uid,reportId, Const.FOCUS_TYPE.REPORT);
            if (focusExist != null) {
                focus.setId(focusExist.getId());
            }

            focus.setCtime(new Date());
            focus.setUid(uid);
            focus.setFid(reportId);
            focus.setFtype(Const.FOCUS_TYPE.REPORT);
            focus.setStatus(Const.FOCUS_STATUS.OFF);
            focusService.addOrUpdateFocus(focus);
        }

        if(rightsIds!= null) for (Integer rightsId: rightsIds) {
            Focus focus = new Focus();
            Focus focusExist = focusService.getFocusByCond(uid,rightsId, Const.FOCUS_TYPE.RIGHTS);
            if (focusExist != null) {
                focus.setId(focusExist.getId());
            }

            focus.setCtime(new Date());
            focus.setUid(uid);
            focus.setFid(rightsId);
            focus.setFtype(Const.FOCUS_TYPE.RIGHTS);
            focus.setStatus(Const.FOCUS_STATUS.OFF);
            focusService.addOrUpdateFocus(focus);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }

    /**
     * 获取关注状态
     * @param uid
     * @param fid
     * @param ftype
     * @return
     */
    @RequestMapping(value = "/api/focus/status", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult focusStatus(Integer uid, Integer fid, String ftype){
        Focus focus = focusService.getFocusByCond(uid,fid,ftype);
        Integer status = focus.getStatus();

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,status);
    }

    /**
     * 我的关注
     * @param uid
     * @return
     */
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
            map.put("ctime",focus.getCtime());
            switch (ftype){
                case "product":
                    Product product = productService.getProduct(fid);
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
                    map.put("name",report.getName());
                    map.put("cover",report.getCover());
                    map.put("summary",report.getSummary());
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
                    map.put("name",rights.getName());
                    map.put("cover",rights.getCover());
                    map.put("summary",rights.getSummary());
                    map.put("spend_score",rights.getSpendScore());
                    map.put("level",rights.getLevel());
                    List<Dictionary> dictionary = dictionaryService.findDicByType("customer_level");
                    for(Dictionary dic : dictionary){
                        if(dic.getValue().equals(rights.getLevel())){
                            map.put("level_name",dic.getKey());
                        }
                    }
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
