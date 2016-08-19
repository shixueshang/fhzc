package com.fhzc.app.dao.mybatis.inter;

import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SystemNoticeRecordMapper {
    int countByExample(SystemNoticeRecordExample example);

    int deleteByExample(SystemNoticeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemNoticeRecord record);

    int insertSelective(SystemNoticeRecord record);

    List<SystemNoticeRecord> selectByExampleWithBLOBsWithRowbounds(SystemNoticeRecordExample example, RowBounds rowBounds);

    List<SystemNoticeRecord> selectByExampleWithBLOBs(SystemNoticeRecordExample example);

    List<SystemNoticeRecord> selectByExampleWithRowbounds(SystemNoticeRecordExample example, RowBounds rowBounds);

    List<SystemNoticeRecord> selectByExample(SystemNoticeRecordExample example);

    SystemNoticeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemNoticeRecord record, @Param("example") SystemNoticeRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") SystemNoticeRecord record, @Param("example") SystemNoticeRecordExample example);

    int updateByExample(@Param("record") SystemNoticeRecord record, @Param("example") SystemNoticeRecordExample example);

    int updateByPrimaryKeySelective(SystemNoticeRecord record);

    int updateByPrimaryKeyWithBLOBs(SystemNoticeRecord record);

    int updateByPrimaryKey(SystemNoticeRecord record);
}