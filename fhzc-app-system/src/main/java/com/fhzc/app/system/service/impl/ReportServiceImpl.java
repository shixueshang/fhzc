package com.fhzc.app.system.service.impl;

import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.dao.mybatis.inter.ReportMapper;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.model.ReportExample;
import com.fhzc.app.system.service.ReportService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/15.
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Override
    public PageableResult<Report> findPageReports(int page, int size) {
        ReportExample example = new ReportExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Report> list = reportMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Report>(page, size, list.size(), list);
    }

    @Override
    public void addOrUpdateReport(Report report) {
        Integer id = report.getId();
        if(id == null){
            reportMapper.insert(report);
        }else{
            reportMapper.updateByPrimaryKey(report);
        }
    }

    @Override
    public Report getReport(Integer id) {
        return reportMapper.selectByPrimaryKey(id);
    }
}
