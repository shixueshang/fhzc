package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.AssetsHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AssetsHistoryMapper {
    int countByExample(AssetsHistoryExample example);

    int deleteByExample(AssetsHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetsHistory record);

    int insertSelective(AssetsHistory record);

    List<AssetsHistory> selectByExampleWithRowbounds(AssetsHistoryExample example, RowBounds rowBounds);

    List<AssetsHistory> selectByExample(AssetsHistoryExample example);

    AssetsHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetsHistory record, @Param("example") AssetsHistoryExample example);

    int updateByExample(@Param("record") AssetsHistory record, @Param("example") AssetsHistoryExample example);

    int updateByPrimaryKeySelective(AssetsHistory record);

    int updateByPrimaryKey(AssetsHistory record);
}