package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ActivityMapper {
    int countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExampleWithBLOBsWithRowbounds(ActivityExample example, RowBounds rowBounds);

    List<Activity> selectByExampleWithBLOBs(ActivityExample example);

    List<Activity> selectByExampleWithRowbounds(ActivityExample example, RowBounds rowBounds);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExampleWithBLOBs(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);
}