package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;

/**
 * Created by lihongde on 2016/8/21 17:05
 */
public interface FocusService {

    /**
     * 根据关注类型查询关注列表
     *
     * @param ftype
     * @param page
     * @param size
     * @return
     */
    PageableResult<Focus> getFocusByType(String ftype, List<Integer> fids, int page, int size);

    /**
     * 获得某个类型的关注数
     * @param type
     * @param typeId
     * @return
     */
    List<Focus> findFocusByType(String type, Integer typeId);
}
