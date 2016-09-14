package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/7/29.
 */
public interface ScoreService {

    /**
     * 客户的总积分, 所有已审批的状态为add的有效积分
     * @param uid
     * @return
     */
    Integer getTotalScore(Integer uid);

    /**
     * 冻结积分, 所有已审批的状态为frozen的有效积分
     * @param uid
     * @return
     */
    Integer getFrozenScore(Integer uid);

    /**
     * 已过期
     * @param uid
     * @return
     */
    Integer getExpiredScore(Integer uid);

    /**
     * 已消费积分
     * @param uid
     * @return
     */
    Integer getConsumeScore(Integer uid);

    /**
     * 当前可用积分, 已审批的 total-consume-expire-forzen
     * @param uid
     * @return
     */
    Integer getAvailableScore(Integer uid);

    /**
     * 即将过期
     * @param uid
     * @return
     */
    Integer getWillExpiredScore(Integer uid);

    List<ScoreHistory> getAvailableScore(Integer userId, Date start, Date end);

    List<ScoreHistory> getFrozenScore(Integer userId, Date start, Date end);

    List<ScoreHistory> getWillExpiredScore(Integer userId, Date start, Date end);

    List<ScoreHistory> getAllScoreList(Integer userId, Date start, Date end);

    void add(ScoreHistory scoreHistory);

    /**
     * 权益取消或者预约失败时把该条冻结的积分设置为无效
     * @param uid
     * @param eventId
     * @param fromType
     * @return
     */
    void delete(Integer uid, Integer eventId, String fromType);
    
    void delete(Integer reservationId);
}
