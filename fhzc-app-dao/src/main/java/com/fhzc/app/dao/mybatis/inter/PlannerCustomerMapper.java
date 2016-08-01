package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.PlannerCustomer;
import com.fhzc.app.dao.mybatis.model.PlannerCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlannerCustomerMapper {
    int countByExample(PlannerCustomerExample example);

    int deleteByExample(PlannerCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlannerCustomer record);

    int insertSelective(PlannerCustomer record);

    List<PlannerCustomer> selectByExampleWithRowbounds(PlannerCustomerExample example, RowBounds rowBounds);

    List<PlannerCustomer> selectByExample(PlannerCustomerExample example);

    PlannerCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlannerCustomer record, @Param("example") PlannerCustomerExample example);

    int updateByExample(@Param("record") PlannerCustomer record, @Param("example") PlannerCustomerExample example);

    int updateByPrimaryKeySelective(PlannerCustomer record);

    int updateByPrimaryKey(PlannerCustomer record);
}