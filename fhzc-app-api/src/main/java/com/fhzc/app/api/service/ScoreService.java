package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/7/29.
 */
public interface ScoreService {

    List<ScoreHistory> getList(Integer userId, String type);

    List<ScoreHistory> getAvailableList(Integer userId);

    List<ScoreHistory> getFrozen(Integer userId);

    List<ScoreHistory> getWillExpired(Integer userId);

    List<ScoreHistory> getExpiredList(Integer userId);

    List<ScoreHistory> calcWillExpired(List<ScoreHistory> scoreHistories);

    Integer sumScore(List<ScoreHistory> scoreHistories);

    List<ScoreHistory> getList(Integer userId, String type, Date start, Date end);

    List<ScoreHistory> getAvailableList(Integer userId, Date start, Date end);

    List<ScoreHistory> getFrozen(Integer userId, Date start, Date end);

    List<ScoreHistory> getWillExpired(Integer userId, Date start, Date end);

    List<ScoreHistory> getAllList(Integer userId, Date start, Date end);

    List<ScoreHistory> getConsume(Integer userId);

    void add(ScoreHistory scoreHistory);

    /**
     * 权益取消或者预约失败时把该条冻结的积分设置为无效
     * @param uid
     * @param eventId
     * @param fromType
     * @return
     */
    void delete(Integer uid, Integer eventId, String fromType);
}
