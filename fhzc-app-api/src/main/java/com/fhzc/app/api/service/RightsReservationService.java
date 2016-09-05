package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.RightsReservation;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public interface RightsReservationService {
    void addOrUpdateRightsReservation(RightsReservation rightsReservation);

    RightsReservation getRightsReservation(Integer id);

    List<RightsReservation> getUserRightsList(Integer customer_id);

    RightsReservation getUserRightsReservation(Integer uid, Integer rightsId);
}
