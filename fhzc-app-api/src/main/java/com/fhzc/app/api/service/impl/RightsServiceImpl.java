package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.RightsService;
import com.fhzc.app.dao.mybatis.inter.RightsMapper;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsExample;

import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/19 19:53
 */
@Service
public class RightsServiceImpl implements RightsService {

    @Resource
    private RightsMapper rightsMapper;

    @Override
    public PageableResult<Rights> findPageRights(int page, int size) {
        RightsExample example = new RightsExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Rights> list = rightsMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Rights>(page, size, list.size(), list);
    }

    @Override
    public Rights getRights(Integer id) {
        return rightsMapper.selectByPrimaryKey(id);
    }


}