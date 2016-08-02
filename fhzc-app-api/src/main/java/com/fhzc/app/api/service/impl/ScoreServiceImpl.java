package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
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
    public List<ScoreHistory> getList(Integer uid, String type){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(uid);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(APIConstants.Score.IS_VAILD);
        criteria.andIsApproveEqualTo(APIConstants.Score.IS_APPROVE);

        return scoreHistoryMapper.selectByExample(example);

    }

    @Override
    public List<ScoreHistory> getAvailableList(Integer uid){
        List<ScoreHistory> scoreHistories = this.getList(uid, APIConstants.Score.ADD);
        return scoreHistories;
    }


    @Override
    public List<ScoreHistory> getFrozen(Integer uid){
        List<ScoreHistory> scoreHistories = this.getList(uid, APIConstants.Score.FROZEN);
        return scoreHistories;
    }


    @Override
    public List<ScoreHistory> calcWillExpired(List<ScoreHistory> scoreHistories){

        List<ScoreHistory> result = new ArrayList<>();
        for(ScoreHistory score : scoreHistories){
            long diff=System.currentTimeMillis() - score.getVaildTime().getTime();
            if(APIConstants.Score.LONGDAY < (diff / (1000 * 60 * 60 * 24))){
                result.add(score);
            }
        }

        return result;
    }

    @Override
    public List<ScoreHistory> getWillExpired(Integer uid) {
        return this.calcWillExpired(this.getAvailableList(uid));

    }

    @Override
    public List<ScoreHistory> getExpiredList(Integer uid){
        List<ScoreHistory> scoreHistories = this.getList(uid, APIConstants.Score.EXPIRE);
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
    public List<ScoreHistory> getList(Integer uid, String type, Date start, Date end){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(uid);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(APIConstants.Score.IS_VAILD);
        criteria.andIsApproveEqualTo(APIConstants.Score.IS_APPROVE);
        criteria.andCtimeBetween(start,end);

        return scoreHistoryMapper.selectByExample(example);
    }

    @Override
    public List<ScoreHistory> getAvailableList(Integer uid, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getList(uid, APIConstants.Score.ADD,start,end);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getFrozen(Integer uid, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getList(uid, APIConstants.Score.FROZEN,start,end);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getWillExpired(Integer uid, Date start, Date end){
        return this.calcWillExpired(this.getAvailableList(uid,start,end));
    }

    @Override
    public List<ScoreHistory> getAllList(Integer uid, Date start, Date end){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(uid);
        criteria.andIsVaildEqualTo(APIConstants.Score.IS_VAILD);
        criteria.andIsApproveEqualTo(APIConstants.Score.IS_APPROVE);
        criteria.andVaildTimeBetween(start,end);

        return scoreHistoryMapper.selectByExample(example);
    }

}
