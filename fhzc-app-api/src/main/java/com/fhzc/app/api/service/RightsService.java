package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;


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
     * 获得权益信息
     * @param id
     * @return
     */
    Rights getRights(Integer id);

    List<Rights> getListByCid(Integer cid);

    List<Rights> getRecommend();

    List<Rights> getLatestRights();
}
