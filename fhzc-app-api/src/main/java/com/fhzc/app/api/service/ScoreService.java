package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/7/29.
 */
public interface ScoreService {

    List<ScoreHistory> getList(Integer customerId, String type);
    List<ScoreHistory> getAvailableList(Integer customerId);
    List<ScoreHistory> getFrozen(Integer customerId);
    List<ScoreHistory> getWillExpired(Integer customerId);
    List<ScoreHistory> getExpiredList(Integer customerId);
    List<ScoreHistory> calcWillExpired(List<ScoreHistory> scoreHistories);
    Integer sumScore(List<ScoreHistory> scoreHistories);

    List<ScoreHistory> getList(Integer customerId, String type, Date start, Date end);
    List<ScoreHistory> getAvailableList(Integer customerId, Date start, Date end);
    List<ScoreHistory> getFrozen(Integer customerId, Date start, Date end);
    List<ScoreHistory> getWillExpired(Integer customerId, Date start, Date end);
    List<ScoreHistory> getAllList(Integer customerId, Date start, Date end);

    List<ScoreHistory> getConsume(Integer cutomerId);

    int add(ScoreHistory scoreHistory);

    int delete(Integer uid, Integer eventId, String fromType);
}
