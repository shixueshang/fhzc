package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthlyExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlannerAchivementsMonthlyMapper {
    int countByExample(PlannerAchivementsMonthlyExample example);

    int deleteByExample(PlannerAchivementsMonthlyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlannerAchivementsMonthly record);

    int insertSelective(PlannerAchivementsMonthly record);

    List<PlannerAchivementsMonthly> selectByExampleWithRowbounds(PlannerAchivementsMonthlyExample example, RowBounds rowBounds);

    List<PlannerAchivementsMonthly> selectByExample(PlannerAchivementsMonthlyExample example);

    PlannerAchivementsMonthly selectByPrimaryKey(Integer id);

    List<PlannerAchivementsMonthly> selectDistinctPlannerUid();

    List<PlannerAchivementsMonthly> selectDistinctPlannerUidByDeptId(Integer departmentId);

    int updateByExampleSelective(@Param("record") PlannerAchivementsMonthly record, @Param("example") PlannerAchivementsMonthlyExample example);

    int updateByExample(@Param("record") PlannerAchivementsMonthly record, @Param("example") PlannerAchivementsMonthlyExample example);

    int updateByPrimaryKeySelective(PlannerAchivementsMonthly record);

    int updateByPrimaryKey(PlannerAchivementsMonthly record);

    List<PlannerAchivementsMonthly> findAchiveMonthlyByArea(Map<String, Object> param);

    List<PlannerAchivementsMonthly> findAchiveMonthlyBySub(Map<String, Object> param);

    List<PlannerAchivementsMonthly> findAchiveMonthlyByTeam(Map<String, Object> param);

    List<PlannerAchivementsMonthly> findAchiveMonthlyByPlanner(Map<String, Object> param);
}