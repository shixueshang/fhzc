package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.SystemNoticeRecordService;
import com.fhzc.app.api.tools.DateUtil;
import com.fhzc.app.dao.mybatis.inter.SystemNoticeMapper;
import com.fhzc.app.dao.mybatis.inter.SystemNoticeRecordMapper;
import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecordExample;
import com.fhzc.app.dao.mybatis.util.Const;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by freeman on 16/8/17.
 */
@Service
public class SystemNoticeRecordServiceImpl implements SystemNoticeRecordService {

    @Resource
    SystemNoticeRecordMapper systemNoticeRecordMapper;

    @Resource
    private SystemNoticeMapper systemNoticeMapper;


    @Override
    public List<SystemNoticeRecord> getNoticeRecordByUser(Integer userId) {
        SystemNoticeRecordExample example = new SystemNoticeRecordExample();
        SystemNoticeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPushChannelEqualTo(Const.PUSH_CHANNEL.SYSTEM);
        criteria.andPushTimeBetween(DateUtil.getStartTimeOfDate(new Date()), DateUtil.getEndTimeOfDate(new Date()));
        criteria.andPushStatusEqualTo(Const.PUSH_STATUS.PUSHED);
        return systemNoticeRecordMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public SystemNotice getNotice(Integer noticeId) {
        return systemNoticeMapper.selectByPrimaryKey(noticeId);
    }
}
