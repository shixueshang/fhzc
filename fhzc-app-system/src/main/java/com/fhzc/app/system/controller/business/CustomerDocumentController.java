package com.fhzc.app.system.controller.business;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.function.ExecuteScoreFUNC;
import com.fhzc.app.system.service.CustomerDocumentService;

/**
 * Created by Double_J on 2016/7/22
 */
@Controller
@RequestMapping(value = "business/customerdocument")
public class CustomerDocumentController extends BaseController {

    @Resource
    private CustomerDocumentService customerDocumentService;

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
			if(new ExecuteScoreFUNC().insertScore()){
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
	
	/**6
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
			if(new ExecuteScoreFUNC().insertScore()){
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
}
