package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;

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
     * 添加或修改权益
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
     * 权益预约
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

    /**
     * 获得所有权益
     * @return
     */
    List<Rights> getAllRights();
    
    /**
     * 根据权益id获得预约成功的权益
     * @param id
     * @param status
     * @return
     */
    List<RightsReservation> findSuccessOrdersById(Integer id, Integer status);
    
    /**
     * 根据权益类型获得权益
     * @param typeId
     * @return
     */
    List<Rights> getRightsByType(String typeId);
}
