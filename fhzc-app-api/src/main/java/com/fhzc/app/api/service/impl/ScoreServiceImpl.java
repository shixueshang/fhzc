package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.api.tools.DateUtil;
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
            long diff = score.getVaildTime().getTime() - (System.currentTimeMillis()+ Const.Score.LONGDAY * (1000 * 60 * 60 * 24));
            if(diff <= 0 ){
                result.add(score);
            }
        }

        Integer willExpire = this.sumScore(result);
        //获得累计消费的积分
        Integer consume = this.getConsumeScore(uid);
        Integer frozen = this.getFrozenScore(uid);
        Integer expire = this.getExpiredScore(uid);
        if((Math.abs(consume)+Math.abs(frozen)+Math.abs(expire)) > willExpire){ //如果累计消费积分大于即将过期的，根据优先消费原则没有即将过期积分
            return 0;
        }else{
            willExpire = willExpire - Math.abs(consume)- Math.abs(frozen) - Math.abs(expire);
        }
        return willExpire;
    }

    @Override
    public List<ScoreHistory> getAvailableScore(Integer userId, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getScoreList(userId, Const.Score.ADD, start, end);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getFrozenScore(Integer userId, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getScoreList(userId, Const.Score.FROZEN, start, end);
        return scoreHistories;
    }

    @Override
    public List<ScoreHistory> getWillExpiredScore(Integer userId, Date start, Date end){
        List<ScoreHistory> scoreHistories = this.getScoreList(userId, Const.Score.ADD, start, end);
        List<ScoreHistory> result = new ArrayList<>();
        for(ScoreHistory score : scoreHistories){
            long diff = score.getVaildTime().getTime() - System.currentTimeMillis();
            if(diff > 0 && Const.Score.LONGDAY > (diff / (1000 * 60 * 60 * 24))){
                result.add(score);
            }
        }
        return result;
    }

    @Override
    public void add(ScoreHistory scoreHistory) {
        scoreHistoryMapper.insert(scoreHistory);
    }

    @Override
    public void delete(Integer uid, Integer eventId, String fromType) {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andEventIdEqualTo(eventId);
        criteria.andFromTypeEqualTo(fromType);
        ScoreHistory history = scoreHistoryMapper.selectByExample(example).get(0);
        history.setIsVaild(Const.SCORE_VAILD.NOT_VAILD);
        scoreHistoryMapper.updateByPrimaryKey(history);
    }

    
    @Override
    public void delete(Integer reservationId) {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andReservationIdEqualTo(reservationId);
        ScoreHistory history = scoreHistoryMapper.selectByExample(example).get(0);
        history.setIsVaild(Const.SCORE_VAILD.NOT_VAILD);
        scoreHistoryMapper.updateByPrimaryKey(history);
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

        criteria.andUidEqualTo(uid);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);

        return scoreHistoryMapper.selectByExample(example);

    }

    private List<ScoreHistory> getScoreList(Integer uid, String type, Date start, Date end){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        if(start != null && end != null){
            criteria.andCtimeBetween(DateUtil.getStartTimeOfDate(start), DateUtil.getEndTimeOfDate(end));
        }

        criteria.andUidEqualTo(uid);
        criteria.andStatusEqualTo(type);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);

        return scoreHistoryMapper.selectByExample(example);

    }


    @Override
    public List<ScoreHistory> getAllScoreList(Integer userId, Date start, Date end){
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andUidEqualTo(userId);
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.Score.IS_APPROVE);
        criteria.andCtimeBetween(start,end);

        return scoreHistoryMapper.selectByExample(example);
    }

}
