package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReserQuery;
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
    
    /**
     * 检查权益编号是否存在
     * @param rightsNum
     * @return
     */
    boolean isNumExists(String rightsNum);
    
    /**
     * 根据名字获取权益
     * @param rightsName
     * @return
     */
    Rights getRightsByName(String rightsName);
    
    /**
     * 获得权益预约列表
     * @param query 过滤条件
     * @param page
     * @param size
     * @return
     */
    PageableResult<RightsReservation> findPageRightsReservations(RightsReserQuery query, int page, int size);
    
    /**
     * 获取权益预约
     * @param customerId
     * @param rightsId
     * @param status
     * @return
     */
    List<RightsReservation> getRightsReservations(Integer customerId, Integer rightsId, List<Integer> status);
}
