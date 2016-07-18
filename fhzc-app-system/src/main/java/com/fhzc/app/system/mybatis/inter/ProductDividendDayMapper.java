package com.fhzc.app.system.mybatis.inter;

import com.fhzc.app.system.mybatis.model.ProductDividendDay;
import com.fhzc.app.system.mybatis.model.ProductDividendDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProductDividendDayMapper {
    int countByExample(ProductDividendDayExample example);

    int deleteByExample(ProductDividendDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductDividendDay record);

    int insertSelective(ProductDividendDay record);

    List<ProductDividendDay> selectByExampleWithRowbounds(ProductDividendDayExample example, RowBounds rowBounds);

    List<ProductDividendDay> selectByExample(ProductDividendDayExample example);

    ProductDividendDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductDividendDay record, @Param("example") ProductDividendDayExample example);

    int updateByExample(@Param("record") ProductDividendDay record, @Param("example") ProductDividendDayExample example);

    int updateByPrimaryKeySelective(ProductDividendDay record);

    int updateByPrimaryKey(ProductDividendDay record);
}