package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.model.SystemNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemNoticeMapper {
    int countByExample(SystemNoticeExample example);

    int deleteByExample(SystemNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemNotice record);

    int insertSelective(SystemNotice record);

    List<SystemNotice> selectByExampleWithBLOBsWithRowbounds(SystemNoticeExample example, RowBounds rowBounds);

    List<SystemNotice> selectByExampleWithBLOBs(SystemNoticeExample example);

    List<SystemNotice> selectByExampleWithRowbounds(SystemNoticeExample example, RowBounds rowBounds);

    List<SystemNotice> selectByExample(SystemNoticeExample example);

    SystemNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemNotice record, @Param("example") SystemNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") SystemNotice record, @Param("example") SystemNoticeExample example);

    int updateByExample(@Param("record") SystemNotice record, @Param("example") SystemNoticeExample example);

    int updateByPrimaryKeySelective(SystemNotice record);

    int updateByPrimaryKeyWithBLOBs(SystemNotice record);

    int updateByPrimaryKey(SystemNotice record);
}