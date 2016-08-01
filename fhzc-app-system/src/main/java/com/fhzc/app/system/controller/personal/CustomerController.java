package com.fhzc.app.system.controller.personal;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerCustomer;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        mav.addObject("url", "personal/customer");
        return mav;
    }

    /**
     * 根据姓名查询客户
     * @param name
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView findCustomers(String name){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        PageableResult<User> pageableResult = userService.findPageUsers(name, page, size);
        List<Customer> customerList = new ArrayList<Customer>();
        List<Map<String, Object>> scores = new ArrayList<Map<String, Object>>();
        for(User user : pageableResult.getItems()){
            Customer customer = customerService.getCustomerByUid(user.getUid());
            customerList.add(customer);

            Map<String, Object> scoreMap = new HashMap<String, Object>();
            scoreMap.put("customerId", customer.getCustomerId());
            scoreMap.put("availableScore", scoreService.sumScore(scoreService.getAvailableList(customer.getUid())));
            scoreMap.put("frozenScore", scoreService.sumScore(scoreService.getFrozen(customer.getUid())));
            scores.add(scoreMap);
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

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Integer customerId){
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(Customer customer, User user){
        ModelAndView mav = new ModelAndView("personal/customer/singleCustomerList");
        customerService.addOrUpdateCustomer(customer);
        userService.addOrUpdateUser(user);
        return mav;
    }

}
