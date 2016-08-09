package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.service.PayMentService;

import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Double_J on 2016/7/22 
 */
@Service
public class PayMentServiceImpl implements PayMentService {
	
	//一般对付
    private static final String IMPORT_SQL = "call sp_insert_financialPayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    //鑫丰母兑付
    private static final String SPECIAL_IMPORT_SQL = "call sp_insert_specialfinancialPayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    @Resource
    private ExcelImporter importer;
    
    
    // 一般兑付表导入
    @Override
    public Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception {
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
            return null;
        }

        @Override
        public String getImportSQL() {
            return IMPORT_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
        	List<Object[]> payMentList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[17];
	        		String pcode = objects[5].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		temData[0] = objects[1];											//合同编号 serial
	        		temData[1] = objects[2];											//投资项目 product
	        		temData[2] = objects[3];											//投资人姓名 realname
	        		temData[3] = objects[4];											//投资人类型
	        		temData[4] = "";													//证件类型 passport_type	
	        		temData[5] = EncryptUtils.encryptToDES(key, pcode);					//证件号码 passport_code
	        		temData[6] = EncryptUtils.encryptToDES(key,objects[6].toString());	//投资人手机号 phone_num
	        		temData[7] = TextUtils.StringtoInteger(objects[7].toString());		//投资额 amount_rmb
	        		temData[8] = objects[8];											//到账日期 payment_date
	        		temData[9] = objects[9];											//截止日期 end_date
	        		temData[10] = TextUtils.StringtoInteger(objects[10].toString());	//投资期限 period
	        		temData[11] = objects[11];											//基准收益率 earing_rate
	        	    temData[12] = objects[12];											//分配收益 rate
	        		temData[13] = objects[13];											//本息合计 total_rate
	        		temData[14] = objects[14];											//开户银行 bank
	        		temData[15] = objects[15];											//帐号 bank_account
	        		temData[16] = objects[16];											//发行公司 pub_agent
	        		payMentList.add(temData);
        		} 
        	}
            return payMentList;
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
        }).importExcelFile(multipartFile,0,2);

        return importResult;
    }
    
    //鑫丰母基金导入
    @Override
    public Map<String, Object> importExcelFileSpecial(MultipartFile multipartFile) throws Exception {
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
            return null;
        }

        @Override 
        public String getImportSQL() {
            return SPECIAL_IMPORT_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
        	List<Object[]> spayMentList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[18];
	        		String pcode = objects[5].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		temData[0] = objects[0];										//交易日期 payment_date
	        		temData[1] = objects[1];										//计息日 start_date
	        		temData[2] = objects[2];										//产品名称 product
	        		temData[3] = objects[3];										//投资人姓名 realname	
	        		temData[4] = objects[4];										//证件类型 passport_type
	        		temData[5] = EncryptUtils.encryptToDES(key, pcode);				//证件号码 passport_code
	        		temData[6] = objects[6];										//投资人类型
	        		temData[7] = TextUtils.StringtoInteger(objects[7].toString());  //交易额 amount_rmb
	        		temData[8] = objects[8];										//份额类别 part_type
	        		temData[9] = objects[9];										//业务类别 bussiness_type
	        		temData[10] = objects[10];										//计息截止日期 end_date
	        		temData[11] = objects[11];										//收益期 period
	        		temData[12] = objects[12];										//收益利率 earing_rate
	        	    temData[13] = objects[13];										//收益 rate
	        		temData[14] = objects[14];										//返款 return
	        		temData[15] = objects[15];										//返款额 total_rate
	        		temData[16] = objects[16];										//开户行 bank
	        		temData[17] = objects[17];										//付款银行账号 bank_account
	        		spayMentList.add(temData);
        		} 
        	}
            return spayMentList;
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
        }).importExcelFile(multipartFile,0,2);

        return importResult;
    }

}
