package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.ScoreHistoryExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.ScoreService;

import org.apache.commons.lang3.StringUtils;
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
            //按有效日期升序
            /*Collections.sort(result, new Comparator<ScoreHistory>() {
                public int compare(ScoreHistory arg0, ScoreHistory arg1) {
                    return arg0.getVaildTime().compareTo(arg1.getVaildTime());
                }
            });*/

            willExpire = willExpire - Math.abs(consume)- Math.abs(frozen) - Math.abs(expire);
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
    public PageableResult<ScoreHistory> findPageScore(List<Integer> userIds, String name, Integer fromType, Integer isApprove, int page, int size) {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        if(userIds.size() > 0){
            criteria.andUidIn(userIds);
        }

        if(org.apache.commons.lang.StringUtils.isNotEmpty(name) && userIds.isEmpty()){
            return new PageableResult<ScoreHistory>(page, size, 0, new ArrayList<ScoreHistory>());
        }
        if(fromType != null){
        	List<String> scoreType = new ArrayList<String>();
            switch(fromType){
        	case 0: scoreType.add("product");
        			scoreType.add("activity");
        			scoreType.add("rights");
        			scoreType.add("other");
        			break;
        	case 1:	scoreType.add("product");break;
        	case 2:	scoreType.add("activity");break;
        	case 3:	scoreType.add("rights");break;
        	case 4:	scoreType.add("other");break;
        }
            criteria.andFromTypeIn(scoreType);
        }
        if(isApprove != null){
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
    public void delete(Integer reservationId, String status) {
        ScoreHistoryExample example = new ScoreHistoryExample();
        ScoreHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andReservationIdEqualTo(reservationId);
       
        ScoreHistory history = scoreHistoryMapper.selectByExample(example).get(0);
        if(StringUtils.isNotBlank(status) && Const.Score.CONSUME.equals(status)){
       	 	history.setStatus(Const.Score.CONSUME);
        }else{
        	history.setIsVaild(Const.SCORE_VAILD.NOT_VAILD);
        }
        scoreHistoryMapper.updateByPrimaryKey(history);
    }

}
