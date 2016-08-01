package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.CustomerOrgan;
import com.fhzc.app.dao.mybatis.model.CustomerOrganExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CustomerOrganMapper {
    int countByExample(CustomerOrganExample example);

    int deleteByExample(CustomerOrganExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerOrgan record);

    int insertSelective(CustomerOrgan record);

    List<CustomerOrgan> selectByExampleWithRowbounds(CustomerOrganExample example, RowBounds rowBounds);

    List<CustomerOrgan> selectByExample(CustomerOrganExample example);

    CustomerOrgan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerOrgan record, @Param("example") CustomerOrganExample example);

    int updateByExample(@Param("record") CustomerOrgan record, @Param("example") CustomerOrganExample example);

    int updateByPrimaryKeySelective(CustomerOrgan record);

    int updateByPrimaryKey(CustomerOrgan record);
}