package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.VerifyCode;
import com.fhzc.app.dao.mybatis.model.VerifyCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VerifyCodeMapper {
    int countByExample(VerifyCodeExample example);

    int deleteByExample(VerifyCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    List<VerifyCode> selectByExampleWithRowbounds(VerifyCodeExample example, RowBounds rowBounds);

    List<VerifyCode> selectByExample(VerifyCodeExample example);

    VerifyCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VerifyCode record, @Param("example") VerifyCodeExample example);

    int updateByExample(@Param("record") VerifyCode record, @Param("example") VerifyCodeExample example);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);
}