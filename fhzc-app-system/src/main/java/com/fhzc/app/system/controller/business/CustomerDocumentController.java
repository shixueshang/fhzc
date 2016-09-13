package com.fhzc.app.system.controller.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.AssetsService;
import com.fhzc.app.system.service.CustomerDocumentService;
import com.fhzc.app.system.service.CustomerService;
import com.fhzc.app.system.service.ProductService;
import com.fhzc.app.system.service.ScoreHistoryService;

/**
 * Created by Double_J on 2016/7/22
 */
@Controller
@RequestMapping(value = "business/customerdocument")
public class CustomerDocumentController extends BaseController {

    @Resource
    private CustomerDocumentService customerDocumentService;
    
    @Resource
	private AssetsService assetsService;

	@Resource
	private ScoreHistoryService scoreHistoryService;

	@Resource
	private CustomerService customerService;

	@Resource
	private ProductService productService;

    /**
     * 个人客户导入页面
     * @return
     */
    @RequestMapping(value = "/importorpersonal", method = RequestMethod.GET)
    public ModelAndView importorPersonalCustomerDocument(){
        ModelAndView mav = new ModelAndView("business/customerdocument/importorpersonal");
        mav.addObject("url", "business/customerdocument/importorpersonal");
        return mav;
    }
    
    /**
     * 个人客户excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importpersonal", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView importExcelPersonal(MultipartFile multiFile){
    	Map<String, Object> result = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("business/customerdocument/importorpersonal");
        try {
            result = customerDocumentService.importExcelFilePersonal(multiFile);
            result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
        }
        mav.addObject("url","business/customerdocument");
        return mav;
    }
    
    /**
     * 机构客户导入页面
     * @return
     */
    @RequestMapping(value = "/importoragent", method = RequestMethod.GET)
    public ModelAndView importorAgentCustomerDocument(){
        ModelAndView mav = new ModelAndView("business/customerdocument/importoragent");
        mav.addObject("url", "business/customerdocument/importoragent");
        return mav;
    }
    
    /**
     * 机构客户excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importagent", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView importExcelAgent(MultipartFile multiFile){
    	Map<String, Object> result = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("business/customerdocument/importoragent");
        try {
            result = customerDocumentService.importExcelFileAgent(multiFile);
            result.put("success", true);
            mav.addAllObjects(result);
        } catch (Exception e) {
            logger.error("导入失败" + e.getMessage() );
            result.put("success", false);
            result.put("error_message", e.getMessage());
            mav.addAllObjects(result);
        }
        mav.addObject("url","business/customerdocument");
        return mav;
    }
    
    /**
	 * 每日任务计算待审批积分
	 *
	 * @param multiFile
	 * @return
	 */
	@RequestMapping(value = "/approvalScorePersonal", method = RequestMethod.GET)
	@ResponseBody
	@SystemControllerLog(description = "每日任务计算待审批积分")
	public Map<String, Object> approvalScorePersonal() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(insertScore()){
				result.put("result", true);
			}else{
				result.put("result", false);
			}
		} catch (Exception e) {
			logger.error("执行失败"+ e.getMessage() );
			result.put("result", false);
		}
		return result;
	}
	
	/**
	 * 每日任务计算待审批积分
	 *
	 * @param multiFile
	 * @return
	 */
	@RequestMapping(value = "/approvalScoreAgent", method = RequestMethod.GET)
	@ResponseBody
	@SystemControllerLog(description = "每日任务计算待审批积分")
	public Map<String, Object> approvalScoreAgent() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(insertScore()){
				result.put("result", true);
			}else{
				result.put("result", false);
			}
		} catch (Exception e) {
			logger.error("执行失败"+ e.getMessage() );
			result.put("result", false);
		}
		return result;
	}
	
	public boolean insertScore() {
		// 查询assets_history中当天的并且已经成立的未过期的产品数据
		boolean result=false;
		try {
			List<AssetsHistory> list = assetsService.findAssetsForScore();
			for (AssetsHistory asset : list) {
				Product product = productService.getProduct(asset.getProductId());
				// 根据客户和产品id查询积分历史记录
				Customer customer = customerService.getCustomer(asset.getCustomerId());
				List<ScoreHistory> scoreHistories = scoreHistoryService.findScoreByProduct(customer.getUid(),
						asset.getProductId());
				if (scoreHistories.size() == 0) {
					// 把当前的asset记录插入到历史积分表

					ScoreHistory history = new ScoreHistory();
					history.setUid(customer.getUid());
					history.setScore(calculateScore(product, Integer.parseInt(asset.getPeriod()), asset.getAmount()));
					history.setStatus(Const.Score.ADD);
					history.setDetail("积分增加，类型：产品,名称：" + product.getName() + "");
					history.setEventId(product.getPid());
					history.setVaildTime(DateUtil.getDateNextDays(product.getExpiryDay(), 30));
					history.setFromType(Const.FROM_TYPE.PRODUCT);
					history.setIsApprove(Const.YES_OR_NO.NO);
					history.setIsVaild(Const.SCORE_VAILD.IS_VAILD);
					history.setCtime(new Date());
					scoreHistoryService.addHistoryScore(history);
				}

			}
			result=true;
			logger.info("task execute success");
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
			logger.error("task execute failure", e.getMessage());
		}
		return result;
	}
	
	/**
     * 计算产品积分
     * @param product
     * @param amount
     * @return
     */
    private Integer calculateScore(Product product, Integer term, Integer amount){
        float termFactor = 0;
        if(term >= 36){
            termFactor = 1.2f;
        }else if(term >= 12){
            termFactor = 1.0f;
        }else{
            termFactor = term / 12 ;
        }
        return new BigDecimal(amount).divide(new BigDecimal(500)).multiply(product.getScoreFactor()).multiply(new BigDecimal(termFactor)).setScale(0, RoundingMode.HALF_EVEN).intValue();
    }
}
