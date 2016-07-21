package com.fhzc.app.api.service;

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
     * 获得推荐列表
     * @return
     */
    public PageableResult<Report> getRecommendReportList();

    /**
     * 获得报告
     * @param id
     * @return
     */
    Report getReport(Integer id);
}
