package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.model.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ContractMapper {
    int countByExample(ContractExample example);

    int deleteByExample(ContractExample example);

    int deleteByPrimaryKey(@Param("id") Integer id, @Param("period") String period);

    int insert(Contract record);

    int insertSelective(Contract record);

    List<Contract> selectByExampleWithRowbounds(ContractExample example, RowBounds rowBounds);

    List<Contract> selectByExample(ContractExample example);

    Contract selectByPrimaryKey(@Param("id") Integer id, @Param("period") String period);

    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}