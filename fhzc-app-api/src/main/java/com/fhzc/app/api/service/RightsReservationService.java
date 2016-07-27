package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.RightsReservation;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public interface RightsReservationService {
    public void addOrUpdateRightsReservation(RightsReservation rightsReservation);
    public RightsReservation getRightsReservation(Integer id);
    public List<RightsReservation> getUserRightsList(Integer customer_id);
}
