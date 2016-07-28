package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Level;
import com.fhzc.app.dao.mybatis.model.LevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LevelMapper {
    int countByExample(LevelExample example);

    int deleteByExample(LevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Level record);

    int insertSelective(Level record);

    List<Level> selectByExampleWithRowbounds(LevelExample example, RowBounds rowBounds);

    List<Level> selectByExample(LevelExample example);

    Level selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Level record, @Param("example") LevelExample example);

    int updateByExample(@Param("record") Level record, @Param("example") LevelExample example);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);
}