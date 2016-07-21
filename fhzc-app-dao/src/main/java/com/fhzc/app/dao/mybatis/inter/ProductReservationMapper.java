package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.model.ProductReservationExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ProductReservationMapper {
    int countByExample(ProductReservationExample example);

    int deleteByExample(ProductReservationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductReservation record);

    int insertSelective(ProductReservation record);

    List<ProductReservation> selectByExampleWithRowbounds(ProductReservationExample example, RowBounds rowBounds);

    List<ProductReservation> selectByExample(ProductReservationExample example);

    ProductReservation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductReservation record, @Param("example") ProductReservationExample example);

    int updateByExample(@Param("record") ProductReservation record, @Param("example") ProductReservationExample example);

    int updateByPrimaryKeySelective(ProductReservation record);

    int updateByPrimaryKey(ProductReservation record);
}