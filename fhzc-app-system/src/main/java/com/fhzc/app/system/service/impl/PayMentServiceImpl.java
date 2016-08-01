package com.fhzc.app.system.service.impl;

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

    private static final String IMPORT_SQL = "call sp_insert_financialPayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SPECIAL_IMPORT_SQL = "call sp_insert_specialfinancialPayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
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
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {
        	List<Object[]> payMentList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[16];
	        		temData[0] = objects[1];	//合同编号 serial
	        		temData[1] = objects[2];	//投资项目 product
	        		temData[2] = objects[3];	//投资人姓名 realname
	        		temData[3] = "身份证";	//证件类型 passport_type	
	        		temData[4] = objects[4];	//证件号码 passport_code
	        		temData[5] = objects[5];	//投资人手机号 phone_num
	        		temData[6] = objects[6];	//投资额 amount_rmb
	        		temData[7] = objects[7];	//到账日期 payment_date
	        		temData[8] = objects[8];	//截止日期 end_date
	        		temData[9] = objects[9];	//投资期限 period
	        		temData[10] = objects[10];	//基准收益率 earing_rate
	        	    temData[11] = objects[11];	//分配收益 rate
	        		temData[12] = objects[12];	//本息合计 total_rate
	        		temData[13] = objects[13];	//开户银行 bank
	        		temData[14] = objects[14];	//帐号 bank_account
	        		temData[15] = objects[15];	//发行公司 pub_agent
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
        }).importExcelFile(multipartFile);

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
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {
        	List<Object[]> spayMentList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[17];
	        		temData[0] = objects[0];	//交易日期 payment_date
	        		temData[1] = objects[1];	//计息日 start_date
	        		temData[2] = objects[2];	//产品名称 product
	        		temData[3] = objects[3];	//投资人姓名 realname	
	        		temData[4] = "身份证";		//证件类型 passport_type
	        		temData[5] = objects[4];	//证件号码 passport_code
	        		temData[6] = objects[5];	//交易额 amount_rmb
	        		temData[7] = objects[6];	//份额类别 part_type
	        		temData[8] = objects[7];	//业务类别 bussiness_type
	        		temData[9] = objects[8];	//计息截止日期 end_date
	        		temData[10] = objects[9];	//收益期 period
	        		temData[12] = objects[10];	//收益利率 earing_rate
	        	    temData[12] = objects[11];	//收益 rate
	        		temData[13] = objects[12];	//返款 return
	        		temData[14] = objects[13];	//返款额 total_rate
	        		temData[15] = objects[14];	//开户行 bank
	        		temData[16] = objects[15];	//付款银行账号 bank_account
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
        }).importExcelFile(multipartFile);

        return importResult;
    }

}
