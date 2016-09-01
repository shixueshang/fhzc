package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.AssetsService;
import com.fhzc.app.dao.mybatis.inter.AssetsHistoryMapper;
import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.AssetsHistoryExample;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by freeman on 16/8/3.
 */
@Service
public class AssetsServiceImpl implements AssetsService{

    @Resource
    AssetsHistoryMapper assetsHistoryMapper;

    @Override
    public List<AssetsHistory> getHistory(Integer customer_id) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customer_id);
        criteria.andInvaildEqualTo((byte) 1);
        if(assetsHistoryMapper.countByExample(example) > 0){
            return assetsHistoryMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<AssetsHistory> getPayHistoryByPlannerId(Integer planner_id) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("payment_date desc");
        criteria.andPlannerIdEqualTo(planner_id);
        criteria.andTypeEqualTo(Const.ASSET_TYPE.PURCHASE);
        criteria.andPaymentDateIsNotNull();
        if(assetsHistoryMapper.countByExample(example) > 0){
            return assetsHistoryMapper.selectByExample(example);
        }
        return null;
    }
}
