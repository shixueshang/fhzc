package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Suggest;
import com.fhzc.app.dao.mybatis.model.SuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SuggestMapper {
    int countByExample(SuggestExample example);

    int deleteByExample(SuggestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Suggest record);

    int insertSelective(Suggest record);

    List<Suggest> selectByExampleWithBLOBsWithRowbounds(SuggestExample example, RowBounds rowBounds);

    List<Suggest> selectByExampleWithBLOBs(SuggestExample example);

    List<Suggest> selectByExampleWithRowbounds(SuggestExample example, RowBounds rowBounds);

    List<Suggest> selectByExample(SuggestExample example);

    Suggest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByExampleWithBLOBs(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByExample(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByPrimaryKeySelective(Suggest record);

    int updateByPrimaryKeyWithBLOBs(Suggest record);

    int updateByPrimaryKey(Suggest record);
}