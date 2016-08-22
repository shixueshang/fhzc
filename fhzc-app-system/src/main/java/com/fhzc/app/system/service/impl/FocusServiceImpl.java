package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.FocusMapper;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.FocusExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.FocusService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/21 17:05
 */
@Service
public class FocusServiceImpl implements FocusService{

    @Resource
    private FocusMapper focusMapper;

    @Override
    public PageableResult<Focus> getFocusByType(String ftype, int page, int size) {
        FocusExample example = new FocusExample();
        FocusExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(ftype)){
            criteria.andFtypeEqualTo(ftype);
        }

        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Focus> focuses = focusMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Focus>(page, size, focusMapper.countByExample(example), focuses);
    }

    @Override
    public List<Focus> findFocusByType(String type, Integer typeId, Integer status) {
        FocusExample example = new FocusExample();
        FocusExample.Criteria criteria = example.createCriteria();
        criteria.andFtypeEqualTo(type);
        criteria.andFidEqualTo(typeId);
        criteria.andStatusEqualTo(status);
        return focusMapper.selectByExample(example);
    }
}
