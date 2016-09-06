package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.ScoreService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
            if(diff > 0 && Const.Score.LONGDAY > (diff / (1000 * 60 * 60 * 24))){
                result.add(score);
            }
        }

        Integer willExpire = this.sumScore(result);
        //获得累计消费的积分
        Integer consume = this.getConsumeScore(uid);
        if(Math.abs(consume) > willExpire){ //如果累计消费积分大于即将过期的，根据优先消费原则没有即将过期积分
            return 0;
        }else{
            //按有效日期升序
            /*Collections.sort(result, new Comparator<ScoreHistory>() {
                public int compare(ScoreHistory arg0, ScoreHistory arg1) {
                    return arg0.getVaildTime().compareTo(arg1.getVaildTime());
                }
            });*/

            Integer scoreCount = 0;
           for(ScoreHistory sh : result){
               scoreCount += sh.getScore();
           }

            willExpire = scoreCount - Math.abs(consume);
        }

        return willExpire;

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
        criteria.andIsApproveEqualTo(isApprove);
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

    @Override
    public List<ScoreHistory> getExpiredScore() {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andVaildTimeBetween(DateUtil.getStartTimeOfDate(new Date()), DateUtil.getEndTimeOfDate(new Date()));
        criteria.andIsVaildEqualTo(Const.SCORE_VAILD.IS_VAILD);
        criteria.andIsApproveEqualTo(Const.APPROVE_STATUS.APPROVED);
        return scoreHistoryMapper.selectByExample(example);
    }

    @Override
    public void update(ScoreHistory history) {
        scoreHistoryMapper.updateByPrimaryKey(history);
    }


}
