package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import java.util.List;

/**
 * Created by lihongde on 2016/8/01 11:53
 */
public interface ScoreService {

    /**
     * 根据积分类型获取用户积分列表
     * @param uid
     * @param type  积分类型
     * @return
     */
    List<ScoreHistory> getList(Integer uid, String type);

    /**
     * 当前可用积分
     * @param uid
     * @return
     */
    List<ScoreHistory> getAvailableList(Integer uid);

    /**
     * 冻结积分
     * @param uid
     * @return
     */
    List<ScoreHistory> getFrozen(Integer uid);

    /**
     * 即将过期
     * @param uid
     * @return
     */
    List<ScoreHistory> getWillExpired(Integer uid);

    /**
     * 已过期
     * @param uid
     * @return
     */
    List<ScoreHistory> getExpiredList(Integer uid);


    /**
     * 统计
     * @param scoreHistories
     * @return
     */
    Integer sumScore(List<ScoreHistory> scoreHistories);

}
