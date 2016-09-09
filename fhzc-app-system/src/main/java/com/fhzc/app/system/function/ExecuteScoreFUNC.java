package com.fhzc.app.system.function;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.AssetsService;
import com.fhzc.app.system.service.CustomerService;
import com.fhzc.app.system.service.ProductService;
import com.fhzc.app.system.service.ScoreHistoryService;

public class ExecuteScoreFUNC {
	protected Logger logger = LoggerFactory.getLogger(ExecuteScoreFUNC.class);

	@Resource
	private AssetsService assetsService;

	@Resource
	private ScoreHistoryService scoreHistoryService;

	@Resource
	private CustomerService customerService;

	@Resource
	private ProductService productService;
	
	/**
     * 定时查询投资人档案表的信息，插入到积分表
     */
	public boolean insertScore() {
		// 查询assets_history中当天的并且已经成立的未过期的产品数据
		boolean result=false;
		try {
			List<AssetsHistory> list = assetsService.findAssetsForScore();
			for (AssetsHistory asset : list) {
				Product product = productService.getProduct(asset.getProductId());
				// 根据客户和产品id查询积分历史记录
				Customer customer = customerService.getCustomer(asset.getCustomerId());
				List<ScoreHistory> scoreHistories = scoreHistoryService.findScoreByProduct(customer.getUid(),
						asset.getProductId());
				if (scoreHistories.size() == 0) {
					// 把当前的asset记录插入到历史积分表

					ScoreHistory history = new ScoreHistory();
					history.setUid(customer.getUid());
					history.setScore(calculateScore(product, Integer.parseInt(asset.getPeriod()), asset.getAmount()));
					history.setStatus(Const.Score.ADD);
					history.setDetail("积分增加，类型：产品,名称：" + product.getName() + "");
					history.setEventId(product.getPid());
					history.setVaildTime(DateUtil.getDateNextDays(product.getExpiryDay(), 30));
					history.setFromType(Const.FROM_TYPE.PRODUCT);
					history.setIsApprove(Const.YES_OR_NO.NO);
					history.setIsVaild(Const.SCORE_VAILD.IS_VAILD);
					history.setCtime(new Date());
					scoreHistoryService.addHistoryScore(history);
				}

			}
			result=true;
			logger.info("task execute success");
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
			logger.error("task execute failure", e.getMessage());
		}
		return result;
	}
	
	/**
     * 计算产品积分
     * @param product
     * @param amount
     * @return
     */
    private Integer calculateScore(Product product, Integer term, Integer amount){
        float termFactor = 0;
        if(term >= 36){
            termFactor = 1.2f;
        }else if(term >= 12){
            termFactor = 1.0f;
        }else{
            termFactor = term / 12 ;
        }
        return new BigDecimal(amount).divide(new BigDecimal(500)).multiply(product.getScoreFactor()).multiply(new BigDecimal(termFactor)).setScale(0, RoundingMode.HALF_EVEN).intValue();
    }

}