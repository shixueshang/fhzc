package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.ActivityApply;
import com.fhzc.app.dao.mybatis.model.ActivityApplyExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ActivityApplyMapper {
    int countByExample(ActivityApplyExample example);

    int deleteByExample(ActivityApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityApply record);

    int insertSelective(ActivityApply record);

    List<ActivityApply> selectByExampleWithRowbounds(ActivityApplyExample example, RowBounds rowBounds);

    List<ActivityApply> selectByExample(ActivityApplyExample example);

    ActivityApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityApply record, @Param("example") ActivityApplyExample example);

    int updateByExample(@Param("record") ActivityApply record, @Param("example") ActivityApplyExample example);

    int updateByPrimaryKeySelective(ActivityApply record);

    int updateByPrimaryKey(ActivityApply record);

}