package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.AssetsRecommend;
import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;

/**
 * Created by lihongde on 2016/8/4 13:32
 */
public interface AssetsService {

    /**
     * 获得订单列表
     * @param productId
     * @param customerIds
     * @param page
     * @param size
     * @return
     */
    PageableResult<AssetsHistory> findPageAssets(Integer productId, List<Integer> customerIds, int page, int size);

    /**
     * 获得订单列表
     * @return
     */
    List<AssetsHistory> findAllAssets();

    /**
     * 查询当天导入的已成立的未到期的产品数据
     * @return
     */
    List<AssetsHistory> findAssetsForScore();

    /**
     * 查询客户的当前持仓
     * @return
     */
    List<AssetsHistory> findCurrentHoldings(Integer customerId);

    /**
     * 查询客户的历史持仓
     * @return
     */
    List<AssetsHistory> findHistoryHoldings(Integer customerId);
    
    /**
     * 查询所有资产配置推荐
     * @return
     */
    List<AssetsRecommend> findAssetsRecomends();
    
    /**
     * 添加推荐资产配置
     * @param assetsRecommend
     */
    void addOrUpdateAssetsRecommend(AssetsRecommend assetsRecommend);
    
    /**
     * 获取推荐资产配置
     * @param type
     */
    AssetsRecommend getAssetsRecommendByType(String type);
    
    /**
     * 删除推荐资产配置
     * @param type
     */
   void delRecommend(Integer id);
}
