package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.ContractService;
import com.fhzc.app.system.service.CustomerService;
import com.fhzc.app.system.service.PlannerService;
import com.fhzc.app.system.service.ProductService;
import com.fhzc.app.system.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Double_J on 2016/7/7 15:22
 */
@Controller
@RequestMapping(value = "business/contract")
public class ContractController extends BaseController {

    @Resource(name="contractService")
    private ContractService contractService;

    @Resource
    private UserService userService;
    
    @Resource
    private PlannerService plannerService;
    
    @Resource
    private ProductService productService;
    
    @Resource
    private CustomerService customerService;
    
    /**
     * 日表导入页面
     * @return
     */
    @RequestMapping(value = "/importor", method = RequestMethod.GET)
    public ModelAndView importorContract(){
        ModelAndView mav = new ModelAndView("business/contract/importor");
        return mav;
    }
    
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "导入日表")
    public ModelAndView importExcel(MultipartFile multiFile){
    	Map<String, Object> result = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("business/contract/importor");
        try {
            result = contractService.importExcelFile(multiFile);
            result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
            e.printStackTrace();
        }
        return mav;
    }
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listContracts(){
        ModelAndView mav = new ModelAndView("business/contract/list");
        PageableResult<Contract> pageableResult = contractService.findPageContracts(null,new ArrayList<Integer>(),page, size);
        List<Customer> customers = customerService.findAllCustomer();
        List<Product> products = productService.findAllProduct();
        List<Planner> planners = plannerService.findAllPlanner();
        for (Contract contract : pageableResult.getItems()) {
        	for (Customer customer : customers) {
				if(customer.getCustomerId() == contract.getCustomerId()){
					contract.setCustomerName(userService.getUser(customer.getUid()).getRealname());
				}
			}
        	for(Product product : products){
  				if(product.getPid() == contract.getProductId()){
  					contract.setProductName(product.getName());
  				}
  			} 
        	for (Planner planner : planners) {
      			if(planner.getId() == contract.getPlannerId()){
      				contract.setPlannerName(userService.getUser(planner.getUid()).getRealname());
      			}
        	 }
		}
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("contracts", pageableResult.getItems());
        mav.addObject("url", "business/contract");
        return mav;
    }

    /**
     * 按产品名称或理财师姓名查询合同列表
     * @param productName
     * @param plannerName
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询合同列表")
    public ModelAndView find(String productName, String plannerName){
        ModelAndView mav = new ModelAndView("/business/contract/list");
        List<User> users = new ArrayList<User>();
        List<Integer> plannerIds = new ArrayList<Integer>();
        Product pro = new Product();
        if(StringUtils.isNotBlank(plannerName)){
          users = userService.getUsersByName(plannerName.trim());
    	  if(users.isEmpty()){
          	return mav;
          }else{
    	     for(User user : users){
	        	if(plannerService.getPlannerByUid(user.getUid()) == null){
	        		return mav;
	        	} 
	        plannerIds.add(plannerService.getPlannerByUid(user.getUid()).getId());
    	     }
          }
        }
        if(StringUtils.isNotBlank(productName)){
        	 pro = productService.getProduct(productName.trim());
             if(pro == null){
             	return mav;
             }
        }
        PageableResult<Contract> pageableResult = contractService.findPageContracts(pro.getPid(), plannerIds, page, size);
        List<Contract> contracts = pageableResult.getItems();
        for(Contract contract : contracts){
            Planner planner = plannerService.getPlanner(contract.getPlannerId());
            if(planner != null){
            	contract.setPlannerName(userService.getUser(planner.getUid()).getRealname());
            }
            Product product = productService.getProduct(contract.getProductId());
            if(product != null){
            	contract.setProductName(product.getName());
            }
            Customer customer = customerService.getCustomer(contract.getCustomerId());
            if(customer != null){
            	contract.setCustomerName(userService.getUser(customer.getUid()).getRealname());
            }
            
        }
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("contracts", contracts);
        mav.addObject("url", "/business/contract");
        return mav;
    }
}
