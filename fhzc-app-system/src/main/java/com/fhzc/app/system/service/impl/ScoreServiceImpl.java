package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.service.ScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongde on 2016/8/1 11:53
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreHistoryMapper scoreHistoryMapper;

    @Override
    public List<ScoreHistory> getList(Integer uid, String type){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(uid);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(Const.Score.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);

        return scoreHistoryMapper.selectByExample(example);

    }

    @Override
    public List<ScoreHistory> getAvailableList(Integer uid){
        List<ScoreHistory> scoreHistories = this.getList(uid, Const.Score.ADD);
        return scoreHistories;
    }


    @Override
    public List<ScoreHistory> getFrozen(Integer uid){
        List<ScoreHistory> scoreHistories = this.getList(uid, Const.Score.FROZEN);
        return scoreHistories;
    }


    private List<ScoreHistory> calcWillExpired(List<ScoreHistory> scoreHistories){

        List<ScoreHistory> result = new ArrayList<>();
        for(ScoreHistory score : scoreHistories){
            long diff=System.currentTimeMillis() - score.getVaildTime().getTime();
            if(Const.Score.LONGDAY < (diff / (1000 * 60 * 60 * 24))){
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
        List<ScoreHistory> scoreHistories = this.getList(uid, Const.Score.EXPIRE);
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




}