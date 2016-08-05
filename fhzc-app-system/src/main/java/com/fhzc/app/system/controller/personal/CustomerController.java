package com.fhzc.app.system.controller.personal;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * @return
     */
    @RequestMapping(value = "/single/find", method = RequestMethod.GET)
    public ModelAndView findSingleCustomers(String name){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        PageableResult<User> pageableResult = userService.findPageUsers(name, page, size);
        List<Customer> customerList = new ArrayList<Customer>();
        List<Map<String, Object>> scores = new ArrayList<Map<String, Object>>();
        for(User user : pageableResult.getItems()){
            Customer customer = customerService.getCustomerByUid(user.getUid(), Const.CUSTOMER_TYPE.SINGLE_CUSTOMER);
            if(customer != null){
                customerList.add(customer);

                Map<String, Object> scoreMap = new HashMap<String, Object>();
                scoreMap.put("customerId", customer.getCustomerId());
                scoreMap.put("availableScore", scoreService.sumScore(scoreService.getAvailableList(customer.getUid())));
                scoreMap.put("frozenScore", scoreService.sumScore(scoreService.getFrozen(customer.getUid())));
                scores.add(scoreMap);
            }
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("customers", customerList);
        mav.addObject("users", pageableResult.getItems());
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
        mav.addObject("availableScore", JSON.toJSON(scoreService.sumScore(scoreService.getAvailableList(customer.getUid()))));
        mav.addObject("frozenScore", JSON.toJSON(scoreService.sumScore(scoreService.getFrozen(customer.getUid()))));

        PlannerCustomer plannerCustomer = customerService.getPlannerByCustomerId(customer.getCustomerId());
        Planner planner = plannerService.getPlanner(plannerCustomer.getPlannerId());
        mav.addObject("planner", JSON.toJSON(planner));
        mav.addObject("plannerUser", JSON.toJSON(userService.getUser(planner.getUid())));
        return mav;
    }

    @RequestMapping(value = "/single/update", method = RequestMethod.POST)
    public ModelAndView update(Customer customer, User user){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        customerService.addOrUpdateCustomer(customer);
        userService.addOrUpdateUser(user);
        return mav;
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
     * @return
     */
    @RequestMapping(value = "/organ/find", method = RequestMethod.GET)
    public ModelAndView findOrganCustomers(String name){
        ModelAndView mav = new ModelAndView("personal/customer/organCustomerList");
        PageableResult<User> pageableResult = userService.findPageUsers(name, page, size);
        List<Customer> customerList = new ArrayList<Customer>();
        List<Map<String, Object>> scores = new ArrayList<Map<String, Object>>();
        for(User user : pageableResult.getItems()){
            Customer customer = customerService.getCustomerByUid(user.getUid(), Const.CUSTOMER_TYPE.ORGAN_CUSTOMER);
            if(customer != null){
                customerList.add(customer);

                Map<String, Object> scoreMap = new HashMap<String, Object>();
                scoreMap.put("customerId", customer.getCustomerId());
                scoreMap.put("availableScore", scoreService.sumScore(scoreService.getAvailableList(customer.getUid())));
                scoreMap.put("frozenScore", scoreService.sumScore(scoreService.getFrozen(customer.getUid())));
                scores.add(scoreMap);
            }

        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("customers", customerList);
        mav.addObject("users", pageableResult.getItems());
        mav.addObject("customerLevel", dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL));
        mav.addObject("passports", dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT));
        mav.addObject("scores", scores);
        mav.addObject("url", "personal/customer");
        return mav;
    }

    @RequestMapping(value = "/organ/detail/{id}", method = RequestMethod.GET)
    public ModelAndView editOrganCustomer(@PathVariable(value = "id") Integer customerId){
        ModelAndView mav = new ModelAndView("personal/customer/organCustomerAdd");
        Customer customer = customerService.getCustomer(customerId);
        mav.addObject("customer", JSON.toJSON(customer));
        mav.addObject("user", JSON.toJSON(userService.getUser(customer.getUid())));
        mav.addObject("passports", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT)));
        mav.addObject("customerLevels", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("availableScore", JSON.toJSON(scoreService.sumScore(scoreService.getAvailableList(customer.getUid()))));
        mav.addObject("frozenScore", JSON.toJSON(scoreService.sumScore(scoreService.getFrozen(customer.getUid()))));

        PlannerCustomer plannerCustomer = customerService.getPlannerByCustomerId(customer.getCustomerId());
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
    public String delete(@PathVariable(value = "id") Integer id, RedirectAttributes attr){
        CustomerOrgan customerOrgan = customerService.getRightsEnjoyPerson(id);
        customerService.delete(id);
        attr.addAttribute("id", customerOrgan.getCustomerId());
        return "redirect:/personal/customer/organ/enjoy/list/{id}";
    }

}
