package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.ScoreHistoryService;
import com.fhzc.app.system.service.ScoreService;
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

    /**
     * 积分列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listScore(){
        ModelAndView mav = new ModelAndView("business/score/list");
        PageableResult<ScoreHistory> pageableResult = scoreService.findPageScore(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("scores", pageableResult.getItems());
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
    public String approve(@PathVariable(value = "id") Integer scoreId){
        scoreService.approve(scoreId);
        return "redirect:/business/score/list";
    }

    /**
     * 批量审批
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchApprove", method = RequestMethod.GET)
    @ResponseBody
    public Object batchApprove(@RequestParam(value = "ids[]") Integer[] ids){
        for(Integer id : ids){
            scoreService.approve(id);
        }
        return true;
    }
}
