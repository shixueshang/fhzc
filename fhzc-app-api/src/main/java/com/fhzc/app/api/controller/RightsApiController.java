package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.DictionaryService;
import com.fhzc.app.api.service.FocusService;
import com.fhzc.app.api.service.RightsReservationService;
import com.fhzc.app.api.service.RightsService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;

import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/7/20.
 */
@Controller
public class RightsApiController extends BaseController{

    @Resource
    private RightsService rightsService;

    @Resource
    private FocusService focusService;

    @Resource
    private RightsReservationService rightsReservationService;

    @Resource
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/api/rights",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rightsList(Integer cid){
        List<Rights> rightsList =  rightsService.getListByCid(cid);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,rightsList);
    }

    @RequestMapping(value = "/api/rights/select",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rightsSelectList(){
        PageableResult<Rights> rightsList =  rightsService.findPageRights(0,100);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,rightsList);
    }

    @RequestMapping(value = "/api/rights/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult rightsDetail(Integer rightsId) throws Exception {
        Rights rights = rightsService.getRights(rightsId);
        Map result = ObjUtils.objectToMap(rights);
        User user = super.getCurrentUser();
        Focus focus = focusService.getFocusByCond(user.getUid(),rightsId,APIConstants.FocusType.Rights);
        List<Dictionary> dictionaryList = dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL);
        result.put("levelNeed","");
        for (Dictionary di:dictionaryList){
            if(rights.getLevel().toString().equals(di.getValue())){
                result.put("levelNeed",di.getKey());
                break;
            }
        }
        if(focus != null){
            result.put("focusStatus",focus.getStatus());
        }else{
            result.put("focusStatus","");
        }
        RightsReservation reservation = rightsReservationService.getUserRightsReservation(user.getUid(),rightsId);
        if(reservation != null) {
            result.put("reservationStatus", reservation.getStatus());
            result.put("reservationId", reservation.getId());
        }else{
            result.put("reservationStatus", "");
            result.put("reservationId", "");
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
