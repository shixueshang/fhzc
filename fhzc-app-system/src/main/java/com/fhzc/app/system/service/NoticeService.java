package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.SystemNotice;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/8/17 14:41
 */
public interface NoticeService {

    /**
     * 获得消息列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<SystemNotice> findPageNotices(int page, int size);

    /**
     * 添加或修改
     * @param systemNotice
     */
    void addOrUpdate(SystemNotice systemNotice);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据noticeId删除record表中的记录
     * @param noticeId
     */
    void deleteByNoticeId(Integer noticeId);
}
