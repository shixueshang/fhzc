package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.RankYearService;
import com.fhzc.app.dao.mybatis.inter.RankYearMapper;
import com.fhzc.app.dao.mybatis.model.RankYear;
import com.fhzc.app.dao.mybatis.model.RankYearExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by freeman on 16/8/18.
 */
@Service
public class RankYearServiceImpl implements RankYearService{

    @Resource
    RankYearMapper rankYearMapper;


    @Override
    public int addOrUpdate(RankYear rankYear) {
        RankYearExample example = new RankYearExample();
        RankYearExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(rankYear.getPlannerId());
        criteria.andYearEqualTo(rankYear.getYear());

        if (rankYearMapper.countByExample(example) > 0) {
            RankYear rank1= rankYearMapper.selectByExample(example).get(0);
            rankYear.setId(rank1.getId());
            return rankYearMapper.updateByPrimaryKey(rankYear);
        }else{
            return rankYearMapper.insert(rankYear);
        }
    }

    @Override
    public RankYear getByPlannerIdYear(Integer plannerId, Integer year){
        RankYearExample example = new RankYearExample();
        RankYearExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(plannerId);
        criteria.andYearEqualTo(year);
        if (rankYearMapper.countByExample(example) > 0) {
            return rankYearMapper.selectByExample(example).get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Integer> getExistYear(){
        List<RankYear> rankList = rankYearMapper.selectDistinctYear();
        List<Integer> result = new ArrayList<>();
        for (RankYear rank : rankList) {
            result.add(rank.getYear());
        }
        return result;
    }

    @Override
    public List<Integer> getExistDepartment(){
        List<RankYear> rankList = rankYearMapper.selectDistinctDepartmentId();
        List<Integer> result = new ArrayList<>();
        for (RankYear rank : rankList) {
            result.add(rank.getDepartmentId());
        }
        return result;
    }

    @Override
    public List<RankYear> getYearRankList(Integer year){
        RankYearExample example = new RankYearExample();
        RankYearExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("annualised desc");
        criteria.andYearEqualTo(year);
        return rankYearMapper.selectByExample(example);
    }

    @Override
    public List<RankYear> getYearRankList(Integer year , Integer departmentId){
        RankYearExample example = new RankYearExample();
        RankYearExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("annualised desc");
        criteria.andYearEqualTo(year);
        criteria.andDepartmentIdEqualTo(departmentId);
        return rankYearMapper.selectByExample(example);
    }

    @Override
    public List<RankYear> getPlannerRankList(Integer plannerId) {
        RankYearExample example = new RankYearExample();
        RankYearExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(plannerId);
        example.setOrderByClause("year desc");
        if (rankYearMapper.countByExample(example) > 0) {
            return rankYearMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    @Override
    public List<RankYear> getPlannerRankList(Integer plannerId, Integer departmentId) {
        RankYearExample example = new RankYearExample();
        RankYearExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerIdEqualTo(plannerId);
        criteria.andDepartmentIdEqualTo(departmentId);
        example.setOrderByClause("year_month desc");
        if (rankYearMapper.countByExample(example) > 0) {
            return rankYearMapper.selectByExample(example);
        }else{
            return null;
        }
    }
}
