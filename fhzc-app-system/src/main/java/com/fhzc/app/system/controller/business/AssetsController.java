package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
        mav.addObject("url", "/business/assets");
        return mav;
    }
}
