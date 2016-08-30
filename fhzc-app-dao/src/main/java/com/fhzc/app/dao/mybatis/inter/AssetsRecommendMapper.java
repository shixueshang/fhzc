package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.AssetsRecommend;
import com.fhzc.app.dao.mybatis.model.AssetsRecommendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AssetsRecommendMapper {
    int countByExample(AssetsRecommendExample example);

    int deleteByExample(AssetsRecommendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetsRecommend record);

    int insertSelective(AssetsRecommend record);

    List<AssetsRecommend> selectByExampleWithRowbounds(AssetsRecommendExample example, RowBounds rowBounds);

    List<AssetsRecommend> selectByExample(AssetsRecommendExample example);

    AssetsRecommend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetsRecommend record, @Param("example") AssetsRecommendExample example);

    int updateByExample(@Param("record") AssetsRecommend record, @Param("example") AssetsRecommendExample example);

    int updateByPrimaryKeySelective(AssetsRecommend record);

    int updateByPrimaryKey(AssetsRecommend record);
}