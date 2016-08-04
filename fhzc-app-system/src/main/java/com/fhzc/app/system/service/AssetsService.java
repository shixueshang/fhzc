package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/8/4 13:32
 */
public interface AssetsService {

    /**
     * 获得订单列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<AssetsHistory> findPageAssets(int page, int size);
}
