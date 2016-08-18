package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.RankMonthService;
import com.fhzc.app.dao.mybatis.inter.RankMonthMapper;
import com.fhzc.app.dao.mybatis.model.RankMonth;
import com.fhzc.app.dao.mybatis.model.RankMonthExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/8/18.
 */
@Service
public class RankMonthServiceImpl implements RankMonthService {
    @Resource
    RankMonthMapper rankMonthMapper;


    @Override
    public int addOrUpdate(RankMonth rankMonth) {
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(rankMonth.getPlannerId());
        criteria.andYearMonthEqualTo(rankMonth.getYearMonth());

        if (rankMonthMapper.countByExample(example) > 0) {
            RankMonth rank1= rankMonthMapper.selectByExample(example).get(0);
            rankMonth.setId(rank1.getId());
            return rankMonthMapper.updateByPrimaryKey(rankMonth);
        }else{
            return rankMonthMapper.insert(rankMonth);
        }
    }

    @Deprecated
    private Date getMaxDate(){
        RankMonthExample example = new RankMonthExample();
        example.setOrderByClause("year_month desc");
        if (rankMonthMapper.countByExample(example) > 0) {
            RankMonth rank = rankMonthMapper.selectByExample(example).get(0);
            return rank.getYearMonth();
        }else{
            return null;
        }
    }

    @Deprecated
    private Date getMinDate(){
        RankMonthExample example = new RankMonthExample();
        example.setOrderByClause("year_month asc");
        if (rankMonthMapper.countByExample(example) > 0) {
            RankMonth rank = rankMonthMapper.selectByExample(example).get(0);
            return rank.getYearMonth();
        }else{
            return null;
        }
    }

    @Override
    public List<Date> getExistYearMonth(){
        List<RankMonth> rankList = rankMonthMapper.selectDistinctYearMonth();
        List<Date> result = new ArrayList<>();
        for (RankMonth rank : rankList) {
            result.add(rank.getYearMonth());
        }
        return result;
    }

    @Override
    public List<RankMonth> getYearMonthRankList(Date yearMonth){
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("annualised desc");
        criteria.andYearMonthEqualTo(yearMonth);
        return rankMonthMapper.selectByExample(example);
    }
}
