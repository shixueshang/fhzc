package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ReportService;
import com.fhzc.app.dao.mybatis.inter.ReportMapper;
import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.model.ReportExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
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
        ReportExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
        List<Report> list = reportMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Report>(page, size, list.size(), list);
    }

    @Override
    public List<Report> getRecommendReportList(){
        ReportExample example = new ReportExample();
        ReportExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("ctime desc");
        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
        criteria.andIsRecommendEqualTo(Const.YES_OR_NO.YES);
        RowBounds rowBounds = new RowBounds(0, 3);

        return reportMapper.selectByExampleWithBLOBsWithRowbounds(example,rowBounds);
    }

    @Override
    public Report getReport(Integer id) {
        ReportExample example = new ReportExample();
        ReportExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        if(reportMapper.countByExample(example) > 0) {
            return reportMapper.selectByPrimaryKey(id);
        }else{
            return null;
        }
    }
}
