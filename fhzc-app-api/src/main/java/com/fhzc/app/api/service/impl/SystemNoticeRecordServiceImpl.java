package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.SystemNoticeRecordService;
import com.fhzc.app.dao.mybatis.inter.SystemNoticeRecordMapper;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecordExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by freeman on 16/8/17.
 */
@Service
public class SystemNoticeRecordServiceImpl implements SystemNoticeRecordService {

    @Resource
    SystemNoticeRecordMapper systemNoticeRecordMapper;

    public List<SystemNoticeRecord> getByUserId(Integer userId){
        SystemNoticeRecordExample example = new SystemNoticeRecordExample();
        SystemNoticeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("id desc");
        RowBounds rowBounds = new RowBounds(0, 50);
        if (systemNoticeRecordMapper.countByExample(example) > 0) {
            return systemNoticeRecordMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        } else {
            return null;
        }
    }
}
