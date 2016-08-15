package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.AboutApp;
import com.fhzc.app.dao.mybatis.model.AboutAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AboutAppMapper {
    int countByExample(AboutAppExample example);

    int deleteByExample(AboutAppExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AboutApp record);

    int insertSelective(AboutApp record);

    List<AboutApp> selectByExampleWithBLOBsWithRowbounds(AboutAppExample example, RowBounds rowBounds);

    List<AboutApp> selectByExampleWithBLOBs(AboutAppExample example);

    List<AboutApp> selectByExampleWithRowbounds(AboutAppExample example, RowBounds rowBounds);

    List<AboutApp> selectByExample(AboutAppExample example);

    AboutApp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AboutApp record, @Param("example") AboutAppExample example);

    int updateByExampleWithBLOBs(@Param("record") AboutApp record, @Param("example") AboutAppExample example);

    int updateByExample(@Param("record") AboutApp record, @Param("example") AboutAppExample example);

    int updateByPrimaryKeySelective(AboutApp record);

    int updateByPrimaryKeyWithBLOBs(AboutApp record);

    int updateByPrimaryKey(AboutApp record);
}