package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AssetsHistoryMapper;
import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.AssetsHistoryExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.AssetsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/4 13:36
 */
@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsHistoryMapper assetsHistoryMapper;

    @Override
    public PageableResult<AssetsHistory> findPageAssets(int page, int size) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<AssetsHistory> list = assetsHistoryMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<AssetsHistory>(page, size, assetsHistoryMapper.countByExample(example), list);
    }
}
