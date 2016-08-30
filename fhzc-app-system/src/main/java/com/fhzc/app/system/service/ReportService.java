package com.fhzc.app.system.service;

import java.util.List;

import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/7/15.
 */
public interface ReportService {

    /**
     * 获得投研报告列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Report> findPageReports(int page, int size);

    /**
     * 添加投研报告
     * @param report
     */
    void addOrUpdateReport(Report report);

    /**
     * 获得报告
     * @param id
     * @return
     */
    Report getReport(Integer id);
    
    /**
     * 获得所有投研报告
     * @param page
     * @param size
     * @return
     */
    List<Report> findAllReport();
    
    /**
     * 根据报告类型获得权益
     * @param typeId
     * @return
     */
    List<Report> getReportByType(String typeId);
}
