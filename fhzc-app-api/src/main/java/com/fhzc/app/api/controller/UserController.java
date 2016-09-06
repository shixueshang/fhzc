package com.fhzc.app.api.controller;

import com.fhzc.app.api.exception.BadRequestException;
import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.*;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;


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

    @Resource
    private AssetsService assetsService;

    /**
     * 获取登录用户信息
     * @return
     */
    @RequestMapping(value = "/api/user/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getUserInfo() throws Exception {
        User user  = getCurrentUser();
        user = super.setUserAvatarPath(user);
        Map<String, Object> result = ObjUtils.objectToMap(user);
        if(user.getLoginRole().equals(Const.USER_ROLE.CUSTOMER)) {
            Customer customer = customerService.getCustomerByUid(user.getUid());
            result.put("levelId", customer.getLevelId());
            result.put("level", super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
            result.put("cb_id", customer.getCbId());

            List<PlannerCustomer> plannerCustomers = plannerCustomerService.getCustomerPlannerList(customer.getCustomerId());
            if(plannerCustomers != null) {
                List<Map<String, Object>> planners = new ArrayList<Map<String, Object>>();
                for (PlannerCustomer pl : plannerCustomers) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Planner planner = plannerService.getPlanner(pl.getPlannerId());
                    User plannerUser = userService.getUser(planner.getUid());
                    map.put("plannerId", pl.getPlannerId());
                    map.put("uid", planner.getUid());
                    map.put("plannerName", plannerUser.getRealname());
                    map.put("isMain", pl.getIsMain());
                    planners.add(map);
                }
                result.put("planners", planners);
            }
        }else{
            Planner pl = plannerService.getPlannerByUid(user.getUid());
            result.put("position", pl.getJobTitleCn());
            result.put("department", departmentService.getDeparent(pl.getDepartmentId()).getTitle());

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
        if (! user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "查询的用户不是理财师");
        }
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        if(planner == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "查询的用户没有理财师数据");
        }
        List<PlannerCustomer> plannerCustomers = plannerCustomerService.getPlannerCustomerList(planner.getId());

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if(plannerCustomers !=null ) {
            for (PlannerCustomer pc : plannerCustomers) {
                Map<String, Object> map = new HashMap<String, Object>();
                Customer customer = customerService.getCustomer(pc.getCustomerId());
                User customerUser= userService.getUser(customer.getUid());
                Integer assetsSum = 0;

                /* 计算客户资产 */
                List<AssetsHistory> assetsHistoryList = assetsService.getHistory(pc.getCustomerId());
                if (assetsHistoryList == null) {
                    assetsSum = 0;
                } else {
                    for (AssetsHistory asset:assetsHistoryList){

                        if(asset.getType().equals(Const.ASSET_TYPE.PURCHASE)){
                            assetsSum = assetsSum + asset.getAmount();
                        }

                        if(asset.getType().equals(Const.ASSET_TYPE.RENEW)){
                            assetsSum = assetsSum + asset.getAmount();
                        }

                        if(asset.getType().equals(Const.ASSET_TYPE.DIVIDEND)){
                            assetsSum = assetsSum + asset.getAmount();
                        }

                        if(asset.getType().equals(Const.ASSET_TYPE.REDEMPTION)){
                            assetsSum = assetsSum  - asset.getAmount();
                        }

                    }
                }

                map.put("assetsSum", assetsSum);
                map.put("uid", customerUser.getUid());
                map.put("customerId", pc.getCustomerId());
                customerUser = super.setUserAvatarPath(customerUser);
                map.put("avatar", customerUser.getAvatar());
                map.put("realname", customerUser.getRealname());
                map.put("mobile", customerUser.getMobile());
                map.put("phone", customerUser.getPhone());
                map.put("address", customerUser.getAddress());
                map.put("risk", super.getDicName(customer.getRisk(), Const.DIC_CAT.RISK_LEVEL));
                map.put("main", pc.getIsMain());
                map.put("memo", pc.getMemo());
                map.put("passportType", super.getDicName(customerUser.getPassportTypeId(), Const.DIC_CAT.PASSPORT));
                map.put("passportTypeId", customerUser.getPassportTypeId());

                StringBuilder sb1 = new StringBuilder(customerUser.getPassportCode());
                map.put("passportCode", sb1.replace(3, 7, "****"));
                map.put("passportAddress", customerUser.getPassportAddress());
                map.put("birthday", customerUser.getBirthday());
                if (customerUser.getGender().equals(Const.GENDER.MALE)) {
                    map.put("gender", Const.GENDER.MALE_ZH);
                }

                if (customerUser.getGender().equals(Const.GENDER.FEMALE)) {
                    map.put("gender", Const.GENDER.FEMALE_ZH);
                }

                map.put("score", scoreService.getTotalScore(customerUser.getUid()));
                map.put("level", super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));

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
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"非理财师用户请求");
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

        User u = userService.getUserByLogin(login.trim());
        if(u != null){
            throw new BadRequestException("登录名已存在,请重新输入");
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
    public ApiJsonResult getCustomerInfo(@RequestParam(value = "customer_id") Integer customerId) throws Exception {
        Customer customer = customerService.getCustomer(customerId);
        if(customer != null) {
            User customerUser = userService.getUser(customer.getUid());
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("name", customerUser.getRealname());

            if (customer.getCustomerType().equals(Const.CUSTOMER_TYPE.ORGAN_CUSTOMER)) {
                result.put("type", Const.CUSTOMER_TYPE.ORGAN_CUSTOMER_ZH);
            }
            if (customer.getCustomerType().equals(Const.CUSTOMER_TYPE.SINGLE_CUSTOMER)) {
                result.put("type", Const.CUSTOMER_TYPE.SINGLE_CUSTOMER_ZH);
            }

            result.put("level", super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
            result.put("risk", super.getDicName(customer.getRisk(), Const.DIC_CAT.RISK_LEVEL));
            result.put("passportType", super.getDicName(customerUser.getPassportTypeId(), Const.DIC_CAT.PASSPORT));
            result.put("passportTypeId", customerUser.getPassportTypeId());

            StringBuilder sb1 = new StringBuilder(customerUser.getPassportCode());
            result.put("passportCode", sb1.replace(3, 7, "****"));
            result.put("passportAddress", customerUser.getPassportAddress());
            result.put("birthday", customerUser.getBirthday());
            if (customerUser.getGender().equals(Const.GENDER.MALE)) {
                result.put("gender", Const.GENDER.MALE_ZH);
            }

            if (customerUser.getGender().equals(Const.GENDER.FEMALE)) {
                result.put("gender", Const.GENDER.FEMALE_ZH);
            }
            StringBuilder sb2 = new StringBuilder(customerUser.getMobile());
            result.put("mobile", sb2.replace(3, 6, "***"));

            result.put("email", customerUser.getEmail());

            result.put("address", customerUser.getAddress());

            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST, "无此客户信息");
    }

    /**
     * 获取理财师信息
     * @return
     */
    @RequestMapping(value = "/api/planner/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getPlannerInfo(Integer planner_id) throws Exception {
        Planner planner = plannerService.getPlanner(planner_id);
        if(planner == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"没有这个理财师");
        }
        User user = userService.getUser(planner.getUid());
        user = super.setUserAvatarPath(user);
        Map result = new HashMap();
        result.put("name", user.getRealname());
        result.put("avatar", user.getAvatar());
        result.put("workNum", planner.getWorkNum());
        //获得3级部门信息
        Department department = departmentService.getDeparent(planner.getDepartmentId());
        result.put("departmentId", departmentService.getDeparent(department.getParentDeptId()).getTitle());
        result.put("status", planner.getStatus());
        result.put("roleId", planner.getRoleId());
        result.put("entryTime", planner.getEntryTime());
        result.put("jobTitleCn", planner.getJobTitleCn());
        result.put("position", planner.getPosition());
        result.put("mobile", user.getMobile());
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }

    /**
     * 设置客户风险等级
     * @param risk
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/set/risk", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult setRisk( @RequestParam(value = "risk") Integer risk) throws Exception {
        Customer customer = customerService.getCustomerByUid(super.getCurrentUser().getUid());
        if(customer == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND, "没有这个客户");
        }
        customer.setRisk(risk);
        int result = customerService.setCustomer(customer);
        if (result > 0) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, "修改成功");
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED, "修改失败");
    }

    /**
     * 设置头像
     * @param userId
     * @return
     */
    @RequestMapping(value = "/api/set/avatar", method = RequestMethod.POST)
    @ResponseBody
    public ApiJsonResult setAvatar(Integer userId) {
        String savePath = TextUtils.getConfig(Const.CONFIG_KEY_IMAGE_SAVE_PATH, this);
        List<String> imageList = FileUtils.saveFilesToDisk(request, savePath);
        String imageFile = null;
        if (imageList.size() > 0) {
            User user = userService.getUser(userId);
            imageFile = savePath +  imageList.get(0);
            user.setAvatar(imageFile);
            userService.updateUser(user);

            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("user", user);

            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, imageFile);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.FAILED, "上传失败");
    }


}

