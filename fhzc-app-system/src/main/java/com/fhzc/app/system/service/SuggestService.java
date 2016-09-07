package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Suggest;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by Double_J on 2016/9/7
 */
public interface SuggestService {
    /**
     * 获得意见列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Suggest> findPageSuggests(int page, int size);
}
