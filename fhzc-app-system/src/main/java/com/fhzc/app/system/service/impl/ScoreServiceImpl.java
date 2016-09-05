package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.service.ScoreService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/1 11:53
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreHistoryMapper scoreHistoryMapper;

    @Override
    public Integer getTotalScore(Integer uid){
        List<ScoreHistory> scoreHistories = this.getScoreList(uid, Const.Score.ADD);
        return this.sumScore(scoreHistories);
    }


    @Override
    public Integer getFrozenScore(Integer uid){
        List<ScoreHistory> scoreHistories = this.getScoreList(uid, Const.Score.FROZEN);
        return this.sumScore(scoreHistories);
    }

    @Override
    public Integer getExpiredScore(Integer uid){
        List<ScoreHistory> scoreHistories = this.getScoreList(uid, Const.Score.EXPIRE);
        return this.sumScore(scoreHistories);
    }

    @Override
    public Integer getConsumeScore(Integer uid){
        List<ScoreHistory> scoreHistories = this.getScoreList(uid, Const.Score.CONSUME);
        return this.sumScore(scoreHistories);
    }

    @Override
    public Integer getAvailableScore(Integer uid){
        Integer total = this.getTotalScore(uid);
        Integer consume = this.getConsumeScore(uid);
        Integer expire = this.getExpiredScore(uid);
        Integer frozen = this.getFrozenScore(uid);
        Integer available = total + consume - expire + frozen;
        return available;
    }


    @Override
    public Integer getWillExpiredScore(Integer uid) {
        List<ScoreHistory> scoreHistories = this.getScoreList(uid, Const.Score.ADD);
        List<ScoreHistory> result = new ArrayList<>();
        for(ScoreHistory score : scoreHistories){
            long diff = score.getVaildTime().getTime() - System.currentTimeMillis();
            if(Const.Score.LONGDAY > (diff / (1000 * 60 * 60 * 24))){
                result.add(score);
            }
        }
        return this.sumScore(result);

    }

    private Integer sumScore(List<ScoreHistory> scoreHistories){
        Integer sum = 0;
        for(ScoreHistory score : scoreHistories){
            sum += score.getScore();
        }
        return sum;
    }

    private List<ScoreHistory> getScoreList(Integer uid, String type){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        if(Const.Score.EXPIRE.equals(type)){
            criteria.andVaildTimeLessThan(new Date());
        }
        criteria.andUidEqualTo(uid);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);

        return scoreHistoryMapper.selectByExample(example);

    }

    @Override
    public PageableResult<ScoreHistory> findPageScore(List<Integer> userIds, String name, Integer isApprove, int page, int size) {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        if(userIds.size() > 0){
            criteria.andUidIn(userIds);
        }

        if(org.apache.commons.lang.StringUtils.isNotEmpty(name) && userIds.isEmpty()){
            return new PageableResult<ScoreHistory>(page, size, 0, new ArrayList<ScoreHistory>());
        }
        if(isApprove !=null ){
        	criteria.andIsApproveEqualTo(isApprove);
        }
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<ScoreHistory> list = scoreHistoryMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<ScoreHistory>(page, size, scoreHistoryMapper.countByExample(example), list);
    }
    
    @Override
    public void approve(Integer id) {
        ScoreHistory scoreHistory = scoreHistoryMapper.selectByPrimaryKey(id);
        scoreHistory.setIsApprove(Const.APPROVE_STATUS.APPROVED);
        scoreHistoryMapper.updateByPrimaryKey(scoreHistory);

    }
    
    @Override
    public void approveFailed(Integer id) {
        ScoreHistory scoreHistory = scoreHistoryMapper.selectByPrimaryKey(id);
        scoreHistory.setIsApprove(Const.APPROVE_STATUS.FAILED_APPROVED);
        scoreHistoryMapper.updateByPrimaryKey(scoreHistory);

    }

    @Override
    public void addSCoreRecord(ScoreHistory history) {
        scoreHistoryMapper.insert(history);
    }


}
