package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Areas;
import com.fhzc.app.dao.mybatis.model.AreasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AreasMapper {
    int countByExample(AreasExample example);

    int deleteByExample(AreasExample example);

    int deleteByPrimaryKey(Integer areaId);

    int insert(Areas record);

    int insertSelective(Areas record);

    List<Areas> selectByExampleWithRowbounds(AreasExample example, RowBounds rowBounds);

    List<Areas> selectByExample(AreasExample example);

    Areas selectByPrimaryKey(Integer areaId);

    int updateByExampleSelective(@Param("record") Areas record, @Param("example") AreasExample example);

    int updateByExample(@Param("record") Areas record, @Param("example") AreasExample example);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
}