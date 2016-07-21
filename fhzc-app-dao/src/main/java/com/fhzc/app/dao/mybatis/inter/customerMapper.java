package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.customer;
import com.fhzc.app.dao.mybatis.model.customerExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface customerMapper {
    int countByExample(customerExample example);

    int deleteByExample(customerExample example);

    int deleteByPrimaryKey(Integer customerId);

    int insert(customer record);

    int insertSelective(customer record);

    List<customer> selectByExampleWithRowbounds(customerExample example, RowBounds rowBounds);

    List<customer> selectByExample(customerExample example);

    customer selectByPrimaryKey(Integer customerId);

    int updateByExampleSelective(@Param("record") customer record, @Param("example") customerExample example);

    int updateByExample(@Param("record") customer record, @Param("example") customerExample example);

    int updateByPrimaryKeySelective(customer record);

    int updateByPrimaryKey(customer record);
}