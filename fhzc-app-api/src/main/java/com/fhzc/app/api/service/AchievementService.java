package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;

import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/8/14.
 */
public interface AchievementService {
    Integer getMonthSale(Integer plannerId, Integer year,Integer month);
    Integer getYearSale(Integer plannerId, Integer year);

    Map<String,String> getMonthRankList(Integer year, Integer month);
    Map<String,String>  getYearRankList(Integer year);

    Map<String,String> getMonthRankList(Integer year, Integer month, Integer department_id);
    Map<String,String>  getYearRankList(Integer year, Integer department_id);

    Integer userRankInList(Map<String, String> map, Integer plannerId);

    List<PlannerAchivementsMonthly> getPlanners();
}
