package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.SystemRoleModule;
import com.fhzc.app.dao.mybatis.model.SystemRoleModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemRoleModuleMapper {
    int countByExample(SystemRoleModuleExample example);

    int deleteByExample(SystemRoleModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemRoleModule record);

    int insertSelective(SystemRoleModule record);

    List<SystemRoleModule> selectByExampleWithRowbounds(SystemRoleModuleExample example, RowBounds rowBounds);

    List<SystemRoleModule> selectByExample(SystemRoleModuleExample example);

    SystemRoleModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemRoleModule record, @Param("example") SystemRoleModuleExample example);

    int updateByExample(@Param("record") SystemRoleModule record, @Param("example") SystemRoleModuleExample example);

    int updateByPrimaryKeySelective(SystemRoleModule record);

    int updateByPrimaryKey(SystemRoleModule record);
}