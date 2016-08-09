package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.PushToken;
import com.fhzc.app.dao.mybatis.model.PushTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PushTokenMapper {
    int countByExample(PushTokenExample example);

    int deleteByExample(PushTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PushToken record);

    int insertSelective(PushToken record);

    List<PushToken> selectByExampleWithRowbounds(PushTokenExample example, RowBounds rowBounds);

    List<PushToken> selectByExample(PushTokenExample example);

    PushToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PushToken record, @Param("example") PushTokenExample example);

    int updateByExample(@Param("record") PushToken record, @Param("example") PushTokenExample example);

    int updateByPrimaryKeySelective(PushToken record);

    int updateByPrimaryKey(PushToken record);
}