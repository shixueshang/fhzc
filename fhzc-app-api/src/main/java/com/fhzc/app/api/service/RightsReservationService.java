package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.RightsReservation;

/**
 * Created by Administrator on 2016/7/22.
 */
public interface RightsReservationService {
    public void addOrUpdateProductReservation(RightsReservation rightsReservation);
    public RightsReservation getRightsReservation(Integer id);
}
