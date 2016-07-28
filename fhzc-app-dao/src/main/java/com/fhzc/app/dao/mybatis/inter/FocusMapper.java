package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.FocusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FocusMapper {
    int countByExample(FocusExample example);

    int deleteByExample(FocusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Focus record);

    int insertSelective(Focus record);

    List<Focus> selectByExampleWithRowbounds(FocusExample example, RowBounds rowBounds);

    List<Focus> selectByExample(FocusExample example);

    Focus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Focus record, @Param("example") FocusExample example);

    int updateByExample(@Param("record") Focus record, @Param("example") FocusExample example);

    int updateByPrimaryKeySelective(Focus record);

    int updateByPrimaryKey(Focus record);
}