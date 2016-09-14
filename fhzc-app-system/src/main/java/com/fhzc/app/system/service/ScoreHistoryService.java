package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Double_J on 2016/7/29
 */
public interface ScoreHistoryService {

	/**
     * 积分消费导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFileConsume(MultipartFile multipartFile) throws Exception;
	
    /**
     * 积分奖励导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFileAdd(MultipartFile multipartFile) throws Exception;
    
    /**
     * 一步导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String,Map<String, Object>> importExcel(MultipartFile multipartFile) throws Exception;

    /**
     * 添加积分记录
     * @param scoreHistory
     */
    void addHistoryScore(ScoreHistory scoreHistory);

    /**
     * 查询某个产品用户的增加积分记录
     * @param userId
     * @param productId
     * @return
     */
    List<ScoreHistory> findScoreByProduct(Integer userId, Integer productId);
    
    /**
     * 通过权益预约id获取对应的积分记录
     * @param reservationId
     * @return
     */
    ScoreHistory getSoreHisoryByRerId(Integer reservationId);
}
