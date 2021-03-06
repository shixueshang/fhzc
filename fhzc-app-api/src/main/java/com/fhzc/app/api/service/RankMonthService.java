package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.model.RankMonth;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/8/18.
 */
public interface RankMonthService {
    int addOrUpdate(RankMonth rankMonth);
    RankMonth getByPlannerIdYearMonth(Integer plannerId, Date yearMonth);
    List<Integer> getExistDepartment();
    List<Date> getExistYearMonth();
    List<RankMonth> getYearMonthRankList(Date yearMonth);
    List<RankMonth> getYearMonthRankList(Date yearMonth, Integer departmentId);
    List<RankMonth> getPlannerRankList(Integer plannerId);
    List<RankMonth> getPlannerRankList(Integer plannerId,Integer departmentId);
    List<RankMonth> getPlannerRankListByYear(Integer plannerId, Date start, Date end);
    List<PlannerAchivementsMonthly> getSortByDate(Date start, Date end);
}
