package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.bo.ProductReservationBo;
import com.fhzc.app.dao.mybatis.model.ProductReserQuery;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.model.ProductReservationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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

    List<ProductReservationBo> selectReservations(ProductReserQuery query, RowBounds rowBounds);

    int countReservations(ProductReserQuery query);
}
