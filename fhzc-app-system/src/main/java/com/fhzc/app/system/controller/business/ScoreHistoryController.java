package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.CustomerScore;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.CustomerService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.ScoreHistoryService;
import com.fhzc.app.system.service.ScoreService;
import com.fhzc.app.system.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Double_J on 2016/7/29
 */
@Controller
@RequestMapping(value = "business/score")
public class ScoreHistoryController extends BaseController {

    @Resource
    private ScoreHistoryService scoreHistoryService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private UserService userService;
    
    @Resource
    private CustomerService customerService;

    /**
     * 积分历史导入页面
     * @return
     */
    @RequestMapping(value = "/importoradd", method = RequestMethod.GET)
    public ModelAndView importorAddScore(){
        ModelAndView mav = new ModelAndView("business/addscorehistory/importor");
        return mav;
    }
    
    /**
     * 积分历史excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importadd", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "积分历史导入")
    public ModelAndView importExcelAdd(MultipartFile multiFile){
    	Map<String, Object> result = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("business/addscorehistory/importor");
        try {
            result = scoreHistoryService.importExcelFileAdd(multiFile);
            result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
        }
        return mav;
    }
    
    /**
     * 权益消费导入页面
     * @return
     */
    @RequestMapping(value = "/importorconsume", method = RequestMethod.GET)
    public ModelAndView importorConsumeScore(){
        ModelAndView mav = new ModelAndView("business/consumescorehistory/importor");
        return mav;
    }
    
    /**
     * 权益消费excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importconsume", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "权益消费导入")
    public ModelAndView importExcelConsume(MultipartFile multiFile){
    	Map<String, Object> result = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("business/consumescorehistory/importor");
        try {
            result = scoreHistoryService.importExcelFileConsume(multiFile);
            result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
        }
        return mav;
    }

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("business/score/list");
        mav.addObject("url", "business/score");
        return mav;
    }

    /**
     * 积分列表
     * @param identity  身份证
     * @param isApprove 审批状态
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看积分列表")
    public ModelAndView findScore(String identity, Integer isApprove){
        ModelAndView mav = new ModelAndView("business/score/list");
        List<User> users = new ArrayList<User>();
        List<Customer> customers = new ArrayList<Customer>();
        List<Integer> customerIds = new ArrayList<Integer>();
        String customerName = "";       
        if(StringUtils.isNotBlank(identity)){
          users = userService.getUsersByName(identity.trim());
    	  if(users.isEmpty()){
          	return mav;
          }else{
    	     for(User user : users){
    	    	if( customerService.getCustomerByUid(user.getUid(), null) == null){
    	    		return mav;
    	    	}
    	    	customerIds.add(customerService.getCustomerByUid(user.getUid(),null).getCustomerId());
    	     }
          }
        }
        PageableResult<ScoreHistory> pageableResult = scoreService.findPageScores(customerIds, isApprove, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("scores", pageableResult.getItems());
        if(StringUtils.isBlank(identity)){
        	customers = customerService.findAllCustomer();
        	for(ScoreHistory score : pageableResult.getItems()){
        		for (Customer customer : customers) {
					if(customer.getUid() == score.getUid()){
						customerName = userService.getUser(customer.getUid()).getRealname();
						score.setCustomerName(customerName);
					}
				}
        		
        	}
        }else{
        	for(ScoreHistory score : pageableResult.getItems()){
        		for (User user : users) {
        			if(user.getUid() == score.getUid()){
						score.setCustomerName(user.getRealname());
					}
        			
				}
        	}
        }
        mav.addObject("isApprove", isApprove);
        mav.addObject("scoreStatus", dictionaryService.findDicByType(Const.DIC_CAT.SCORE_STATUS));
        mav.addObject("fromTypes", dictionaryService.findDicByType(Const.DIC_CAT.SCORE_FROM_TYPE));
        mav.addObject("url", "business/score");
        return mav;
    }

    /**
     * 积分审批
     * @param scoreId
     * @return
     */
    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    @SystemControllerLog(description = "积分审批")
    public String approve(@PathVariable(value = "id") Integer scoreId){
        scoreService.approve(scoreId);
        return "redirect:/business/score/list";
    }
    
    /**
     * 积分审批
     * @param scoreId
     * @return
     */
    @RequestMapping(value = "/approveFailed/{id}", method = RequestMethod.GET)
    @SystemControllerLog(description = "积分审批失败")
    public String approveFailed(@PathVariable(value = "id") Integer scoreId){
        scoreService.approveFailed(scoreId);
        return "redirect:/business/score/list";
    }

    /**
     * 批量审批
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchApprove", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "积分批量审批")
    public Object batchApprove(@RequestParam(value = "ids[]") Integer[] ids){
        for(Integer id : ids){
            scoreService.approve(id);
        }
        return true;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView query(@RequestParam(required = false) String name){
        ModelAndView mav = new ModelAndView("/business/score/query");
        PageableResult<Customer> pageableResult = customerService.findPageCustomers(page, size);

        List<CustomerScore> list = new ArrayList<CustomerScore>();
        if(name == null || "".equals(name.trim())){
            for(Customer customer :  pageableResult.getItems()){
                User user  = userService.getUser(customer.getUid());
                    CustomerScore cs =  buildCustomerScore(customer, user);
                    list.add(cs);
            }
        }else{
            for(Customer customer :  pageableResult.getItems()){
                User user  = userService.getUser(customer.getUid());
                if(name.trim().equals(user.getRealname())){
                    CustomerScore cs =  buildCustomerScore(customer, user);
                    list.add(cs);
                }
            }
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("customers", list);
        mav.addObject("url", "business/score");
        return mav;
    }

    private CustomerScore buildCustomerScore(Customer customer, User user){
        CustomerScore cs = new CustomerScore();
        cs.setCustomerId(customer.getCustomerId());
        cs.setCustomerName(user.getRealname());
        cs.setAvaliableScore(scoreService.sumScore(scoreService.getAvailableList(user.getUid())));
        cs.setFrozeScore(scoreService.sumScore(scoreService.getFrozen(user.getUid())));
        cs.setWillExpireScore(scoreService.sumScore(scoreService.getExpiredList(user.getUid())));
        return cs;
    }
}
