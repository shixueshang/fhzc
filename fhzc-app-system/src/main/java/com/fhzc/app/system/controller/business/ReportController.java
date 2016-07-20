package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.system.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 投研报告
 * Created by lihongde on 2016/7/15.
 */
@Controller
@RequestMapping(value = "business/report")
public class ReportController extends BaseController {

    @Resource
    private ReportService reportService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listReport(){
        ModelAndView mav = new ModelAndView("business/report/list");
        PageableResult<Report> pageableResult =  reportService.findPageReports(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reports", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/pub")
    public String pub(){
        return "business/report/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addOrUpdateReport(Report report, MultipartFile coverFile){
        ModelAndView mav = new ModelAndView("business/report/list");
        String coverName = FileUtil.generatePictureName(coverFile);
        String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
        FileUtil.transferFile(coverPath, coverName, coverFile);
        report.setCover(coverPath + coverName);
        report.setCtime(new Date());
        report.setIsDel(Const.YES_OR_NO.NO);
        reportService.addOrUpdateReport(report);
        return mav;
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
        return mav;
    }
}
