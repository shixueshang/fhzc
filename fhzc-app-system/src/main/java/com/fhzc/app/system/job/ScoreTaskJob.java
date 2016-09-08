package com.fhzc.app.system.job;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    @Resource
    private ScoreService scoreService;

    /**
     * 定时查询投资人档案表的信息，插入到积分表
     */
    public void execute(){
        //查询assets_history中当天的并且已经成立的未过期的产品数据
        try{
            List<AssetsHistory> list = assetsService.findAssetsForScore();
            for(AssetsHistory asset : list){
                Product product = productService.getProduct(asset.getProductId());
                    //根据客户和产品id查询积分历史记录
                    Customer customer = customerService.getCustomer(asset.getCustomerId());
                    List<ScoreHistory> scoreHistories = scoreHistoryService.findScoreByProduct(customer.getUid(), asset.getProductId());
                    if(scoreHistories.size() == 0){
                        //把当前的asset记录插入到历史积分表

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
            logger.info("task execute success");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("task execute failure", e.getMessage());
        }
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
        return new BigDecimal(amount).divide(new BigDecimal(500)).multiply(product.getScoreFactor().divide(new BigDecimal(100))).multiply(new BigDecimal(termFactor)).setScale(0, RoundingMode.HALF_EVEN).intValue();
    }


    /**
     * 每日任务计算过期积分
     */
    public void run(){
        try{
            //查询当天过期的积分列表
            List<ScoreHistory> expires = scoreService.getExpiredScore();
            //获得存在过期积分的用户
            List<Integer> userIds = new ArrayList<Integer>();
            for(ScoreHistory history : expires){
                if(!userIds.contains(history.getUid())){
                    userIds.add(history.getUid());
                }
            }

            for(Integer userId : userIds){
                Integer consume = Math.abs(scoreService.getConsumeScore(userId));
                Integer frozen = Math.abs(scoreService.getFrozenScore(userId));
                Integer expire = 0;  //客户当天的过期积分
                for(ScoreHistory sh : expires){
                    if(sh.getUid() == userId){
                        expire += sh.getScore();
                    }
                }
                if((consume + frozen) < expire){ //有过期积分, 需要向积分记录表插入一条过期积分
                    Integer score = consume + frozen - expire;
                    ScoreHistory scoreHistory = new ScoreHistory();
                    scoreHistory.setUid(userId);
                    scoreHistory.setStatus(Const.Score.EXPIRE);
                    scoreHistory.setDetail("积分自动失效");
                    scoreHistory.setIsApprove(Const.APPROVE_STATUS.APPROVED);
                    scoreHistory.setIsVaild(Const.SCORE_VAILD.IS_VAILD);
                    scoreHistory.setScore(score);
                    scoreHistory.setCtime(new Date());scoreHistory.setEventId(-1);
                    scoreHistory.setFromType(Const.FROM_TYPE.OTHER);
                    scoreService.addSCoreRecord(scoreHistory);
                }

            }

            logger.info("task execute success");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("task execute failure", e.getMessage());
        }

    }
}
