package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.SystemNoticeMapper;
import com.fhzc.app.dao.mybatis.inter.SystemNoticeRecordMapper;
import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.model.SystemNoticeExample;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecordExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.service.NoticeService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/17 14:43
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private SystemNoticeMapper systemNoticeMapper;

    @Resource
    private SystemNoticeRecordMapper systemNoticeRecordMapper;

    @Override
    public PageableResult<SystemNotice> findPageNotices(int page, int size) {
        SystemNoticeExample example = new SystemNoticeExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<SystemNotice> list = systemNoticeMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<SystemNotice>(page, size, systemNoticeMapper.countByExample(example), list);
    }

    @Override
    public void addOrUpdate(SystemNotice systemNotice) {
        Integer id = systemNotice.getId();
        if(id == null){
            systemNoticeMapper.insertSelective(systemNotice);
        }else{
            systemNoticeMapper.updateByPrimaryKeyWithBLOBs(systemNotice);
        }
    }

    @Override
    public void delete(Integer id) {
        systemNoticeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public SystemNotice getSystemNotice(Integer noticeId) {
        return systemNoticeMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public void deleteRecordByNoticeId(Integer noticeId) {
        SystemNoticeRecordExample example = new SystemNoticeRecordExample();
        SystemNoticeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andNoticeIdEqualTo(noticeId);
        systemNoticeRecordMapper.deleteByExample(example);
    }

    @Override
    public void addOrUpdateNoticeRecord(SystemNoticeRecord record) {
        Integer id = record.getId();
        if(id == null){
            systemNoticeRecordMapper.insert(record);
        }else{
            systemNoticeRecordMapper.updateByPrimaryKeyWithBLOBs(record);
        }
    }

    @Override
    public List<SystemNoticeRecord> findRecordByNoticeId(Integer noticeId) {
        SystemNoticeRecordExample example = new SystemNoticeRecordExample();
        SystemNoticeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andPushStatusEqualTo(Const.PUSH_STATUS.WAITTING_PUSH);
        return systemNoticeRecordMapper.selectByExampleWithBLOBs(example);
    }
}
