package com.fhzc.app.system.service;



import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Double_J on 2016/7/22
 */
public interface PayMentService {

	/**
	  * 一般兑付表导入
	  * @param multipartFile
	  * @return
	  * @throws Exception
	  */
	 Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;
	 
	 /**
	  * 鑫丰母基金兑付表导入
	  * @param multipartFile
	  * @return
	  * @throws Exception
	  */
	 Map<String, Object> importExcelFileSpecial(MultipartFile multipartFile) throws Exception;
 
}
