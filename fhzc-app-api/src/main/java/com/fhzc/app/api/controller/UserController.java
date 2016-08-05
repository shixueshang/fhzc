package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lihongde on 2016/7/28 14:59
 */
@Controller
public class UserController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private UserService userService;

    @Resource
    private PlannerCustomerService plannerCustomerService;

    @Resource
    private ScoreService scoreService;
    /**
     * 获取登录用户信息
     * @return
     */
    @RequestMapping(value = "/api/user/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getUserInfo(){
        User user  = getCurrentUser();
        Customer customer = customerService.getCustomerByUid(user.getUid());
        if(customer != null){
            List<Dictionary> dicts = dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL);
            for(Dictionary dict : dicts){
                if(dict.getValue().equals(customer.getLevelId().toString())){
                    user.setLevel(dict.getKey());
                }
            }
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, user);
    }

     /**
     * 理财师的客户
     * @return
     */
    @RequestMapping(value = "/api/planner/customer", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getPlannerCustomers(){
        User user  = getCurrentUser();
        List<PlannerCustomer> plannerCustomers = plannerCustomerService.getPlannerCustomerList(user.getUid());

        List<Map> result = new ArrayList<>();

        if(plannerCustomers != null) {
            for (PlannerCustomer pc : plannerCustomers) {
                Map map = new HashMap();
                User customer = userService.getUser(pc.getCustomerId());
                map.put("uid", pc.getCustomerId());
                map.put("avatar", customer.getAvatar());
                map.put("realname", customer.getRealname());
                map.put("mobile", customer.getMobile());
                map.put("phone", customer.getPhone());
                map.put("address", customer.getAddress());
                map.put("main", pc.getIsMain());
                List<ScoreHistory> scoreHistoryList = scoreService.getAvailableList(pc.getCustomerId());
                map.put("score", scoreService.sumScore(scoreHistoryList));
                result.add(map);
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }

    /**
     * 验证登陆名是否存在
     */
    @RequestMapping(value = "/api/login/exist", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult loginExist(String login){
        User user =  userService.getUserByLogin(login);
        Map map = new HashMap();
        if(user != null){
            map.put("isExist",true);
        }else{
            map.put("isExist",false);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, map);
    }

    /**
     * 修改登陆名
     */
    @RequestMapping(value = "/api/mod/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult modLogin(String login){
        if(login.trim() == ""){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST);
        }
        User user = super.getCurrentUser();
        user.setLogin(login);
        userService.updateUser(user);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
    }
}
