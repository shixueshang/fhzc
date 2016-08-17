package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.SystemNoticeMapper;
import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.model.SystemNoticeExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
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
            systemNoticeMapper.insert(systemNotice);
        }else{
            systemNoticeMapper.updateByPrimaryKeyWithBLOBs(systemNotice);
        }
    }

    @Override
    public void delete(Integer id) {
        systemNoticeMapper.deleteByPrimaryKey(id);
    }
}
