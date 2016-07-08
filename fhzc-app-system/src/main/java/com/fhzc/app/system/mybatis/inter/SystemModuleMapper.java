package com.fhzc.app.system.mybatis.inter;

import com.fhzc.app.system.mybatis.model.SystemModule;
import com.fhzc.app.system.mybatis.model.SystemModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemModuleMapper {
    int countByExample(SystemModuleExample example);

    int deleteByExample(SystemModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    List<SystemModule> selectByExampleWithBLOBsWithRowbounds(SystemModuleExample example, RowBounds rowBounds);

    List<SystemModule> selectByExampleWithBLOBs(SystemModuleExample example);

    List<SystemModule> selectByExampleWithRowbounds(SystemModuleExample example, RowBounds rowBounds);

    List<SystemModule> selectByExample(SystemModuleExample example);

    SystemModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemModule record, @Param("example") SystemModuleExample example);

    int updateByExampleWithBLOBs(@Param("record") SystemModule record, @Param("example") SystemModuleExample example);

    int updateByExample(@Param("record") SystemModule record, @Param("example") SystemModuleExample example);

    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKeyWithBLOBs(SystemModule record);

    int updateByPrimaryKey(SystemModule record);
}