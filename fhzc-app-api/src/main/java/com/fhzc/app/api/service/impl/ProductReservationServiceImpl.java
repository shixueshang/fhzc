package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.dao.mybatis.inter.ProductReservationMapper;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jiajitao on 2016/7/20.
 */
@Service
public class ProductReservationServiceImpl implements ProductReservationService {
    @Resource
    private ProductReservationMapper productReservationMapper;

    @Override
    public void addOrUpdateProductReservation(ProductReservation productReservation) {
        Integer id= productReservation.getId();
        if(id == null){
            productReservationMapper.insert(productReservation);
        }else{
            productReservationMapper.updateByPrimaryKey(productReservation);
        }
    }
}
