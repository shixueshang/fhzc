package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Report;
import com.fhzc.app.dao.mybatis.model.ReportExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ReportMapper {
    int countByExample(ReportExample example);

    int deleteByExample(ReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Report record);

    int insertSelective(Report record);

    List<Report> selectByExampleWithBLOBsWithRowbounds(ReportExample example, RowBounds rowBounds);

    List<Report> selectByExampleWithBLOBs(ReportExample example);

    List<Report> selectByExampleWithRowbounds(ReportExample example, RowBounds rowBounds);

    List<Report> selectByExample(ReportExample example);

    Report selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExampleWithBLOBs(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExample(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKeyWithBLOBs(Report record);

    int updateByPrimaryKey(Report record);
}