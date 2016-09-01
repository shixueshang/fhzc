package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.RankMonthService;
import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsMonthlyMapper;
import com.fhzc.app.dao.mybatis.inter.RankMonthMapper;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.model.RankMonth;
import com.fhzc.app.dao.mybatis.model.RankMonthExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/8/18.
 */
@Service
public class RankMonthServiceImpl implements RankMonthService {
    @Resource
    RankMonthMapper rankMonthMapper;

    @Resource
    PlannerAchivementsMonthlyMapper plannerAchivementsMonthlyMapper;

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

    @Override
    public RankMonth getByPlannerIdYearMonth(Integer plannerId, Date yearMonth){
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(plannerId);
        criteria.andYearMonthEqualTo(yearMonth);
        if (rankMonthMapper.countByExample(example) > 0) {
            return rankMonthMapper.selectByExample(example).get(0);
        }else{
            return null;
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
    public List<Integer> getExistDepartment(){
        List<RankMonth> rankList = rankMonthMapper.selectDistinctDepartmentId();
        List<Integer> result = new ArrayList<>();
        for (RankMonth rank : rankList) {
            result.add(rank.getDepartmentId());
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

    @Override
    public List<RankMonth> getYearMonthRankList(Date yearMonth,Integer departmentId){
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("annualised desc");
        criteria.andYearMonthEqualTo(yearMonth);
        criteria.andDepartmentIdEqualTo(departmentId);
        return rankMonthMapper.selectByExample(example);
    }

    @Override
    public List<RankMonth> getPlannerRankList(Integer plannerId) {
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(plannerId);
        example.setOrderByClause("`year_month` desc");
        if (rankMonthMapper.countByExample(example) > 0) {
            return rankMonthMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    @Override
    public List<RankMonth> getPlannerRankList(Integer plannerId, Integer departmentId) {
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(plannerId);
        criteria.andPlannerIdEqualTo(departmentId);
        example.setOrderByClause("`year_month` desc");
        if (rankMonthMapper.countByExample(example) > 0) {
            return rankMonthMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    @Override
    public List<RankMonth> getPlannerRankListByYear(Integer plannerId, Date start, Date end) {
        RankMonthExample example = new RankMonthExample();
        RankMonthExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("`year_month` desc");
        criteria.andPlannerIdEqualTo(plannerId);
        criteria.andYearMonthBetween(start, end);
        if (rankMonthMapper.countByExample(example) > 0) {
            return rankMonthMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    @Override
    public List<PlannerAchivementsMonthly> getSortByDate(Date start, Date end){
        return plannerAchivementsMonthlyMapper.selectAnnualisedOrder(start, end);
    }
}
