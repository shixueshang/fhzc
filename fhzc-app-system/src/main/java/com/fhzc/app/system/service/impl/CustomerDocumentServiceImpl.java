package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.service.AssetsService;
import com.fhzc.app.system.service.CustomerDocumentService;
import com.fhzc.app.system.service.DepartmentService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.PlannerService;
import com.fhzc.app.system.service.ProductService;

import org.apache.commons.codec.digest.DigestUtils;
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
	
	//个人投资人
    private static final String IMPORT_PERSONAL_SQL = "call sp_insert_customerdocument(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    //机构投资人
    private static final String IMPORT__AGENT_SQL = "call sp_insert_customerdocument(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    @Resource
    private ExcelImporter importer;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private ProductService productService;
    
    @Resource
    private DepartmentService departmentService;
    
    @Resource
    private PlannerService plannerService;
    
    @Resource
    private AssetsService assetsService;

    //个人客户导入
    @Override
    public Map<String, Object> importExcelFilePersonal(MultipartFile multipartFile) throws Exception {
       List<Dictionary> pdics = dictionaryService.findDicByType("passport");
       List<Product> prs = productService.findAllProduct();
       List<Department> deps = departmentService.findAllDepartment();
       List<Planner> planners =  plannerService.findAllPlanner();
       List<Dictionary> cdics = dictionaryService.findDicByType("customer_level");
	   List<AssetsHistory> ahs =  assetsService.findAllAssets();
       int sheetnum = 0;
       int rownum = 2;
    	Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "个人") ){
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
            return IMPORT_PERSONAL_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
        	List<Object[]> customerList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		int i = 0;
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[42];
	        		String phone = TextUtils.IntToDouble(objects[11].toString());

	        		//客户号必填
	        		List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 3, objects[2]);
	        		boolean isExist = false;
	        		if (errordata.size() >0){
	    				return errordata; 
	    			}
	        		
	        		//判断证件类型的写法与数据库保持一致
		    		errordata  = TextUtils.checkEmptyString(i+3, 4, objects[3]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Dictionary dictionary : pdics){
    					if(dictionary.getKey().equals(objects[3].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 4, objects[3].toString()+ ", 该证件类型不存在！");
	    				return errordata;
	    			}
		    		
	        		//证件号必填
		    		errordata  = TextUtils.checkEmptyString(i+3, 5, objects[4]);
		    		if (errordata.size() >0){
	    				return errordata;
	    			}
		    		
	        		//年月日数字
		    		errordata  = TextUtils.checkNumber(i+3, 6, objects[5], false);
		    		if (errordata.size() >0){
	    				return errordata;
	    			}
    				errordata  = TextUtils.checkNumber(i+3, 7, objects[6], false);
    				if (errordata.size() >0){
	    				return errordata;
	    			}
    				errordata  = TextUtils.checkNumber(i+3, 8, objects[7], false);
    				if (errordata.size() >0){
	    				return errordata;
	    			}
    				
    				//校验手机号
	        		errordata  = TextUtils.validPhoneNum(i+3, 12, phone,false);
	        		if (errordata.size() >0){
	    				return errordata;
	    			}
	        		
	        		//检验产品列不为空,且购买产品存在
	        		errordata  = TextUtils.checkEmptyString(i+3, 20, objects[19]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Product product :prs){
    					if(product.getName().equals(objects[19].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 20, objects[19].toString() +",该产品不存在！");
	    				return errordata;
	    			}
		    		
	        		//投资额、年化额
		    		errordata  = TextUtils.checkNumber(i+3, 21, objects[20],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			errordata  = TextUtils.checkNumber(i+3, 22, objects[21],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
	        		//资金到账日/产品成立日/产品到期日
	    			errordata  = TextUtils.checkDateString(i+3, 23, objects[22],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			errordata  = TextUtils.checkDateString(i+3, 24, objects[23],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			errordata  = TextUtils.checkDateString(i+3, 25, objects[24],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
	        		//银行、账号必填
	    			errordata  = TextUtils.checkEmptyString(i+3, 26, objects[25]);
	    			if (errordata.size() >0){
	    				return errordata; 
	    			}
	    			errordata  = TextUtils.checkEmptyString(i+3, 27, objects[26]);
	    			if (errordata.size() >0){
	    				return errordata; 
	    			}
	    			
	        		//投资期限
	    			errordata  = TextUtils.checkNumber(i+3, 29, objects[28],false);
	    			if (errordata.size() >0){
	    				return errordata; 
	    			}
	    			
	        		//分支机构
	    			errordata  = TextUtils.checkEmptyString(i+3, 31, objects[30]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Department department :deps){
    					if(department.getTitle().equals(objects[30].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 31, objects[30].toString()+ ", 该分公司不存在！");
	    				return errordata;
	    			}
		    		
	        		//理财师工号
		    		errordata  = TextUtils.checkEmptyString(i+3, 33, objects[32]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Planner planner :planners){
    					if(planner.getWorkNum().equals(objects[32].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 33, objects[32].toString()+ ", 该理财师编号不存在！");
	    				return errordata;
	    			}
		    		
	        		//合同编号
		    		errordata  = TextUtils.checkEmptyString(i+3, 34, objects[33]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			
	    			//检测合同编号是否存在
    				for(AssetsHistory ah : ahs){
    					if(ah.getSerial().equals(objects[33].toString().trim())){
    						errordata = TextUtils.setErrorMessage(i+3, 34, objects[33].toString()+",该合同编号已存在！");
    	    				return errordata;
    					}
    					break;
    				}
    				
	        		//会员级别
	    			errordata  = TextUtils.checkEmptyString(i+3, 37, objects[36]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Dictionary dictionary : cdics){
    					if(dictionary.getKey().equals(objects[36].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 37, objects[36].toString()+ ", 该会员级别不存在！");
	    				return errordata;
	    			}
		    		
	        		String pcode = objects[4].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		temData[0] = phone;														//手机号码 客户初始账号，待确认修改
	        		temData[1] = DigestUtils.md5Hex(phone);									//手机号码 客户初始密码，待确认修改
	        		temData[2] = objects[0];												//投资人姓名 user--realname
	        		temData[3] = objects[1];												//客户性别 user--gender	
	        		temData[4] = objects[2];												//客户号 customer--cb_id
	        		temData[5] = objects[3];												//证件类型 user--passport_type
	        		temData[6] = EncryptUtils.encryptToDES(key, pcode);						//证件号码
	        		temData[7] = TextUtils.StringtoInteger(objects[5].toString());			//出生年 user-birthday
	        		temData[8] = TextUtils.StringtoInteger(objects[6].toString());			//出生月 user--birthday
	        		temData[9] = TextUtils.StringtoInteger(objects[7].toString());			//出生日 user--birthday
	        		temData[10] = objects[8];												//发证机关 user--passport_agent
	        	    temData[11] = objects[9];												//证件有效期 user--passport_expire
	        		temData[12] = objects[10];												//号码类型 user--mobile
	        		temData[13] =  EncryptUtils.encryptToDES(key, phone);					//手机号码 user--mobile
	        		temData[14] = objects[12];												//号码类型 user--mobile
	        		temData[15] =  EncryptUtils.encryptToDES(key, objects[13].toString());	//电话号码 user--mobile
	        		temData[16] = objects[14];												//号码类型 user--phone
	        		temData[17] =  EncryptUtils.encryptToDES(key, objects[15].toString());	//电话号码 user--mobile
	        		temData[18] = objects[16];												//电子邮箱 user--email
	        		temData[19] = objects[17];												//身份证地址 user--passport_address
	        		temData[20] = objects[18];												//通讯地址 user--address
	        		temData[21] = objects[19];												//购买产品名称 contract--product_id
	        		temData[22] = TextUtils.StringtoInteger(objects[20].toString());		//投资额 contract--amount_rmb
	        		temData[23] = TextUtils.StringtoInteger(objects[21].toString());		//年化金额 annualised
	        		temData[24] = objects[22];												//资金到帐日 contract--buy_time
	        		temData[25] = objects[23];												//产品成立日 contract--product_found_day
	        		temData[26] = objects[24];												//产品到期日 contract--product_expire_day
	        		temData[27] = objects[25];												//开户银行信息 contract--bank
	        		temData[28] = objects[26];												//银行账号 contract--bank_account
	        		temData[29] = objects[27];												//基金份额 contract--lot
	        		temData[30] = TextUtils.StringtoInteger(objects[28].toString());		//投资期限 contract--period
	        		temData[31] = objects[29];												//发行机构 contract--pub_agent
	        		temData[32] = objects[30];												//分支机构 contract--branch_agent
	        		temData[33] = objects[31];												//理财师  contract--planner_id
	        		temData[34] = objects[32];												//理财师员工编号
	        		temData[35] = objects[33];												//合同编号 contract--serial
	        		temData[36] = TextUtils.StringtoInteger(objects[34].toString());		//产品积分值 
	        		temData[37] = objects[35];												//是否会员 contract--is_member
	        		temData[38] = objects[36];												//会员级别 customer--level_id
	        		temData[39] = objects[37];												//备注 contract--memo
	        		temData[40] = key;														//加密key
	        		temData[41] = "个人";													//投资人类型
	        		customerList.add(temData);
	        		i++;
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
        }).importExcelFile(multipartFile,sheetnum,rownum);

        return importResult;
    }

    //机构客户导入
	@Override
	public Map<String, Object> importExcelFileAgent(MultipartFile multipartFile) throws Exception {
	   List<Dictionary> pdics = dictionaryService.findDicByType("passport");
       List<Product> prs = productService.findAllProduct();
       List<Department> deps = departmentService.findAllDepartment();
       List<Planner> planners =  plannerService.findAllPlanner();
       List<Dictionary> cdics = dictionaryService.findDicByType("customer_level");
       List<AssetsHistory> ahs =  assetsService.findAllAssets();
	   int sheetnum = 0;
	   int rownum = 2 ;
		Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
		        @Override
		        public String validation(Workbook xwb) {
		        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "机构") ){
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
		            return IMPORT__AGENT_SQL;
		        }

		        @Override
		        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
		        	List<Object[]> customerList = new ArrayList<Object[]>();
		        	if(data.get(0).length>0){
		        		int i = 0;
		        		for (Object[] objects : data) {
			        		Object[] temData = new  Object[42];
			        		String phone = TextUtils.IntToDouble(objects[9].toString());

			        		//客户号
			        		List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 2, objects[1]);
			        		boolean isExist = false;
			        		if (errordata.size() >0){
			    				return errordata; 
			    			}
			        		
			        		//证件类型
			        		errordata  = TextUtils.checkEmptyString(i+3, 3, objects[2]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Dictionary dictionary : pdics){
		    					if(dictionary.getKey().equals(objects[2].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 3, objects[2].toString()+ ", 该证件类型不存在！");
			    				return errordata;
			    			}
				    		
			        		//机构证件号必填
				    		errordata  = TextUtils.checkEmptyString(i+3, 4, objects[3]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			
			        		//手机号码
			        		errordata  = TextUtils.validPhoneNum(i+3, 10, phone,false);
			        		if (errordata.size() >0){
			    				return errordata;
			    			}
			        		
			        		//产品
			        		errordata  = TextUtils.checkEmptyString(i+3, 17, objects[16]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Product product :prs){
		    					if(product.getName().equals(objects[16].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 17, objects[16].toString() +",该产品不存在！");
			    				return errordata;
			    			}
				    		
			        		//投资额
				    		errordata  = TextUtils.checkNumber(i+3, 18, objects[17],false);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			
			        		//资金到账日/产品成立日/产品到期日
			    			errordata  = TextUtils.checkDateString(i+3, 29, objects[18],false);
			    			if (errordata.size() >0){
			    				return errordata;
			    			} 
			    			errordata  = TextUtils.checkDateString(i+3, 20, objects[19],false);
			    			if (errordata.size() >0){
			    				return errordata;
			    			} 
			    			errordata  = TextUtils.checkDateString(i+3, 21, objects[20],false);
			    			if (errordata.size() >0){
			    				return errordata;
			    			} 
			    			
			        		//银行、账号必填
			    			errordata  = TextUtils.checkEmptyString(i+3, 22, objects[21]);
			    			if (errordata.size() >0){
			    				return errordata; 
			    			}
			    			errordata  = TextUtils.checkEmptyString(i+3, 23, objects[22]);
			    			if (errordata.size() >0){
			    				return errordata; 
			    			}
			    			
			        		//投资期限
			    			errordata  = TextUtils.checkNumber(i+3, 25, objects[24],false);
			    			if (errordata.size() >0){
			    				return errordata; 
			    			}
			    			
			        		//分支机构
			    			errordata  = TextUtils.checkEmptyString(i+3, 27, objects[26]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Department department :deps){
		    					if(department.getTitle().equals(objects[26].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 27, objects[26].toString()+ ", 该分公司不存在！");
			    				return errordata;
			    			}
				    		
			        		//理财师工号
				    		errordata  = TextUtils.checkEmptyString(i+3, 29, objects[28]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Planner planner :planners){
		    					if(planner.getWorkNum().equals(objects[28].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 29, objects[28].toString()+ ", 该理财师编号不存在！");
			    				return errordata;
			    			}
				    		
			        		//合同编号
				    		errordata  = TextUtils.checkEmptyString(i+3, 30, objects[29]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			
			    			//检测合同编号是否存在
		    				for(AssetsHistory ah : ahs){
		    					if(ah.getSerial().equals(objects[29].toString().trim())){
		    						errordata = TextUtils.setErrorMessage(i+3, 30, objects[29].toString()+",该合同编号已存在！");
		    	    				return errordata;
		    					}
		    					break;
		    				}
		    				
			        		//会员级别
			    			errordata  = TextUtils.checkEmptyString(i+3, 33, objects[32]);
			    			if (errordata.size() >0){
			    				return errordata;
			    			}
			    			isExist = false;
		    				for(Dictionary dictionary : cdics){
		    					if(dictionary.getKey().equals(objects[32].toString().trim())){
		    						isExist = true;
		    						break;
		    					}
		    				}
				    		if(!isExist){
			    				errordata = TextUtils.setErrorMessage(i+3, 33, objects[32].toString()+ ", 该会员级别不存在！");
			    				return errordata;
			    			}
			        		String pcode = objects[3].toString();
			        		String key = pcode.substring(pcode.length()-8);
			        		
			        		temData[0] = phone;														//手机号码 客户初始账号，待确认修改
			        		temData[1] = DigestUtils.md5Hex(phone);									//手机号码 客户初始密码，待确认修改
			        		temData[2] = objects[0];												//投资人姓名 user--realname
			        		temData[3] = objects[7];												//联系人性别 user--gender
			        		temData[4] = objects[1];												//客户号 customer--cb_id
			        		temData[5] = objects[2];												//证件类型 user--passport_type
			        		temData[6] = EncryptUtils.encryptToDES(key, pcode);						//证件号 user-- passport_code
			        		temData[7] = "1900";													//出生年 user-birthday，机构客户无此信息为了和个人客户使用同一个存储过程，临时赋值
			        		temData[8] = "1";														//出生月 user--birthday
			        		temData[9] = "1";														//出生日 user--birthday
			        		temData[10] = "";														//发证机关 user--passport_agent
			        		temData[11] = objects[4];												//证件有效期 user--passport_agent
			        		temData[12] = objects[8];												//号码类型 user--mobile
			        		temData[13] = EncryptUtils.encryptToDES(key, phone);;					//手机号码 user--mobile
			        		temData[14] = objects[10];												//号码类型 user--mobile
			        		temData[15] = EncryptUtils.encryptToDES(key, objects[11].toString());	//电话号码 user--mobile
			        		temData[16] = objects[12];												//号码类型 user--phone
			        		temData[17] = EncryptUtils.encryptToDES(key, objects[13].toString());	//电话号码 user--phone_ext
			        		temData[18] = "";														//电子邮箱 user--email
			        		temData[19] = objects[14];												//注册地址 passport_address
			        		temData[20] = objects[15];												//通讯地址 user--passport_address
			        		temData[21] = objects[16];												//购买产品名称 contract--product_id
			        		temData[22] = TextUtils.StringtoInteger(objects[17].toString());		//投资额 contract--amount_rmb
			        		temData[23] = TextUtils.StringtoInteger(objects[17].toString());		//年化金额
			        		temData[24] = objects[18];												//资金到帐日 contract--buy_time
			        		temData[25] = objects[19];												//产品成立日 contract--product_found_day
			        		temData[26] = objects[20];												//产品到期日 contract--product_expire_day
			        		temData[27] = objects[21];												//开户银行信息 contract--bank
			        		temData[28] = objects[22];												//银行账号 contract--bank_account
			        		temData[29] = objects[23];												//基金份额 contract--lot
			        		temData[30] = TextUtils.StringtoInteger(objects[24].toString());		//投资期限 contract--period
			        		temData[31] = objects[25];												//发行机构 contract--pub_agent
			        		temData[32] = objects[26];												//分支机构 contract--branch_agent
			        		temData[33] = objects[27];												//理财师  contract--planner_id
			        		temData[34] = objects[28];												//理财师员工编号
			        		temData[35] = objects[29];												//合同编号 contract--serial
			        		temData[36] = TextUtils.StringtoInteger(objects[30].toString());		//产品积分值 
			        		temData[37] = objects[31];												//是否会员 contract--is_member
			        		temData[38] = objects[32];												//会员级别 customer--level_id
			        		temData[39] = objects[33];												//备注 contract--memo
			        		temData[40] = key;														//加密key
			        		temData[41] = "机构";													//投资人类型
			        		customerList.add(temData);
			        		i++;
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
		        }).importExcelFile(multipartFile,sheetnum,rownum);

		        return importResult;
	}
	
	@Override
	public Map<String, Map<String, Object>> importExcel(MultipartFile multipartFile) throws Exception {
		 Map<String, Object> importResultP = new HashMap<String, Object>();
		 Map<String, Object> importResultA = new HashMap<String, Object>();
		 Map<String,Map<String, Object>> map = new HashMap<String,Map<String, Object>>();
		 importResultP = importExcelFilePersonal(multipartFile);
		 importResultA = importExcelFileAgent(multipartFile);
		 map.put("personal", importResultP);
		 map.put("agent", importResultA);
		 return map;
	}

}
