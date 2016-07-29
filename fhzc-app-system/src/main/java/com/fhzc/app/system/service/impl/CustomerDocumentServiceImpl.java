package com.fhzc.app.system.service.impl;

import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.service.CustomerDocumentService;
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
public class CustomerDocumentServiceImpl implements CustomerDocumentService {

    private static final String IMPORT_SQL = "call sp_insert_customerdocument(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    @Resource
    private ExcelImporter importer;

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
        	List<Object[]> customerList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[40];
	        		temData[0] = objects[11];	//手机号码 客户初始账号，待确认修改
	        		temData[1] = objects[11];	//手机号码 客户初始密码，待确认修改
	        		temData[2] = objects[0];	//投资人姓名 user--realname
	        		temData[3] = objects[1];	//客户性别 user--gender	
	        		temData[4] = objects[2];	//客户号 customer--cb_id
	        		temData[5] = objects[3];	//证件类型 user--passport_type
	        		temData[6] = objects[4];	//证件号 user-- passport_code
	        		temData[7] = objects[5];	//出生年 user-birthday
	        		temData[8] = objects[6];	//出生月 user--birthday
	        		temData[9] = objects[7];	//出生日 user--birthday
	        		temData[10] = objects[8];	//发证机关 user--passport_agent
	        	    temData[11] = objects[9];	//证件有效期 user--passport_expire
	        		temData[12] = objects[10];	//号码类型 user--mobile
	        		temData[13] = objects[11];	//手机号码 user--mobile
	        		temData[14] = objects[12];	//号码类型 user--mobile
	        		temData[15] = objects[13];	//电话号码 user--mobile
	        		temData[16] = objects[14];	//号码类型 user--phone
	        		temData[17] = objects[15];	//电话号码 user--phone_ext
	        		temData[18] = objects[16];	//电子邮箱 user--email
	        		temData[19] = objects[17];	//身份证地址 user--passport_address
	        		temData[20] = objects[18];	//通讯地址 user--address
	        		temData[21] = objects[19];	//购买产品名称 contract--product_id
	        		temData[22] = objects[20];	//投资额 contract--amount_rmb
	        		temData[23] = objects[21];	//年化投资额 contract--annualised
	        		temData[24] = objects[22];	//资金到帐日 contract--buy_time
	        		temData[25] = objects[23];	//产品成立日 contract--product_found_day
	        		temData[26] = objects[24];	//产品到期日 contract--product_expire_day
	        		temData[27] = objects[25];	//开户银行信息 contract--bank
	        		temData[28] = objects[26];	//银行账号 contract--bank_account
	        		temData[29] = objects[27];	//基金份额 contract--lot
	        		temData[30] = objects[28];	//投资期限 contract--period
	        		temData[31] = objects[29];	//发行机构 contract--pub_agent
	        		temData[32] = objects[30];	//分支机构 contract--branch_agent
	        		temData[33] = objects[31];	//理财师  contract--planner_id
	        		temData[34] = objects[32];	//理财师员工编号
	        		temData[35] = objects[33];	//合同编号 contract--serial
	        		temData[36] = objects[34];	//产品积分值 
	        		temData[37] = objects[35];	//是否会员 contract--is_member
	        		temData[38] = objects[36];	//会员级别 customer--level_id
	        		temData[39] = objects[37];	//备注 contract--memo
	        		customerList.add(temData);
        		}
        	}
            return customerList;
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
