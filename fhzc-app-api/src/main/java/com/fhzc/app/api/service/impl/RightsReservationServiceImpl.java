package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.RightsReservationService;
import com.fhzc.app.dao.mybatis.inter.RightsReservationMapper;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import com.fhzc.app.dao.mybatis.model.RightsReservationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
@Service
public class RightsReservationServiceImpl implements RightsReservationService{
    @Resource
    private RightsReservationMapper rightsReservationMapper;

    @Override
    public void addOrUpdateRightsReservation(RightsReservation rightsReservation) {
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

    @Override
    public List<RightsReservation> getUserRightsList(Integer customer_id) {
        RightsReservationExample example = new  RightsReservationExample();
        RightsReservationExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customer_id);
        return rightsReservationMapper.selectByExample(example);
    }

    @Override
    public RightsReservation getUserRightsReservation(Integer uid, Integer rightsId){
        RightsReservationExample example = new RightsReservationExample();
        RightsReservationExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        criteria.andCustomerIdEqualTo(uid);
        criteria.andRightsIdEqualTo(rightsId);
        if(rightsReservationMapper.countByExample(example) > 0) {
            return rightsReservationMapper.selectByExample(example).get(0);
        }else{
            return null;
        }
    }
}
