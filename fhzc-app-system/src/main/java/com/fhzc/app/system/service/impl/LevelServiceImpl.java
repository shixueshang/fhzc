package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.LevelMapper;
import com.fhzc.app.dao.mybatis.model.Level;
import com.fhzc.app.system.service.LevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/7/22 14:47
 */
@Service
public class LevelServiceImpl implements LevelService {

    @Resource
    private LevelMapper levelMapper;

    public Level findLevelById(int id) {
        return levelMapper.selectByPrimaryKey(id);
    }
}
