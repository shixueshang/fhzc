package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.Banner;
import com.fhzc.app.dao.mybatis.model.BannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BannerMapper {
    int countByExample(BannerExample example);

    int deleteByExample(BannerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    List<Banner> selectByExampleWithRowbounds(BannerExample example, RowBounds rowBounds);

    List<Banner> selectByExample(BannerExample example);

    Banner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByExample(@Param("record") Banner record, @Param("example") BannerExample example);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}