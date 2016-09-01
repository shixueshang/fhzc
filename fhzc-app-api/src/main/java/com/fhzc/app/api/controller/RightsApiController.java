package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
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
import java.util.ArrayList;
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
    private CustomerService customerService;

    /**
     * 权益列表
     * @param cid
     * @return
     */
    @RequestMapping(value = "/api/rights",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rightsList(Integer cid) throws Exception {
        List<Rights> rightsList = rightsService.getListByCid(cid);
        List<Object> result = new ArrayList<>();
        if(rightsList != null){
            for (Rights rights:rightsList){
                String levelName = super.getDicName(rights.getLevel(), Const.DIC_CAT.CUSTOMER_LEVEL);
                Map map = ObjUtils.objectToMap(rights);
                map.put("levelName", levelName);
                result.add(map);
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }

    /**
     * 精选权益
     * @return
     */
    @RequestMapping(value = "/api/rights/select",method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rightsSelectList(){
        PageableResult<Rights> rightsList =  rightsService.findPageRights(0,100);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,rightsList);
    }

    /**
     * 权益详情
     * @param rightsId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/rights/detail",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult rightsDetail(Integer rightsId) throws Exception {
        Rights rights = rightsService.getRights(rightsId);
        Map<String, Object> result = ObjUtils.objectToMap(rights);
        User user = super.getCurrentUser();
        Focus focus = focusService.getFocusByCond(user.getUid(),rightsId,APIConstants.FocusType.Rights);
        result.put("levelNeed",super.getDicName(rights.getLevel(), Const.DIC_CAT.CUSTOMER_LEVEL));
        result.put("focusStatus",focus.getStatus() == null ? "" : focus.getStatus());
        Customer customer = customerService.getCustomerByUid(user.getUid());
        if(customer != null) {
            RightsReservation reservation = rightsReservationService.getUserRightsReservation(customer.getCustomerId(), rightsId);
            result.put("reservationStatus", reservation.getStatus() == null ? "" : reservation.getStatus());
            result.put("reservationId", reservation.getId() == null ? "" : reservation.getId());
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
