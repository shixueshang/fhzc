package com.fhzc.app.api.service;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import java.util.List;

/**
 * Created by freeman on 16/7/29.
 */
public interface ScoreService {

    public List<ScoreHistory> getList(Integer uid, String type);

    public List<ScoreHistory> getAvailableList(Integer uid);

    public List<ScoreHistory> getFrozen(Integer uid);

    public List<ScoreHistory> getWillExpired(Integer uid);

    public List<ScoreHistory> getExpiredList(Integer uid);

    public Integer sumScore(List<ScoreHistory> scoreHistories);

}
