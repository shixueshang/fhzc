package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.SystemLog;

/**
 * Created by lihongde on 2016/8/19 18:16
 */
public interface SystemLogService {

    /**
     * 添加日志
     * @param log
     */
    void add(SystemLog log);
}
