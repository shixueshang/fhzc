package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
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
    private PlannerService plannerService;

    @Resource
    private UserService userService;

    @Resource
    private PlannerCustomerService plannerCustomerService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private DepartmentService departmentService;

    /**
     * 获取登录用户信息
     * @return
     */
    @RequestMapping(value = "/api/user/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getUserInfo() throws Exception {
        User user  = getCurrentUser();
        Map result = ObjUtils.objectToMap(user);
        if(user.getLoginRole().equals(Const.USER_ROLE.CUSTOMER)) {
            Customer customer = customerService.getCustomerByUid(user.getUid());
            user.setLevel(super.getLevelName(customer.getLevelId()));
            result.put("cb_id", customer.getCbId());

            List<PlannerCustomer> plannerCustomers = plannerCustomerService.getCustomerPlannerList(user.getUid());
            if(plannerCustomers != null) {
                List<Map<String, Object>> planners = new ArrayList<Map<String, Object>>();
                for (PlannerCustomer pl : plannerCustomers) {
                    Map planner = new HashMap();
                    User plannerUser = userService.getUser(pl.getPlannerId());
                    planner.put("plannerId", pl.getPlannerId());
                    planner.put("plannerName", plannerUser.getRealname());
                    planner.put("isMain", pl.getIsMain());
                    planners.add(planner);
                }
                result.put("planners", planners);
            }
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }

     /**
     * 理财师的客户
     * @return
     */
    @RequestMapping(value = "/api/planner/customer", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getPlannerCustomers(){
        User user  = getCurrentUser();
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        if (! user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "查询的用户不是理财师");
        }
        if(planner == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "查询的用户没有理财师数据");
        }
        List<PlannerCustomer> plannerCustomers = plannerCustomerService.getPlannerCustomerList(planner.getUid());

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if(plannerCustomers !=null ) {
            for (PlannerCustomer pc : plannerCustomers) {
                Map<String, Object> map = new HashMap<String, Object>();
                Customer customer = customerService.getCustomer(pc.getCustomerId());
                User cUser = userService.getUser(customer.getUid());
                Customer customerInfo = customerService.getCustomerByUid(pc.getCustomerId());
                map.put("uid", pc.getCustomerId());
                map.put("avatar", cUser.getAvatar());
                map.put("realname", cUser.getRealname());
                map.put("mobile", cUser.getMobile());
                map.put("phone", cUser.getPhone());
                map.put("address", cUser.getAddress());
                map.put("main", pc.getIsMain());
                map.put("memo", pc.getMemo());
                List<ScoreHistory> scoreHistoryList = scoreService.getAvailableList(pc.getCustomerId());
                map.put("score", scoreService.sumScore(scoreHistoryList));
                map.put("level", super.getLevelName(customerInfo.getLevelId()));

                result.add(map);
            }
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
        }else{
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "查询的理财师没有客户");
        }
    }

    /**
     * 更新理财师对客户的备注
     * @param id
     * @param memo
     * @return
     */
    @RequestMapping(value = "/api/planner/memo", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult updatePlannerMemo(Integer id, String memo){
        User user  = getCurrentUser();
        if(user.getLoginRole() == Const.USER_ROLE.CUSTOMER){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST);
        }
        PlannerCustomer plannerCustomer = plannerCustomerService.getRow(id);
        plannerCustomer.setMemo(memo);
        plannerCustomerService.updatePlannerCustomer(plannerCustomer);
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK);
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


    /**
     * 获取客户信息
     * @return
     */
    @RequestMapping(value = "/api/customer/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getCustomerInfo(Integer customerId) throws Exception {
        User user  = getCurrentUser();
        if(user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            User customer = userService.getUser(customerId);
            Customer customerInfo = customerService.getCustomerByUid(customerId);
            Map<String, Object> result= new HashMap<String, Object>();
            result.put("name", customer.getRealname());

            if (customerInfo.getCustomerType() == Const.CUSTOMER_TYPE.ORGAN_CUSTOMER) {
                result.put("type",Const.CUSTOMER_TYPE.ORGAN_CUSTOMER_ZH);
            }
            if (customerInfo.getCustomerType() == Const.CUSTOMER_TYPE.SINGLE_CUSTOMER) {
                result.put("type",Const.CUSTOMER_TYPE.SINGLE_CUSTOMER_ZH);
            }

            result.put("level", super.getLevelName(customerInfo.getLevelId()));
            result.put("risk", super.getRiskName(customerInfo.getRisk()));
            result.put("passportType", this.getPassportTypeName(customer.getPassportTypeId()));
            result.put("passportTypeId", customer.getPassportTypeId());

            StringBuilder sb1 = new StringBuilder(customer.getPassportCode());
            result.put("passportCode", sb1.replace(3,7,"****"));
            result.put("passportAddress", customer.getPassportAddress());
            result.put("birthday", customer.getBirthday());
            if (customer.getGender() == Const.GENDER.MALE) {
                result.put("gender", Const.GENDER.MALE_ZH);
            }

            if (customer.getGender() == Const.GENDER.FEMALE) {
                result.put("gender", Const.GENDER.FEMALE_ZH);
            }
            StringBuilder sb2 = new StringBuilder(customer.getMobile());
            result.put("mobile", sb2.replace(3,6,"***"));

            result.put("email",customer.getEmail());

            result.put("address",customer.getAddress());

            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST);
    }

    /**
     * 获取理财师信息
     * @return
     */
    @RequestMapping(value = "/api/planner/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getPlannerInfo(Integer planner_id) throws Exception {
        User user = userService.getUser(planner_id);
        if(user == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"没有这个用户");
        }
        Planner planner = plannerService.getPlannerByUid(planner_id);
        Map result = new HashMap();
        if(planner != null) {
            result.put("name", user.getRealname());
            result.put("avatar", user.getAvatar());
            result.put("workNum", planner.getWorkNum());
            result.put("departmentId", departmentService.getDeparent(planner.getDepartmentId()).getTitle());
            result.put("status", planner.getStatus());
            result.put("roleId", planner.getRoleId());
            result.put("entryTime", planner.getEntryTime());
            result.put("jobTitleCn", planner.getJobTitleCn());
            result.put("position", planner.getPosition());
            result.put("mobile", user.getMobile());
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }
}

