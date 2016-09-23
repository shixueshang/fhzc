package com.fhzc.app.system.controller.personal;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/30 14:00
 */
@Controller
@RequestMapping(value = "/personal/customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private PlannerService plannerService;

    @Resource
    private DepartmentService departmentService;


    /**
     * 个人客户列表页面
     * @return
     */
    @RequestMapping(value = "/single/list")
    public ModelAndView listSingleCustomer(){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        mav.addObject("url", "personal/customer");
        return mav;
    }

    /**
     * 根据姓名查询客户
     * @param name
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/single/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看个人客户列表")
    public ModelAndView findSingleCustomers(String name, String mobile){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        List<Customer> customerList = new ArrayList<Customer>();
        List<Map<String, Object>> scores = new ArrayList<Map<String, Object>>();
        List<User> users = null;

        Admin admin = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());
        if((StringUtils.isNotBlank(name)) && (StringUtils.isNotBlank(mobile))){
        	return mav;
        }
        PageableResult<Integer> customerIds = customerService.findPageCustomersByDepartments(departments, name.trim(), mobile.trim(), Const.CUSTOMER_TYPE.SINGLE_CUSTOMER, page, size);
        if (customerIds != null && customerIds.getItems() != null){
            users = new ArrayList<>(customerIds.getItems().size());

            for (Integer customerId : customerIds.getItems()){
                Customer customer = customerService.getCustomer(customerId);
                customerList.add(customer);

                Map<String, Object> scoreMap = new HashMap<String, Object>();
                scoreMap.put("customerId", customer.getCustomerId());
                scoreMap.put("availableScore", scoreService.getAvailableScore(customer.getUid()));
                scoreMap.put("frozenScore", scoreService.getFrozenScore(customer.getUid()));
                scores.add(scoreMap);

                users.add(userService.getUser(customer.getUid()));
            }
        }

        mav.addObject("page", PageHelper.getPageModel(request, customerIds));
        mav.addObject("customers", customerList);
        mav.addObject("users", users);
        mav.addObject("customerLevel", dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL));
        mav.addObject("passports", dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT));
        mav.addObject("scores", scores);
        mav.addObject("url", "personal/customer");
        return mav;
    }

    @RequestMapping(value = "/single/detail/{id}", method = RequestMethod.GET)
    public ModelAndView editSingleCustomer(@PathVariable(value = "id") Integer customerId){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerAdd");
        Customer customer = customerService.getCustomer(customerId);
        mav.addObject("customer", JSON.toJSON(customer));
        mav.addObject("user", JSON.toJSON(userService.getUser(customer.getUid())));
        mav.addObject("passports", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT)));
        mav.addObject("customerLevels", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("riskLevels", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL)));
        mav.addObject("availableScore", JSON.toJSON(scoreService.getAvailableScore(customer.getUid())));
        mav.addObject("frozenScore", JSON.toJSON(scoreService.getFrozenScore(customer.getUid())));

        PlannerCustomer plannerCustomer = customerService.getPlannerByCustomerId(customer.getCustomerId(), Const.YES_OR_NO.YES);
        Planner planner = plannerService.getPlanner(plannerCustomer.getPlannerId());
        mav.addObject("planner", JSON.toJSON(planner));
        mav.addObject("plannerUser", JSON.toJSON(userService.getUser(planner.getUid())));
        mav.addObject("url", "personal/customer");
        return mav;
    }

    @RequestMapping(value = "/single/update", method = RequestMethod.POST)
    public String update(Customer customer, User user){
        customerService.addOrUpdateCustomer(customer);
        userService.addOrUpdateUser(user);
        return "redirect:/personal/customer/single/list";
    }

    /**
     * 机构客户列表页面
     * @return
     */
    @RequestMapping(value = "/organ/list")
    public ModelAndView listOrganCustomer(){
        ModelAndView mav = new ModelAndView("personal/customer/organCustomerList");
        mav.addObject("url", "personal/customer");
        return mav;
    }

    /**
     * 根据姓名查询客户
     * @param name
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/organ/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看机构客户列表")
    public ModelAndView findOrganCustomers(String name, String mobile){
        ModelAndView mav = new ModelAndView("personal/customer/organCustomerList");
        List<Customer> customerList = new ArrayList<Customer>();
        List<Map<String, Object>> scores = new ArrayList<Map<String, Object>>();
        List<User> users = null;
        
        Admin admin = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());
        if((StringUtils.isNotBlank(name)) && (StringUtils.isNotBlank(mobile))){
        	return mav;
        }
        PageableResult<Integer> customerIds = customerService.findPageCustomersByDepartments(departments, name.trim(), mobile.trim(), Const.CUSTOMER_TYPE.ORGAN_CUSTOMER, page, size);
        if (customerIds != null && customerIds.getItems() != null){
            users = new ArrayList<>(customerIds.getItems().size());

            for (Integer customerId : customerIds.getItems()){
                Customer customer = customerService.getCustomer(customerId);
                customerList.add(customer);

                Map<String, Object> scoreMap = new HashMap<String, Object>();
                scoreMap.put("customerId", customer.getCustomerId());
                scoreMap.put("availableScore", scoreService.getAvailableScore(customer.getUid()));
                scoreMap.put("frozenScore", scoreService.getFrozenScore(customer.getUid()));
                scores.add(scoreMap);

                users.add(userService.getUser(customer.getUid()));
            }
        }
        mav.addObject("page", PageHelper.getPageModel(request, customerIds));
        mav.addObject("customers", customerList);
        mav.addObject("users", users);
        mav.addObject("customerLevel", dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL));
        mav.addObject("passports", dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT));
        mav.addObject("scores", scores);
        mav.addObject("url", "personal/customer");
        return mav;
    }

    @RequestMapping(value = "/organ/update", method = RequestMethod.POST)
    public String addOrUpdate(Customer customer, User user, MultipartFile businessLicenseFile, MultipartFile accountLicenseFile, MultipartFile entrustedLetterFile){
        if(!businessLicenseFile.isEmpty()){
            String fileName = FileUtil.generatePictureName(businessLicenseFile);
            String path = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(path, fileName, businessLicenseFile);
            customer.setBusinessLicense(path + fileName);
        }
        if(!accountLicenseFile.isEmpty()){
            String fileName = FileUtil.generatePictureName(accountLicenseFile);
            String path = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(path, fileName, accountLicenseFile);
            customer.setAccountLicense(path + fileName);
        }
        if(!entrustedLetterFile.isEmpty()){
            String fileName = FileUtil.generatePictureName(entrustedLetterFile);
            String path = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(path, fileName, entrustedLetterFile);
            customer.setEntrustedLetter(path + fileName);
        }
        Department department = departmentService.getDepartment(customer.getDepartmentId());
        customer.setOrganName(department.getTitle());
        customerService.addOrUpdateCustomer(customer);
        userService.addOrUpdateUser(user);
        return "redirect:/personal/customer/organ/list";
    }

    @RequestMapping(value = "/organ/detail/{id}", method = RequestMethod.GET)
    public ModelAndView editOrganCustomer(@PathVariable(value = "id") Integer customerId){
        ModelAndView mav = new ModelAndView("personal/customer/organCustomerAdd");
        Customer customer = customerService.getCustomer(customerId);
        mav.addObject("customer", JSON.toJSON(customer));
        mav.addObject("user", JSON.toJSON(userService.getUser(customer.getUid())));
        mav.addObject("passports", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT)));
        mav.addObject("customerLevels", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("riskLevels", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL)));
        mav.addObject("availableScore", JSON.toJSON(scoreService.getAvailableScore(customer.getUid())));
        mav.addObject("frozenScore", JSON.toJSON(scoreService.getFrozenScore(customer.getUid())));
        mav.addObject("departments", JSON.toJSON(departmentService.getDepartmentTree()));
        PlannerCustomer plannerCustomer = customerService.getPlannerByCustomerId(customer.getCustomerId(), Const.YES_OR_NO.YES);
        if(plannerCustomer != null){
            Planner planner = plannerService.getPlanner(plannerCustomer.getPlannerId());
            mav.addObject("planner", JSON.toJSON(planner));
            mav.addObject("plannerUser", JSON.toJSON(userService.getUser(planner.getUid())));
        }
        return mav;
    }

    /**
     * 权益享用人列表
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/organ/enjoy/list/{id}", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看权益享用人")
    public ModelAndView listEnjoy(@PathVariable(value = "id") Integer customerId){
        ModelAndView mav = new ModelAndView("/personal/customer/rightsEnjoy");
        mav.addObject("customerId", customerId);
        mav.addObject("enjoyPersons", JSON.toJSON(customerService.findOrganCustomer(customerId)));
        mav.addObject("passportTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT)));
        mav.addObject("url", "personal/customer");

        return mav;
    }

    /**
     * 添加或修改权益享用人
     * @param customerOrgan
     * @param attr
     * @return
     */
    @RequestMapping(value = "/organ/enjoy/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改权益享用人")
    public String addOrUpdate(CustomerOrgan customerOrgan, RedirectAttributes attr){
        customerService.addOrUpdateOrganCustomer(customerOrgan);
        attr.addAttribute("id", customerOrgan.getCustomerId());
        return "redirect:/personal/customer/organ/enjoy/list/{id}";
    }

    /**
     * 权益享用人详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/organ/enjoy/detail/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("/personal/customer/rightsEnjoy");
        CustomerOrgan customerOrgan = customerService.getRightsEnjoyPerson(id);
        mav.addObject("customerId", customerOrgan.getCustomerId());
        mav.addObject("enjoy", customerOrgan);
        mav.addObject("passportTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT)));
        mav.addObject("enjoyPersons", JSON.toJSON(customerService.findOrganCustomer(customerOrgan.getCustomerId())));
        mav.addObject("url", "personal/customer");
        return mav;
    }

    /**
     * 权益享用人删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/organ/enjoy/delete/{id}", method = RequestMethod.GET)
    @SystemControllerLog(description = "删除权益享用人")
    public String delete(@PathVariable(value = "id") Integer id, RedirectAttributes attr){
        CustomerOrgan customerOrgan = customerService.getRightsEnjoyPerson(id);
        customerService.delete(id);
        attr.addAttribute("id", customerOrgan.getCustomerId());
        return "redirect:/personal/customer/organ/enjoy/list/{id}";
    }

    @RequestMapping(value = "/missPlanner", method = RequestMethod.GET)
    public ModelAndView missPlanner(){
        ModelAndView mav = new ModelAndView("personal/customer/missPlanner");
        mav.addObject("url", "personal/customer");
        return mav;
    }

    /**
     * 根据姓名查询缺位的客户
     * @param name
     * @return
     */
    @RequestMapping(value = "/find/missPlanner", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看缺位客户列表")
    public ModelAndView findCustomersWithoutPlanner(String name){
        ModelAndView mav = new ModelAndView("personal/customer/missPlanner");
        PageableResult<User> pageableResult = userService.findPageUsers(name, "", page, size);

        Admin admin = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());
        List<Customer> customerList = new ArrayList<Customer>();
        for(User user : pageableResult.getItems()){
            Customer customer = customerService.getCustomerByUid(user.getUid(), null);
            if(customer != null){
                PlannerCustomer pc = customerService.getPlannerByCustomerId(customer.getCustomerId(), null);
                if(pc == null){
                    if(departments.contains(customer.getDepartmentId())){
                        customerList.add(customer);
                    }
                }else{
                    Planner planner = plannerService.getPlanner(pc.getPlannerId());
                    if(planner.getStatus().equals(Const.PLANNER_STATUS.OFF)){
                        if(departments.contains(customer.getDepartmentId())){
                            customer.setOldPlanner(userService.getUser(planner.getUid()).getRealname());
                            customer.setCustomerName(userService.getUser(customer.getUid()).getRealname());
                            customerList.add(customer);
                        }
                    }
                }

            }
        }


        mav.addObject("customers", customerList);
        mav.addObject("users", pageableResult.getItems());
        mav.addObject("customerLevel", dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL));
        mav.addObject("url", "personal/customer");
        return mav;
    }

    /**
     * 为客户指定理财师
     * @param customerId
     * @param plannerId
     * @return
     */
    @RequestMapping(value = "/assignPlanner", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson assignPlanner(Integer customerId, Integer plannerId){

        PlannerCustomer pc = customerService.getPlannerByCustomerId(customerId, Const.YES_OR_NO.YES);
        pc.setPlannerId(plannerId);
        customerService.updatePlannerCustomer(pc);

        //更换新客户的部门为理财师的部门
        Planner planner = plannerService.getPlanner(plannerId);
        Customer customer = customerService.getCustomer(customerId);
        customer.setDepartmentId(planner.getDepartmentId());
        customerService.addOrUpdateCustomer(customer);
        return new AjaxJson(true);
    }

}
