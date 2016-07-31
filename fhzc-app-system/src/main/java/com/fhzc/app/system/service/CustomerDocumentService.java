package com.fhzc.app.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Double_J on 2016/7/22
 */
public interface CustomerDocumentService {

	   /**
     * 投资人档案表导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;


}
