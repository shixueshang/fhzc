package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ScoreHistoryMapper {
    int countByExample(ScoreHistoryExample example);

    int deleteByExample(ScoreHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScoreHistory record);

    int insertSelective(ScoreHistory record);

    List<ScoreHistory> selectByExampleWithRowbounds(ScoreHistoryExample example, RowBounds rowBounds);

    List<ScoreHistory> selectByExample(ScoreHistoryExample example);

    ScoreHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScoreHistory record, @Param("example") ScoreHistoryExample example);

    int updateByExample(@Param("record") ScoreHistory record, @Param("example") ScoreHistoryExample example);

    int updateByPrimaryKeySelective(ScoreHistory record);

    int updateByPrimaryKey(ScoreHistory record);

}
