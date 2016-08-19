package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.SystemLogMapper;
import com.fhzc.app.dao.mybatis.model.SystemLog;
import com.fhzc.app.system.service.SystemLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
