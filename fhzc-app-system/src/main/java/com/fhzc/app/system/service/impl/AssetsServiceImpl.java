package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AssetsHistoryMapper;
import com.fhzc.app.dao.mybatis.inter.AssetsRecommendMapper;
import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.AssetsHistoryExample;
import com.fhzc.app.dao.mybatis.model.AssetsRecommend;
import com.fhzc.app.dao.mybatis.model.AssetsRecommendExample;
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
    
    @Resource
    private AssetsRecommendMapper assetsRecommendMapper;

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
        criteria.andProductFoundDayLessThanOrEqualTo(new Date());
        criteria.andExpireDayGreaterThan(new Date());
        return assetsHistoryMapper.selectByExample(example);
    }

    @Override
    public List<AssetsHistory> findCurrentHoldings(Integer customerId) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        criteria.andExpireDayGreaterThan(new Date());

        AssetsHistoryExample.Criteria criteria1 = example.createCriteria();
        criteria1.andCustomerIdEqualTo(customerId);
        criteria1.andPaymentDateIsNull();
        example.or(criteria1);

        return assetsHistoryMapper.selectByExample(example);
    }

    @Override
    public List<AssetsHistory> findHistoryHoldings(Integer customerId) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        criteria.andPaymentDateLessThanOrEqualTo(new Date());
//        criteria.andExpireDayLessThanOrEqualTo(new Date());
        criteria.andTypeEqualTo(Const.ASSETS_TYPE.REDEMPTION);
//        criteria.andPaymentDateIsNull();
        return assetsHistoryMapper.selectByExample(example);
    }

    @Override
    public List<AssetsHistory> findAssetsByProduct(String type, Integer pid) {
        AssetsHistoryExample example = new AssetsHistoryExample();
        AssetsHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(pid);

        return assetsHistoryMapper.selectByExample(example);
    }

	@Override
	public List<AssetsRecommend> findAssetsRecomends() {
		AssetsRecommendExample example = new AssetsRecommendExample();
		AssetsRecommendExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Const.Data_Status.DATA_NORMAL);
		return assetsRecommendMapper.selectByExample(example);
	}

	@Override
	public void addOrUpdateAssetsRecommend(AssetsRecommend assetsRecommend) {
	      Integer id = assetsRecommend.getId();
	        if(id == null){
	        	assetsRecommendMapper.insertSelective(assetsRecommend);
	        }else{
	        	assetsRecommendMapper.updateByPrimaryKeySelective(assetsRecommend);
	        }
	}

	@Override
	public AssetsRecommend getAssetsRecommendByType(String type) {
		AssetsRecommendExample example = new AssetsRecommendExample();
		AssetsRecommendExample.Criteria criteria = example.createCriteria();
		criteria.andRecommendTypeEqualTo(type);
		if(assetsRecommendMapper.countByExample(example)>0){
			return assetsRecommendMapper.selectByExample(example).get(0);
		}
		return null;
	}

	@Override
	public void delRecommend(Integer id) {
		assetsRecommendMapper.deleteByPrimaryKey(id);
	}
}
