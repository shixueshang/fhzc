package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.SuggestService;
import com.fhzc.app.dao.mybatis.inter.SuggestMapper;
import com.fhzc.app.dao.mybatis.model.Suggest;
import com.fhzc.app.dao.mybatis.model.SuggestExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/8/17.
 */
@Service
public class SuggestServiceImpl implements SuggestService{

    @Resource
    SuggestMapper suggestMapper;

    @Override
    public int add(Suggest suggest) {
        return suggestMapper.insert(suggest);
    }

}
