package com.fhzc.app.system.service;

import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.mybatis.model.Rights;

/**
 * Created by lihongde on 2016/7/19 19:50
 */
public interface RightsService {

    /**
     * 获得权益列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Rights> findPageRights(int page, int size);

    /**
     * 添加或这修改权益
     * @param rights
     */
    void addOrUpdateRights(Rights rights);

    /**
     * 获得权益信息
     * @param id
     * @return
     */
    Rights getRights(Integer id);
}
