package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RightsMapper {
    int countByExample(RightsExample example);

    int deleteByExample(RightsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rights record);

    int insertSelective(Rights record);

    List<Rights> selectByExampleWithBLOBsWithRowbounds(RightsExample example, RowBounds rowBounds);

    List<Rights> selectByExampleWithBLOBs(RightsExample example);

    List<Rights> selectByExampleWithRowbounds(RightsExample example, RowBounds rowBounds);

    List<Rights> selectByExample(RightsExample example);

    Rights selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByExampleWithBLOBs(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByExample(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKeyWithBLOBs(Rights record);

    int updateByPrimaryKey(Rights record);
}