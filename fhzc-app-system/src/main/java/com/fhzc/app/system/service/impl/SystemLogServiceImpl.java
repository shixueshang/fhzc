package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.SystemLogMapper;
import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.model.SystemLog;
import com.fhzc.app.dao.mybatis.model.SystemLogExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.SystemLogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/19 18:17
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Resource
    private SystemLogMapper systemLogMapper;

    @Override
    public void add(SystemLog log) {
        systemLogMapper.insert(log);
    }

    @Override
    public void addByDescription(String description, Admin admin) {
        SystemLog log = new SystemLog();
        log.setUserId(admin.getId());
        log.setLevel(Const.LOG_LEVEL.NORMAL);
        log.setCreateTime(new Date());
        log.setDescription(description);
        log.setRequestIp(admin.getLoginIp());
        log.setType(Const.LOG_TYPE.CONTROLLER);

        systemLogMapper.insert(log);
    }

    @Override
    public PageableResult<SystemLog> findPageLogs(Date date, int page, int size) {
        SystemLogExample example = new SystemLogExample();
        SystemLogExample.Criteria criteria = example.createCriteria();
        if(date != null){
            criteria.andCreateTimeBetween(DateUtil.getStartTimeOfDate(date), DateUtil.getEndTimeOfDate(date));
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<SystemLog> list = systemLogMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<SystemLog>(page, size, systemLogMapper.countByExample(example), list);
    }
}
