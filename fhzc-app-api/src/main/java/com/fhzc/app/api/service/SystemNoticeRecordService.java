package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;

import java.util.List;

/**
 * Created by freeman on 16/8/17.
 */
public interface SystemNoticeRecordService {
    List<SystemNoticeRecord> getByUserId(Integer userId);
}
