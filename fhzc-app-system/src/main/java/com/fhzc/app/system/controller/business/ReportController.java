package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.FocusService;
import com.fhzc.app.system.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 投研报告
 * Created by lihongde on 2016/7/15.
 */
@Controller
@RequestMapping(value = "business/report")
public class ReportController extends BaseController {

    @Resource
    private ReportService reportService;

    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private FocusService focusService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看投研报告列表")
    public ModelAndView listReport(){
        ModelAndView mav = new ModelAndView("business/report/list");
        PageableResult<Report> pageableResult =  reportService.findPageReports(page, size);
        for (Report report : pageableResult.getItems()) {
        	List<Focus> focuses = focusService.findFocusByType(Const.FOCUS_TYPE.REPORT, report.getId());
        	report.setFocusNum(focuses.size() > 0 ? focuses.size() : 0);
		}
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reports", pageableResult.getItems());
        mav.addObject("reportTypes", dictionaryService.findDicByType(Const.DIC_CAT.REPORT_CATEGORY));
        mav.addObject("url", "business/report");
        return mav;
    }

    @RequestMapping(value = "/pub")
    public ModelAndView pub(){
        ModelAndView mav = new ModelAndView("business/report/add");
        mav.addObject("reportTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.REPORT_CATEGORY)));
        mav.addObject("url", "business/report");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改报告")
    public String addOrUpdateReport(Report report, MultipartFile coverFile){

        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            report.setCover(coverPath + coverName);
        }


        report.setCtime(new Date());
        report.setIsDel(Const.YES_OR_NO.NO);
        reportService.addOrUpdateReport(report);

        return "redirect:/business/report/list";
    }

    /**
     * 报告编辑
     * @param id
     * @return
     */
    @RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("/business/report/add");
        mav.addObject("report", reportService.getReport(id));
        mav.addObject("reportTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.REPORT_CATEGORY)));
        return mav;
    }
}
