package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongde on 2016/8/4 13:27
 */
@Controller
@RequestMapping(value = "/business/assets")
public class AssetsController extends BaseController {

    @Resource
    private AssetsService assetsService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @Resource
    private PlannerService plannerService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private ProductService productService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private DepartmentService departmentService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listOrder(){
        return "business/assets/list";
    }

    /**
     * 按产品名称或客户姓名查询订单列表
     * @param productName
     * @param customerName
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询订单列表")
    public ModelAndView find(String productName, String customerName){
        ModelAndView mav = new ModelAndView("/business/assets/list");
        List<User> users = new ArrayList<User>();
        List<Integer> customerIds = new ArrayList<Integer>();
        Product pro = new Product();
        if(StringUtils.isNotBlank(customerName)){
        	users = userService.getUsersByName(customerName.trim());
	        if(users.isEmpty()){
	         	return mav;
	         }else{
	        	  for(User user : users){
	              	if(customerService.getCustomerByUid(user.getUid(), null) == null){
	              		return mav;
	              	}
	                customerIds.add(customerService.getCustomerByUid(user.getUid(), null).getCustomerId());
	              }
	         }
        }
        if(StringUtils.isNotBlank(productName)){
        	pro = productService.getProduct(productName.trim());
        	if(pro == null){
        		return mav;
        	}
        }
        PageableResult<AssetsHistory> pageableResult = assetsService.findPageAssets(pro== null ? null : pro.getPid(), customerIds, page, size);
        List<AssetsHistory> assetsHistories = new ArrayList<AssetsHistory>();
        List<AssetsHistory> assets = pageableResult.getItems();
        for(AssetsHistory assetsHistory : assets){
            Customer customer = customerService.getCustomer(assetsHistory.getCustomerId());
            assetsHistory.setCustomerNum(customer.getCbId());
            Planner planner = plannerService.getPlanner(assetsHistory.getPlannerId());
            if(planner != null){
                assetsHistory.setPlanner(userService.getUser(planner.getUid()).getRealname());
            }
            Product product = productService.getProduct(assetsHistory.getProductId());
            assetsHistory.setProductName(product.getName());
            assetsHistory.setProductCode(product.getCode());
            assetsHistories.add(assetsHistory);
        }
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("assets", assetsHistories);
        mav.addObject("assetsStatus", dictionaryService.findDicByType(Const.DIC_CAT.ASSETS_STATUS));
        mav.addObject("url", "business/assets");
        return mav;
    }

    /**
     * 客户资产持仓情况
     * @return
     */
    @RequestMapping(value = "/holdings/find", method = RequestMethod.GET)
    public ModelAndView holdings(@RequestParam( required = false) String name){
        ModelAndView mav = new ModelAndView("business/assets/holdings");
        PageableResult<Customer> pageableResult = customerService.findPageCustomers(page, size);

        List<CustomerHolding> list = new ArrayList<CustomerHolding>();
        for(Customer customer : pageableResult.getItems()){
            if(name == null || "".equals(name)){
                User user  = userService.getUser(customer.getUid());
                CustomerHolding holding = this.buildHolding(user, customer);
                list.add(holding);
            }else{
                User user  = userService.getUser(customer.getUid());
                if (name.equals(user.getRealname())) {
                    CustomerHolding holding = this.buildHolding(user, customer);
                    list.add(holding);
                }
            }

        }
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("customers", list);
        mav.addObject("url", "business/assets");
        return mav;
    }

    private CustomerHolding buildHolding(User user, Customer customer){
        CustomerHolding holding  = new CustomerHolding();
        holding.setCustomerId(customer.getCustomerId());
        holding.setCustomerName(user.getRealname());
        holding.setLevel(super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
        holding.setIdentity(user.getPassportCode());
        holding.setScore(scoreService.sumScore(scoreService.getAvailableList(user.getUid())));
        holding.setMobile(user.getMobile());
        PlannerCustomer pc = customerService.getPlannerByCustomerId(customer.getCustomerId(), Const.YES_OR_NO.YES);
        Planner planner = plannerService.getPlanner(pc.getPlannerId());
        holding.setPlanner(userService.getUser(planner.getUid()).getRealname());
        holding.setDepartment(departmentService.getDeparent(customer.getDepartmentId()).getTitle());
        return holding;
    }

    @RequestMapping(value = "/holdings/current", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "查看客户当前持仓")
    public AjaxJson currentHoldings(Integer customerId){
        //查询客户未过期或没有兑付记录的产品
        List<AssetsHistory> assetsHistories = assetsService.findCurrentHoldings(customerId);

        List<CurrentHistoryHoldings> result = new ArrayList<CurrentHistoryHoldings>();
        for(AssetsHistory assetsHistory : assetsHistories){
            CurrentHistoryHoldings holdings = new CurrentHistoryHoldings();
            Product product = productService.getProduct(assetsHistory.getProductId());
            holdings.setProductName(product.getName());
            holdings.setAmount(new BigDecimal(assetsHistory.getAmount()));
            holdings.setInvestTerm(assetsHistory.getPeriod());
            holdings.setBuyDay(product.getBuyDay());
            holdings.setDividendDay(product.getDividendDay());
            holdings.setPaymentDay(assetsHistory.getPaymentDate());
            holdings.setBank(assetsHistory.getBank());
            holdings.setBankAccount(assetsHistory.getBankAccount());
            holdings.setEarningRate(assetsHistory.getEarningRate());
            holdings.setFoundDay(assetsHistory.getProductFoundDay());
            holdings.setLot(assetsHistory.getLot());
            holdings.setSerial(assetsHistory.getSerial());
            result.add(holdings);
        }

        return new AjaxJson(true, result);
    }

    @RequestMapping(value = "/holdings/history", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "查看客户历史持仓")
    public AjaxJson historyHoldings(Integer customerId){
        //查询客户已过期并且有兑付记录的产品
        List<AssetsHistory> assetsHistories = assetsService.findHistoryHoldings(customerId);

        List<CurrentHistoryHoldings> result = new ArrayList<CurrentHistoryHoldings>();
        for(AssetsHistory assetsHistory : assetsHistories){
            CurrentHistoryHoldings holdings = new CurrentHistoryHoldings();
            Product product = productService.getProduct(assetsHistory.getProductId());
            holdings.setProductName(product.getName());
            holdings.setAmount(new BigDecimal(assetsHistory.getAmount()));
            holdings.setInvestTerm(assetsHistory.getPeriod());
            holdings.setBuyDay(product.getBuyDay());
            holdings.setDividendDay(product.getDividendDay());
            holdings.setPaymentDay(assetsHistory.getPaymentDate());
            holdings.setBank(assetsHistory.getBank());
            holdings.setBankAccount(assetsHistory.getBankAccount());
            holdings.setEarningRate(assetsHistory.getEarningRate());
            holdings.setFoundDay(assetsHistory.getProductFoundDay());
            holdings.setLot(assetsHistory.getLot());
           // holdings.setTotalAmount();
            result.add(holdings);
        }

        return new AjaxJson(true, result);
    }

}
