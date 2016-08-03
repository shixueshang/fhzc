package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/7/19 19:50
 */
public interface RightsService {

    /**
     * 获得权益列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Rights> findPageRights(int page, int size);

    /**
     * 添加或这修改权益
     * @param rights
     */
    void addOrUpdateRights(Rights rights);

    /**
     * 获得权益信息
     * @param id
     * @return
     */
    Rights getRights(Integer id);

    /**
     * 获得权益信息
     * @param reservation
     * @return
     */
    int addRightsReservation(RightsReservation reservation);

    /**
     * 获得预约列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<RightsReservation> listRightReservations(int page, int size);

    /**
     * 获得预约
     * @param id
     * @return
     */
    RightsReservation getReservationById(Integer id);

    /**
     * 更新预约
     * @param reservation
     * @return
     */
    int updateReservation(RightsReservation reservation);
}
