package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/7/29.
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    @Resource
    private ScoreHistoryMapper scoreHistoryMapper;

    @Override
    public List<ScoreHistory> getList(Integer userId, String type){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        if(type.equals(Const.Score.ADD)){
            criteria.andVaildTimeGreaterThan(new Date());
        }
        criteria.andUidEqualTo(userId);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);

        return scoreHistoryMapper.selectByExample(example);

    }

    @Override
    public List<ScoreHistory> getAvailableList(Integer userId){
        List<ScoreHistory> scoreHistories = this.getList(userId, Const.Score.ADD);
        return scoreHistories;
    }


    @Override
    public List<ScoreHistory> getFrozen(Integer userId){
        List<ScoreHistory> scoreHistories = this.getList(userId, Const.Score.FROZEN);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getConsume(Integer userId) {
        List<ScoreHistory> scoreHistories = this.getList(userId, Const.Score.CONSUME);
        return scoreHistories;
    }

    @Override
    public int add(ScoreHistory scoreHistory) {
        return scoreHistoryMapper.insert(scoreHistory);
    }

    @Override
    public int delete(Integer uid, Integer eventId, String fromType) {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andEventIdEqualTo(eventId);
        criteria.andFromTypeEqualTo(fromType);
        return scoreHistoryMapper.deleteByExample(example);
    }


    @Override
    public List<ScoreHistory> calcWillExpired(List<ScoreHistory> scoreHistories){

        List<ScoreHistory> result = new ArrayList<>();
        for(ScoreHistory score : scoreHistories){
            long diff= score.getVaildTime().getTime() - System.currentTimeMillis();
            if(Const.Score.LONGDAY > (diff / (1000 * 60 * 60 * 24))){
                result.add(score);
            }
        }

        return result;
    }

    @Override
    public List<ScoreHistory> getWillExpired(Integer userId) {
        return this.calcWillExpired(this.getAvailableList(userId));

    }

    @Override
    public List<ScoreHistory> getExpiredList(Integer userId){
        List<ScoreHistory> scoreHistories = this.getList(userId, Const.Score.EXPIRE);
        return scoreHistories;
    }

    @Override
    public Integer sumScore(List<ScoreHistory> scoreHistories){
        Integer sum = 0;
        for(ScoreHistory score : scoreHistories){
            sum += score.getScore();
        }
        return sum;
    }

    @Override
    public List<ScoreHistory> getList(Integer userId, String type, Date start, Date end){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(userId);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);
        criteria.andCtimeBetween(start,end);

        return scoreHistoryMapper.selectByExample(example);
    }

    @Override
    public List<ScoreHistory> getAvailableList(Integer userId, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getList(userId, Const.Score.ADD,start,end);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getFrozen(Integer userId, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getList(userId, Const.Score.FROZEN,start,end);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getWillExpired(Integer userId, Date start, Date end){
        return this.calcWillExpired(this.getAvailableList(userId,start,end));
    }

    @Override
    public List<ScoreHistory> getAllList(Integer userId, Date start, Date end){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(userId);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);
        criteria.andCtimeBetween(start,end);

        return scoreHistoryMapper.selectByExample(example);
    }

}
