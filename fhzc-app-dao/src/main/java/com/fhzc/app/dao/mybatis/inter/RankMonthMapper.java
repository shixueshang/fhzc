package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.RankMonth;
import com.fhzc.app.dao.mybatis.model.RankMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RankMonthMapper {
    int countByExample(RankMonthExample example);

    int deleteByExample(RankMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RankMonth record);

    int insertSelective(RankMonth record);

    List<RankMonth> selectByExampleWithRowbounds(RankMonthExample example, RowBounds rowBounds);

    List<RankMonth> selectByExample(RankMonthExample example);

    List<RankMonth> selectDistinctYearMonth();

    RankMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RankMonth record, @Param("example") RankMonthExample example);

    int updateByExample(@Param("record") RankMonth record, @Param("example") RankMonthExample example);

    int updateByPrimaryKeySelective(RankMonth record);

    int updateByPrimaryKey(RankMonth record);
}