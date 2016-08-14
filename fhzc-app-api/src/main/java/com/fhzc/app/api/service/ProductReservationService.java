package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.ProductReservation;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface ProductReservationService {

    void addOrUpdateProductReservation(ProductReservation productReservation);

    ProductReservation getProductReservation(Integer id) ;

    List<ProductReservation> getUserProductList(Integer customer_id);

    ProductReservation getUserProductReservation(Integer customerId, Integer productId);
}
