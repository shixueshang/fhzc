package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.model.SystemLog;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.Date;

/**
 * Created by lihongde on 2016/8/19 18:16
 */
public interface SystemLogService {

    /**
     * 添加日志
     * @param log
     */
    void add(SystemLog log);

    /**
     * 根据描述添加日志
     * @param description
     * @param admin
     */
    void addByDescription(String description, Admin admin);

    /**
     * 根据操作日期分页查询日志
     * @param date
     * @param page
     * @param size
     * @return
     */
    PageableResult<SystemLog> findPageLogs(Date date, int page, int size);
}
