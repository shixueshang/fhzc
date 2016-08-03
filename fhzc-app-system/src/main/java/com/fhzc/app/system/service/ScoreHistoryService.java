package com.fhzc.app.system.service;

import org.springframework.web.multipart.MultipartFile;

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
     * 获取用户的有效积分
     * @param uid
     * @return
     */
    Integer getScoreByUserId(Integer uid);
}
