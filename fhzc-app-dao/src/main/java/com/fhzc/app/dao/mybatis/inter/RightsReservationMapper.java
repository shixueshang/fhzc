package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.RightsReservation;
import com.fhzc.app.dao.mybatis.model.RightsReservationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RightsReservationMapper {
    int countByExample(RightsReservationExample example);

    int deleteByExample(RightsReservationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RightsReservation record);

    int insertSelective(RightsReservation record);

    List<RightsReservation> selectByExampleWithRowbounds(RightsReservationExample example, RowBounds rowBounds);

    List<RightsReservation> selectByExample(RightsReservationExample example);

    RightsReservation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RightsReservation record, @Param("example") RightsReservationExample example);

    int updateByExample(@Param("record") RightsReservation record, @Param("example") RightsReservationExample example);

    int updateByPrimaryKeySelective(RightsReservation record);

    int updateByPrimaryKey(RightsReservation record);
}