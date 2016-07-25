package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AreasMapper;
import com.fhzc.app.dao.mybatis.model.Areas;
import com.fhzc.app.dao.mybatis.model.AreasExample;
import com.fhzc.app.system.service.AreasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/25 16:33
 */
@Service
public class AreasServiceImpl implements AreasService {

    @Resource
    private AreasMapper areasMapper;

    @Override
    public List<Areas> getAllAreas() {
        AreasExample example = new AreasExample();
        return areasMapper.selectByExample(example);
    }
}
