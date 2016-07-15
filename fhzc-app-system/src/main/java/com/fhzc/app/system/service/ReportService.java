package com.fhzc.app.system.service;

import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.mybatis.model.Report;

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
    void addReport(Report report);
}
