package com.fhzc.app.system.controller.business;

import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.ScoreHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Double_J on 2016/7/29
 */
@Controller
@RequestMapping(value = "business/scorehistory")
public class ScoreHistoryController extends BaseController {

    @Resource
    private ScoreHistoryService scoreHistoryService;

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
            mav.addAllObjects(result);
        }
        return mav;
    }
}
