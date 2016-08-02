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
     * 权益消费导入页面
     * @return
     */
    @RequestMapping(value = "/importorconsume", method = RequestMethod.GET)
    public ModelAndView importorConsumeScore(){
        ModelAndView mav = new ModelAndView("business/consumescorehistory/importor");
        return mav;
    }
    
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importconsume", method = RequestMethod.POST)
    @ResponseBody
//    public Map<String, Map<String, Object>> importExcel(MultipartFile multiFile){
//       	Map<String,Map<String,Object>> result = new HashMap<String,Map<String,Object>>();
//       	Map<String,Object> map = new HashMap<String, Object>();
//        try {
//            result = scoreHistoryService.importExcel(multiFile);
//            map.put("flag", true);
//            result.put("success", map);
//        } catch (Exception e) {
//            logger.error("导入失败");
//            map.put("flag", false);
//            result.put("success", map);
//            e.printStackTrace();
//        }
//        return result;
//    }
    public Map<String, Object> importExcelConsume(MultipartFile multiFile){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = scoreHistoryService.importExcelFileConsume(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }
    
    
    /**
     * excel导入
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/importadd", method = RequestMethod.POST)
    @ResponseBody

    public Map<String, Object> importExcelAdd(MultipartFile multiFile){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = scoreHistoryService.importExcelFileAdd(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }
}
