package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.RankYear;

import java.util.List;

/**
 * Created by freeman on 16/8/18.
 */
public interface RankYearService {
    int addOrUpdate(RankYear rankYear);
    RankYear getByPlannerIdYear(Integer plannerId, Integer year);
    List<Integer> getExistYear();
    List<Integer> getExistDepartment();
    List<RankYear> getYearRankList(Integer year);
    List<RankYear> getYearRankList(Integer year,Integer departmentId);
    List<RankYear> getPlannerRankList(Integer plannerId);
    List<RankYear> getPlannerRankList(Integer plannerId,Integer departmentId);
    List<RankYear> getFirstTenByYear(Integer year);
}

