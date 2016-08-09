package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDailyExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlannerAchivementsDailyMapper {
    int countByExample(PlannerAchivementsDailyExample example);

    int deleteByExample(PlannerAchivementsDailyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlannerAchivementsDaily record);

    int insertSelective(PlannerAchivementsDaily record);

    List<PlannerAchivementsDaily> selectByExampleWithRowbounds(PlannerAchivementsDailyExample example, RowBounds rowBounds);

    List<PlannerAchivementsDaily> selectByExample(PlannerAchivementsDailyExample example);

    PlannerAchivementsDaily selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlannerAchivementsDaily record, @Param("example") PlannerAchivementsDailyExample example);

    int updateByExample(@Param("record") PlannerAchivementsDaily record, @Param("example") PlannerAchivementsDailyExample example);

    int updateByPrimaryKeySelective(PlannerAchivementsDaily record);

    int updateByPrimaryKey(PlannerAchivementsDaily record);

    List<PlannerAchivementsDaily> getAchivementsData(Map<String, Object> param);
}