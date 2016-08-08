package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlannerMapper {
    int countByExample(PlannerExample example);

    int deleteByExample(PlannerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Planner record);

    int insertSelective(Planner record);

    List<Planner> selectByExampleWithRowbounds(PlannerExample example, RowBounds rowBounds);

    List<Planner> selectByExample(PlannerExample example);

    Planner selectByPrimaryKey(Integer id);

    Planner selectByWorkNum(String workNum);

    int updateByExampleSelective(@Param("record") Planner record, @Param("example") PlannerExample example);

    int updateByExample(@Param("record") Planner record, @Param("example") PlannerExample example);

    int updateByPrimaryKeySelective(Planner record);

    int updateByPrimaryKey(Planner record);
}
