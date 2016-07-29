package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ProductReservation;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface ProductReservationService {

    public void addOrUpdateProductReservation(ProductReservation productReservation);

    public ProductReservation getProductReservation(Integer id) ;

    public List<ProductReservation> getUserProductList(Integer customer_id);

    public ProductReservation getUserProductReservation(Integer uid, Integer productId);
}
