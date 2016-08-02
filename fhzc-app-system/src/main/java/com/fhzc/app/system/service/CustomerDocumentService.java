package com.fhzc.app.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Double_J on 2016/7/22
 */
public interface CustomerDocumentService {

	/**
     * 个人投资人档案表导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFilePersonal(MultipartFile multipartFile) throws Exception;
	
    /**
     * 机构投资人档案表导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFileAgent(MultipartFile multipartFile) throws Exception;
    
    /**
     * 投资人档案表一步导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String,Map<String, Object>> importExcel(MultipartFile multipartFile) throws Exception;


}
