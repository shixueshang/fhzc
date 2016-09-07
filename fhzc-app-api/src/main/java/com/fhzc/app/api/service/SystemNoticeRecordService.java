package com.fhzc.app.api.service;


import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.model.SystemNoticeRecord;

import java.util.List;

/**
 * Created by freeman on 16/8/17.
 */
public interface SystemNoticeRecordService {

    /**
     * 获得推送给我的公告
     * @param userId
     * @return
     */
    List<SystemNoticeRecord> getNoticeRecordByUser(Integer userId);

    /**
     * 获得服务公告
     * @param noticeId
     * @return
     */
    SystemNotice getNotice(Integer noticeId);


}
