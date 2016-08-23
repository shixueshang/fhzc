package com.fhzc.app.system.job;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/8/23 12:23
 */
public class ScoreTaskJob {

    protected Logger logger = LoggerFactory.getLogger(ScoreTaskJob.class);

    @Resource
    private AssetsService assetsService;

    @Resource
    private ScoreHistoryService scoreHistoryService;

    @Resource
    private CustomerService customerService;

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    /**
     * 定时查询投资人档案表的信息，插入到积分表
     */
    public void execute(){
        //查询assets_history中当天的并且已经成立的未过期的产品数据
        try{

            List<AssetsHistory> list = assetsService.findAssetsForScore();
            for(AssetsHistory asset : list){
                //根据客户和产品id查询积分历史记录
                Customer customer = customerService.getCustomer(asset.getCustomerId());
                List<ScoreHistory> scoreHistories = scoreHistoryService.findScoreByProduct(customer.getUid(), asset.getProductId());
                if(scoreHistories.size() == 0){
                    //把当前的asset记录插入到历史积分表
                    Product product = productService.getProduct(asset.getProductId());
                    ScoreHistory history = new ScoreHistory();
                    history.setUid(customer.getUid());
                    history.setScore(calculateScore(product, asset.getAmount()));
                    history.setCustomerName(userService.getUser(customer.getUid()).getRealname());
                    history.setDetail("积分增加，类型：产品,名称：" + product.getName() + "");
                    history.setEventId(product.getPid());
                    history.setFromType(Const.FROM_TYPE.PRODUCT);
                    history.setIsApprove(Const.YES_OR_NO.NO);
                    history.setIsVaild(Const.SCORE_VAILD.IS_VAILD);
                    history.setCtime(new Date());
                    scoreHistoryService.addHistoryScore(history);
                }
            }
            logger.info("task execute success");
        }catch (Exception e){
            logger.error("task execute failure", e.getMessage());
        }
    }

    /**
     * 计算产品积分
     * @param product
     * @param amount
     * @return
     */
    private Integer calculateScore(Product product, Integer amount){
        return amount / 500 * product.getScoreFactor().intValue() * Integer.parseInt(product.getRenewDeadline());
    }
}
