package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.vo.FocusVo;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.system.service.CustomerService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.FocusService;
import com.fhzc.app.system.service.ReportService;
import com.fhzc.app.system.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
    
    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

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
    
    /**
     * 投研报告关注列表
     * @return
     */
    @RequestMapping(value = "/focusList", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看投研报告关注列表")
    public ModelAndView listReportFocus(){
        ModelAndView mav = new ModelAndView("business/report/focusList");
        mav.addObject("url", "business/report");
        return mav;
    }
    
    /**
     * 投研报告关注列表
     * @return
     */
    @RequestMapping(value = "/focusFind", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看投研报告关注列表")
    public ModelAndView listReportFocus(String reportName){
        ModelAndView mav = new ModelAndView("business/report/focusList");
        List<Integer> rids = new ArrayList<Integer>();
        List<Report> reports = new ArrayList<Report>();
        if(StringUtils.isNotBlank(reportName)){
        	reports = reportService.findAllReport();
        	for (Report report : reports) {
				if(report.getName().contains(reportName.trim())){
					rids.add(report.getId());
				}
			}
        	if(rids.isEmpty()){
        		return mav;
        	}
        }
        PageableResult<Focus> presult = focusService.getFocusByType(Const.FOCUS_TYPE.REPORT, rids, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, presult));
        mav.addObject("focuses", getFocusVos(presult));
        mav.addObject("url", "business/report");
        return mav;
    }
    
    List<FocusVo> getFocusVos(PageableResult<Focus> presult){
        List<FocusVo> vos = new LinkedList<>();
        if (!CollectionUtils.isEmpty(presult.getItems())){
            for (Focus focus : presult.getItems()){
                FocusVo vo = new FocusVo();
                vo.setId(focus.getId());
                vo.setFocusTime(focus.getCtime());
                if (focus.getStatus() == 0){
                    vo.setStatus("取消关注");
                } else if (focus.getStatus() == 1) {
                    vo.setStatus("关注");
                }
                User user = null;
                try{
                    user = userService.getUser(focus.getUid());
                } catch (Exception ex){
                    continue;
                }
                vo.setUserName(user.getRealname());
                Report r = reportService.getReport(focus.getFid());
                vo.setContentName(r.getName());
                if ("customer".equalsIgnoreCase(user.getLoginRole().trim().toLowerCase())){
                    Customer customer = customerService.getCustomerByUid(user.getUid(), null);
                    if (customer == null){
                        logger.error("Could not find customer with id {}", user.getUid());
                        continue;
                    }
                    vo.setUserType("single".equals(customerService.getCustomerByUid(user.getUid(), null).getCustomerType())?"个人客户":"机构客户");
                } else {
                    vo.setUserType("理财师");
                }
                vos.add(vo);
            }
        }
        return vos;
    }
}
