package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.Level;

import java.util.List;

/**
 * Created by lihongde on 2016/7/22 14:30
 */
public interface LevelService {

    /**
     * 根据id查找level
     * @param id
     * @return
     */
    Level findLevelById(int id);
}
