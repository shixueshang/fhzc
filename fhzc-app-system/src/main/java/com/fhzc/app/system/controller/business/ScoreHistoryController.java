package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.CustomerScore;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.ContractService;
import com.fhzc.app.system.service.CustomerService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.ProductService;
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
    
    @Resource
    private ContractService contractService;
    
    @Resource
    private ProductService productService;

    /**
     * 积分历史导入页面
     * @return
     */
    @RequestMapping(value = "/importoradd", method = RequestMethod.GET)
    public ModelAndView importorAddScore(){
        ModelAndView mav = new ModelAndView("business/addscorehistory/importor");
        mav.addObject("url","business/score/importoradd");
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
        mav.addObject("url","business/addscorehistory");
        return mav;
    }
    
    /**
     * 权益消费导入页面
     * @return
     */
    @RequestMapping(value = "/importorconsume", method = RequestMethod.GET)
    public ModelAndView importorConsumeScore(){
        ModelAndView mav = new ModelAndView("business/consumescorehistory/importor");
        mav.addObject("url","business/score/importorconsume");
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
        mav.addObject("url","business/consumescorehistory");
        return mav;
    }

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView("business/score/list");
        mav.addObject("url", "business/score");
        return mav;
    }
    
    @RequestMapping(value = "/listpending" ,method = RequestMethod.GET)
    public ModelAndView listpending(){
        ModelAndView mav = new ModelAndView("business/score/listpending");
        mav.addObject("url", "business/score");
        return mav;
    }

    
    /**
     * 积分列表
     * @param name  客户姓名
     * @param fromType 积分变动原因
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看积分列表")
    public ModelAndView findScore(String name, Integer fromType){
        ModelAndView mav = new ModelAndView("business/score/list");
        List<User> users = new ArrayList<User>();
        
        if(StringUtils.isNotEmpty(name)){
            users = userService.getUsersByName(name);
        }
        List<Integer> userIds = new ArrayList<Integer>();
        for(User user : users){
            userIds.add(user.getUid());
        }
        PageableResult<ScoreHistory> pageableResult = scoreService.findPageScore(userIds, name, fromType, null, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        List<ScoreHistory> list = new ArrayList<ScoreHistory>();
        List<Contract> clist = new ArrayList<Contract>();
        for(ScoreHistory history : pageableResult.getItems()){
            User customer = userService.getUser(history.getUid());
            history.setCustomerName(customer.getRealname());
            clist = contractService.getContract(history.getUid(), history.getEventId());
            if(clist.isEmpty()){
            	
            }
            list.add(history);
        }

        mav.addObject("scores", list);
        mav.addObject("scoreStatus", dictionaryService.findDicByType(Const.DIC_CAT.SCORE_STATUS));
        mav.addObject("fromTypes", dictionaryService.findDicByType(Const.DIC_CAT.SCORE_FROM_TYPE));
        mav.addObject("url", "business/score");
        return mav;
    }
    /**
     * 积分审批列表
     * @param name  客户姓名
     * @param isApprove 审批状态
     * @return
     */
    @RequestMapping(value = "/findpending", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看积分审批列表")
    public ModelAndView findPendingScore(String name, Integer isApprove){
        ModelAndView mav = new ModelAndView("business/score/listpending");
        List<User> users = new ArrayList<User>();
        if(StringUtils.isNotEmpty(name)){
            users = userService.getUsersByName(name);
        }
        List<Integer> userIds = new ArrayList<Integer>();
        for(User user : users){
            userIds.add(user.getUid());
        }
        PageableResult<ScoreHistory> pageableResult = scoreService.findPageScore(userIds, name, 1, isApprove, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        List<ScoreHistory> list = new ArrayList<ScoreHistory>();
        List<Contract> clist = new ArrayList<Contract>();
        List<Dictionary> dlist = new ArrayList<Dictionary>();
        for(ScoreHistory history : pageableResult.getItems()){
            User customer = userService.getUser(history.getUid());
            history.setCustomerName(customer.getRealname());
            clist = contractService.getContract(history.getUid(), history.getEventId());
            if(!(clist.isEmpty())){
            	for (Contract contract : clist) {
            		history.setAmount(contract.getAmountRmb());
            		history.setPeriod(contract.getPeriod());
            		history.setProductName(productService.getProduct(contract.getProductId()).getName());
            		dlist = dictionaryService.findDicByTypeAndValue(Const.DIC_CAT.PRODUCT_TYPE,productService.getProduct(contract.getProductId()).getProductType());
            		if(!(dlist.isEmpty())){
            			for (Dictionary dictionary : dlist) {
    					history.setProductType(dictionary.getKey());	
    					}
            		}
				}
            }
            list.add(history);
        }
        mav.addObject("scores", list);
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
     * 积分审批失败
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

    /**
     * 积分查询
     * @param name
     * @return
     */
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
        cs.setTotalScore(scoreService.getTotalScore(user.getUid()));
        cs.setAvaliableScore(scoreService.getAvailableScore(user.getUid()));
        cs.setFrozeScore(scoreService.getFrozenScore(user.getUid()));
        cs.setExpiredScore(scoreService.getExpiredScore(user.getUid()));
        cs.setWillExpireScore(scoreService.getWillExpiredScore(user.getUid()));
        cs.setConsumeScore(scoreService.getConsumeScore(user.getUid()));
        return cs;
    }
}
