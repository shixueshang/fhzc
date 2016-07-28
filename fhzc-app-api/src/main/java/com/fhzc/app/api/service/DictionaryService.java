package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Dictionary;

import java.util.List;

/**
 * Created by lihongde on 2016/7/22 14:30
 */
public interface DictionaryService {

    /**
     * 根据分类查询字典数据
     * @param cat
     * @return
     */
    List<Dictionary> findDicByType(String cat);
}
