package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.ImMessage;
import com.fhzc.app.dao.mybatis.model.ImMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ImMessageMapper {
    int countByExample(ImMessageExample example);

    int deleteByExample(ImMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImMessage record);

    int insertSelective(ImMessage record);

    List<ImMessage> selectByExampleWithBLOBsWithRowbounds(ImMessageExample example, RowBounds rowBounds);

    List<ImMessage> selectByExampleWithBLOBs(ImMessageExample example);

    List<ImMessage> selectByExampleWithRowbounds(ImMessageExample example, RowBounds rowBounds);

    List<ImMessage> selectByExample(ImMessageExample example);

    ImMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    int updateByExample(@Param("record") ImMessage record, @Param("example") ImMessageExample example);

    int updateByPrimaryKeySelective(ImMessage record);

    int updateByPrimaryKeyWithBLOBs(ImMessage record);

    int updateByPrimaryKey(ImMessage record);
}