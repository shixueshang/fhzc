package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ScoreHistoryMapper;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.ScoreHistoryService;
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

	//消费积分导入
    @Override
    public Map<String, Object> importExcelFileConsume(MultipartFile multipartFile) throws Exception {
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
            return null;
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
        		for (Object[] objects : data) {
        			Object[] temData = new  Object[10];
	        		String pcode = objects[3].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		temData[0] = objects[0];									//姓名 
	        		temData[1] = objects[1];									//性别 
	        		temData[2] = objects[2];									//证件类型
	        		temData[3] = EncryptUtils.encryptToDES(key, pcode);			//证件号码
	        		temData[4] = objects[4];									//权益名称
	        		temData[5] = objects[5];									//消费时间 
	        		temData[6] = objects[6];									//消费地点
	        		temData[7] = TextUtils.FloatToInt(objects[7].toString());	//消费积分
	        		temData[8] = TextUtils.FloatToInt(objects[8].toString());	//剩余积分
	        		temData[9] = operatorID;									//operatorID
	        		scoreList.add(temData);
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
        }).importExcelFile(multipartFile);

        return importResult;
    }

    //积分历史
	@Override
	public Map<String, Object> importExcelFileAdd(MultipartFile multipartFile) throws Exception {
		 Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
		        @Override
		        public String validation(Workbook xwb) {
		            return null;
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
		        		for (Object[] objects : data) {
		        			Object[] temData = new  Object[13];
			        		String pcode = objects[3].toString();
			        		String key = pcode.substring(pcode.length()-8);
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
		        }).importExcelFile(multipartFile);

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
