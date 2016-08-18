package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.RankMonth;

import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/8/18.
 */
public interface RankMonthService {
    int addOrUpdate(RankMonth rankMonth);
    List<Date> getExistYearMonth();
    List<RankMonth> getYearMonthRankList(Date yearMonth);
}
