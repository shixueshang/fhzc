package com.fhzc.app.api.service;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/7/29.
 */
public interface ScoreService {

    List<ScoreHistory> getList(Integer uid, String type);
    List<ScoreHistory> getAvailableList(Integer uid);
    List<ScoreHistory> getFrozen(Integer uid);
    List<ScoreHistory> getWillExpired(Integer uid);
    List<ScoreHistory> getExpiredList(Integer uid);
    List<ScoreHistory> calcWillExpired(List<ScoreHistory> scoreHistories);
    Integer sumScore(List<ScoreHistory> scoreHistories);

    List<ScoreHistory> getList(Integer uid, String type, Date start, Date end);
    List<ScoreHistory> getAvailableList(Integer uid, Date start, Date end);
    List<ScoreHistory> getFrozen(Integer uid, Date start, Date end);
    List<ScoreHistory> getWillExpired(Integer uid, Date start, Date end);
    List<ScoreHistory> getAllList(Integer uid, Date start, Date end);

}
