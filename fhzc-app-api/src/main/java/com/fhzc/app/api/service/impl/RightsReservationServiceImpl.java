package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.RightsReservationService;
import com.fhzc.app.dao.mybatis.inter.RightsReservationMapper;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/7/22.
 */
@Service
public class RightsReservationServiceImpl implements RightsReservationService{
    @Resource
    private RightsReservationMapper rightsReservationMapper;

    @Override
    public void addOrUpdateProductReservation(RightsReservation rightsReservation) {
        Integer id= rightsReservation.getId();
        if(id == null){
            rightsReservationMapper.insert(rightsReservation);
        }else{
            rightsReservationMapper.updateByPrimaryKey(rightsReservation);
        }
    }
    @Override
    public RightsReservation getRightsReservation(Integer id) {
        return rightsReservationMapper.selectByPrimaryKey(id);
    }

}
