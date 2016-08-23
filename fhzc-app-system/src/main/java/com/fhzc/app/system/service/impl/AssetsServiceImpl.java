package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AssetsHistoryMapper;
import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.AssetsHistoryExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.AssetsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/4 13:36
 */
@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsHistoryMapper assetsHistoryMapper;

    @Override
    public PageableResult<AssetsHistory> findPageAssets(Integer productId, List<Integer> customerIds, int page, int size) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        if(productId != null){
            criteria.andProductIdEqualTo(productId);
        }
        if(customerIds.size() > 0){
            criteria.andCustomerIdIn(customerIds);
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<AssetsHistory> list = assetsHistoryMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<AssetsHistory>(page, size, assetsHistoryMapper.countByExample(example), list);
    }

    @Override
    public List<AssetsHistory> findAllAssets() {
        AssetsHistoryExample example = new AssetsHistoryExample();
        return assetsHistoryMapper.selectByExample(example);
    }

    @Override
    public List<AssetsHistory> findAssetsForScore() {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(Const.ASSETS_TYPE.PURCHASE);
        criteria.andCtimeBetween(DateUtil.getStartTimeOfDate(new Date()), DateUtil.getEndTimeOfDate(new Date()));
        criteria.andProductExpireDayGreaterThan(new Date());
        criteria.andProductFoundDayLessThanOrEqualTo(new Date());
        return assetsHistoryMapper.selectByExample(example);
    }
}
