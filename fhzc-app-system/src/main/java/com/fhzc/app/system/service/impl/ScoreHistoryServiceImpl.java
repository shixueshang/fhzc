package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.RightsService;
import com.fhzc.app.system.service.ScoreHistoryService;
import com.fhzc.app.system.service.UserService;

import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

import java.util.*;

/**
 * Created by Double_J on 2016/7/29 
 */
@Service
public class ScoreHistoryServiceImpl implements ScoreHistoryService {
	
	//消费积分
    private static final String IMPORT_CONSUME_SQL = "call sp_consume_scorehistory(?,?,?,?,?,?,?,?,?,?)";
    
    //奖励积分
    private static final String IMPORT__ADD_SQL = "call sp_add_scorehistory(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    @Resource
    private ExcelImporter importer;

	@Resource
	private ScoreHistoryMapper scoreHistoryMapper;
	
	@Resource
	private RightsService rightsService;
	
	@Resource
	private DictionaryService dictionaryService;
	
	@Resource
	private UserService userService;

	//消费积分导入
    @Override
    public Map<String, Object> importExcelFileConsume(MultipartFile multipartFile) throws Exception {
       List<Rights> rs = rightsService.getAllRights();
       List<Dictionary> pdics = dictionaryService.findDicByType("passport");
       List<User> users = userService.findAllUsers();
       int sheetnum = 0;
       int rownum = 2;
    	Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "权益消费") ){
        		if(xwb.getSheetAt(sheetnum).getRow(0).getCell(0) != null){
        			return "报表第" + String.valueOf(sheetnum+1) +"个sheet,表头为："+ xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString() +" 不是正确的报表！";
        		}else{
        			return "报表第" + String.valueOf(sheetnum+1) +"个sheet, 不是正确的报表！";
        		}
        	}else{
        		return null;
        	}
        }

        @Override
        public String getImportSQL() {
            return IMPORT_CONSUME_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
        	List<Object[]> scoreList = new ArrayList<Object[]>();
        	BaseController bc = new BaseController();
        	int operatorID = bc.getCurrentUser().getId();
        	if(data.get(0).length>0){
        		int i = 0;
        		for (Object[] objects : data) {
        			Object[] temData = new  Object[10];
        			//证件类型
        			List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 3, objects[2]);
        			boolean isExist = false;
        			int passport_type = -1;
        			String pcode = "";
        			String key = "";
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Dictionary dictionary : pdics){
    					if(dictionary.getKey().equals(objects[2].toString().trim())){
    						isExist = true;
    						passport_type = TextUtils.StringtoInteger(dictionary.getValue());
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 3, objects[2].toString()+ ", 该证件类型不存在！");
	    				return errordata;
	    			}
		    		
        			//证件类型+证件号码判断人存在
	    			isExist = false;
	    			errordata  = TextUtils.checkEmptyString(i+3, 4, objects[3]);
	    			if (errordata.size() >0){
	    				return errordata; 
	    			}else{
	    				 pcode = objects[3].toString();
		        		 key = pcode.substring(pcode.length()-8);
	    			}
    				for(User user : users){
    					if(user.getPassportCode().equals(EncryptUtils.encryptToDES(key, pcode)) && user.getPassportTypeId() == passport_type ){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 4, objects[3].toString()+ "，该客户证件号码不存在！");
	    				return errordata;
	    			}
		    		
        			//权益名称
             		errordata  = TextUtils.checkEmptyString(i+3, 5, objects[4]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Rights rights : rs){
    					if(rights.getName().equals(objects[4].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 5, objects[4].toString() +",该权益不存在！");
	    				return errordata;
	    			}
		    		
        			//时间
        			errordata  = TextUtils.checkDateString(i+3, 6, objects[5],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
        			//消费积分、剩余积分必填且为数字
        			errordata  = TextUtils.checkNumber(i+3, 8, objects[7], false);
    				if (errordata.size() >0){
	    				return errordata;
	    			}
    				errordata  = TextUtils.checkNumber(i+3, 9, objects[8], false);
    				if (errordata.size() >0){
	    				return errordata;
	    			}
    				
	        		temData[0] = objects[0];										//姓名 
	        		temData[1] = objects[1];										//性别 
	        		temData[2] = objects[2];										//证件类型
	        		temData[3] = EncryptUtils.encryptToDES(key, pcode);				//证件号码
	        		temData[4] = objects[4];										//权益名称
	        		temData[5] = objects[5];										//消费时间 
	        		temData[6] = objects[6];										//消费地点
	        		temData[7] = TextUtils.StringtoInteger(objects[7].toString());	//消费积分
	        		temData[8] = TextUtils.StringtoInteger(objects[8].toString());	//剩余积分
	        		temData[9] = operatorID;										//operatorID
	        		scoreList.add(temData);
	        		i++;
        		}
        	}
            return scoreList;
        }

        @Override
        public ImportCallBack getImportCallBack() {
            return new ImportCallBack() {
                @Override
                public void preOperation(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {

                }

                @Override
                public void postOperation (SqlSessionTemplate sqlSessionTemplate, List < Object[]>data){

                }
            };

        }
        }).importExcelFile(multipartFile,sheetnum,rownum);

        return importResult;
    }

    //积分历史
	@Override
	public Map<String, Object> importExcelFileAdd(MultipartFile multipartFile) throws Exception {
		List<Dictionary> pdics = dictionaryService.findDicByType("passport");
		List<Dictionary> sdics = dictionaryService.findDicByType("score_from_type");
		List<Dictionary> ssdics = dictionaryService.findDicByType("score_status");
		List<User> users = userService.findAllUsers();
		int sheetnum = 0;
		int rownum = 2;
		Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
		        @Override
		        public String validation(Workbook xwb) {
		        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "积分历史") ){
		        		if(xwb.getSheetAt(sheetnum).getRow(0).getCell(0) != null){
		        			return "报表第" + String.valueOf(sheetnum+1) +"个sheet,表头为："+ xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString() +" 不是正确的报表！";
		        		}else{
		        			return "报表第" + String.valueOf(sheetnum+1) +"个sheet, 不是正确的报表！";
		        		}
		        	}else{
		        		return null;
		        	}
		        }

		        @Override
		        public String getImportSQL() {
		            return IMPORT__ADD_SQL;
		        }

		        @Override
		        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
		        	List<Object[]> scoreList = new ArrayList<Object[]>();
		        	BaseController bc = new BaseController();
		        	int operatorID = bc.getCurrentUser().getId();
		        	if(data.get(0).length>0){
		        		int i = 0;
		        		for (Object[] objects : data) {
		        			Object[] temData = new  Object[13];
		        			//证件类型
		        			List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 3, objects[2]);
		        			boolean isExist = false;
		        			int passport_type = -1;
		        			String pcode = "";
		        			String key = "";
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Dictionary dictionary : pdics){
		    					if(dictionary.getKey().equals(objects[2].toString().trim())){
		    						isExist = true;
		    						passport_type = TextUtils.StringtoInteger(dictionary.getValue());
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 3, objects[2].toString()+ ", 该证件类型不存在！");
			    				return errordata;
			    			}
				    		
		        			//证件类型+证件号码判断人存在
				    		isExist = false;
			    			errordata  = TextUtils.checkEmptyString(i+3, 4, objects[3]);
			    			if (errordata.size() >0){
			    				return errordata; 
			    			}else{
			    				 pcode = objects[3].toString();
				        		 key = pcode.substring(pcode.length()-8);
			    			}
		    				for(User user : users){
		    					if(user.getPassportCode().equals(EncryptUtils.encryptToDES(key, pcode)) && user.getPassportTypeId() == passport_type ){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 4, objects[3].toString()+ "，该客户证件号码不存在！");
			    				return errordata;
			    			}
				    		
				    		//积分数字
				    		errordata  = TextUtils.checkNumber(i+3, 8, objects[7], false);
		    				if (errordata.size() >0){
			    				return errordata;
			    			}
		    				
				    		//积分来源
		    				errordata  = TextUtils.checkEmptyString(i+3, 6, objects[5]);
		    				if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Dictionary dictionary : sdics){
		    					if(dictionary.getKey().equals(objects[5].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 6, objects[5].toString()+ ", 该积分来源不正确！");
			    				return errordata;
			    			}
				    		
		        			//时间
		    				errordata  = TextUtils.checkDateString(i+3, 9, objects[8],false);
			    			if (errordata.size() >0){
			    				return errordata;
			    			} 
			    			
			    			//积分增减标识
			    			errordata  = TextUtils.checkEmptyString(i+3, 5, objects[4]);
		    				if (errordata.size() >0){
			    				return errordata;
			    			}
		    				isExist = false;
		    				for(Dictionary dictionary : ssdics){
		    					if(dictionary.getKey().equals(objects[4].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 5, objects[4].toString()+ ", 该积分增减标识不存在！");
			    				return errordata;
			    			}else{
			    				if(objects[4].toString().trim().equals("增加")){
			    					errordata = TextUtils.checkDateString(i+3, 10, objects[9], false);
				    				if (errordata.size() >0){
					    				return errordata;
					    			}
				    				errordata = TextUtils.checkDateString(i+3, 11, objects[10], false);
				    				if (errordata.size() >0){
					    				return errordata;
					    			}
			    				}
			    			}
			        		temData[0] = objects[0];							//姓名
			        		temData[1] = objects[1];							//性别
			        		temData[2] = objects[2];							//证件类型
			        		temData[3] = EncryptUtils.encryptToDES(key, pcode);	//证件号码
			        		temData[4] = objects[4];							//积分增减
			        		temData[5] = objects[5];							//来源类型
			        		temData[6] = objects[6];							//积分来源
			        		temData[7] = objects[7];							//积分值
			        		temData[8] = objects[10];							//有效期
			        		temData[9] = operatorID;							//operatorID
			        		temData[10] = objects[8];							//操作时间
			        		temData[11] = objects[11];							//积分操作方式
			        		temData[12] = objects[12];							//摘要
			        		scoreList.add(temData);
			        		i++;
		        		}
		        	}
		            return scoreList;
		        }

		        @Override
		        public ImportCallBack getImportCallBack() {
		            return new ImportCallBack() {
		                @Override
		                public void preOperation(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {

		                }

		                @Override
		                public void postOperation (SqlSessionTemplate sqlSessionTemplate, List < Object[]>data){

		                }
		            };

		        }
		        }).importExcelFile(multipartFile,sheetnum,rownum);

		        return importResult;
	}

	@Override
	public Map<String, Map<String, Object>> importExcel(MultipartFile multipartFile) throws Exception {
		 Map<String, Object> importResultC = new HashMap<String, Object>();
		 Map<String, Object> importResultA = new HashMap<String, Object>();
		 Map<String,Map<String, Object>> map = new HashMap<String,Map<String, Object>>();
		 importResultC = importExcelFileConsume(multipartFile);
		 importResultA = importExcelFileAdd(multipartFile);
		 map.put("consume", importResultC);
		 map.put("add", importResultA);
		 return map;
	}

	@Override
	public Integer getScoreByUserId(Integer uid) {
		return scoreHistoryMapper.getScoreByUid(uid);
	}

	@Override
	public void addHistoryScore(ScoreHistory scoreHistory) {
		scoreHistoryMapper.insert(scoreHistory);
	}
}
