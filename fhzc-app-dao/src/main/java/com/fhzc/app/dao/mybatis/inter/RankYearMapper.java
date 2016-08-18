package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.RankYear;
import com.fhzc.app.dao.mybatis.model.RankYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RankYearMapper {
    int countByExample(RankYearExample example);

    int deleteByExample(RankYearExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RankYear record);

    int insertSelective(RankYear record);

    List<RankYear> selectByExampleWithRowbounds(RankYearExample example, RowBounds rowBounds);

    List<RankYear> selectByExample(RankYearExample example);

    RankYear selectByPrimaryKey(Integer id);

    List<RankYear> selectDistinctYear();

    int updateByExampleSelective(@Param("record") RankYear record, @Param("example") RankYearExample example);

    int updateByExample(@Param("record") RankYear record, @Param("example") RankYearExample example);

    int updateByPrimaryKeySelective(RankYear record);

    int updateByPrimaryKey(RankYear record);
}