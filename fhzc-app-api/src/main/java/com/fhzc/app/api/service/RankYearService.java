package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.RankYear;

import java.util.List;

/**
 * Created by freeman on 16/8/18.
 */
public interface RankYearService {
    int addOrUpdate(RankYear rankYear);
    List<Integer> getExistYear();
    List<RankYear> getYearRankList(Integer year);
}

