package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.SystemAdminModule;
import com.fhzc.app.dao.mybatis.model.SystemAdminModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemAdminModuleMapper {
    int countByExample(SystemAdminModuleExample example);

    int deleteByExample(SystemAdminModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemAdminModule record);

    int insertSelective(SystemAdminModule record);

    List<SystemAdminModule> selectByExampleWithRowbounds(SystemAdminModuleExample example, RowBounds rowBounds);

    List<SystemAdminModule> selectByExample(SystemAdminModuleExample example);

    SystemAdminModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemAdminModule record, @Param("example") SystemAdminModuleExample example);

    int updateByExample(@Param("record") SystemAdminModule record, @Param("example") SystemAdminModuleExample example);

    int updateByPrimaryKeySelective(SystemAdminModule record);

    int updateByPrimaryKey(SystemAdminModule record);
}