package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;

/**
 * Created by lihongde on 2016/8/01 11:53
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

    /**
     * 查询积分列表
     * @param userIds
     * @param name  姓名
     * @param isApprove 审批状态
     * @param page
     * @param size
     * @return
     */
    PageableResult<ScoreHistory> findPageScore(List<Integer> userIds, String name, Integer fromType, Integer isApprove, int page, int size);

    /**
     * 审批积分通过
     * @param id
     */
    void approve(Integer id);
    
    /**
     * 审批积分失败
     * @param id
     */
    void approveFailed(Integer id);

    /**
     * @param history
     */
    void addSCoreRecord(ScoreHistory history);

    /**
     * 查询当天过期积分列表
     * @return
     */
    List<ScoreHistory> getExpiredScore();


    /**
     * 权益取消或者预约失败时把该条冻结的积分设置为无效
     * @param uid
     * @param eventId
     * @param fromType
     * @return
     */
    void delete(Integer uid, Integer eventId, String fromType);
    
    /**
     * 权益取消或者预约失败时把该条冻结的积分设置为无效
     * @param uid
     * @param eventId
     * @param fromType
     * @return
     */
    void delete(Integer reservationId);

}
