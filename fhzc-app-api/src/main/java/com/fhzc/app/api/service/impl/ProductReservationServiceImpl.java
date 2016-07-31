package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ProductReservationService;
import com.fhzc.app.dao.mybatis.inter.ProductReservationMapper;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.model.ProductReservationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public ProductReservation getProductReservation(Integer id) {
        return productReservationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductReservation> getUserProductList(Integer customer_id) {
        ProductReservationExample example = new ProductReservationExample();
        ProductReservationExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customer_id);
        return productReservationMapper.selectByExample(example);
    }

    @Override
    public ProductReservation getUserProductReservation(Integer uid, Integer productId){
        ProductReservationExample example = new ProductReservationExample();
        ProductReservationExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(uid);
        criteria.andProductIdEqualTo(productId);
        if(productReservationMapper.countByExample(example) > 0) {
            return productReservationMapper.selectByExample(example).get(0);
        }else{
            return null;
        }
    }
}
