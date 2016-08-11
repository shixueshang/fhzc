package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.AssetsHistory;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.service.AssetsService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.PayMentService;
import com.fhzc.app.system.service.PlannerService;
import com.fhzc.app.system.service.ProductService;
import com.fhzc.app.system.service.UserService;

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
    
    @Resource
    private ProductService productService;
    
    @Resource
    private PlannerService plannerService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private AssetsService assetsService;
    
    @Resource
    private UserService userService;
    
    // 一般兑付表导入
    @Override
    public Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception {
		int sheetnum =0;
		int rownum =2;
		PageableResult<Product> prs = productService.findPageProducts(0, 10000);
		PageableResult<AssetsHistory> ahs =  assetsService.findPageAssets(0, 1000000);
		
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "投资兑付明细表") ){
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
            return IMPORT_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
        	List<Object[]> payMentList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
	        	for (int i = 0, length = data.size(); i < length; i++) {
	        		
	    			Object[] objects = data.get(i);
	        		Object[] temData = new  Object[17];
	        		String pcode = objects[5].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		
	        		//错误检验处理
	    			
	    			//检测产品
	    			List<Object[]> errordata  = TextUtils.checkEmptyString(rownum+i+1, 3, objects[2]);
	    			boolean isExist = false;
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			
	    			//检测是否存在
	    			isExist = false;
    				for(Product product :prs.getItems()){
    					if(product.getName().equals(objects[2].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
    				
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(rownum+i+1, 3, objects[2].toString()+ "，该产品不存在！");
	    				return errordata;
	    			}

	    			//检查合同编号是否为空
	    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 2, objects[1]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			//检测合同编号是否存在
	    			isExist = false;
    				for(AssetsHistory ah :ahs.getItems()){
    					if(ah.getSerial().equals(objects[1].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(rownum+i+1, 2, objects[1].toString()+",该合同编号不存在！");
	    				return errordata;
	    			}        		
    			
	    			//检测到账日期
	    			errordata  = TextUtils.checkDateString(rownum+i+1, 9, objects[8],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 	
	    			//检测截止日期
	    			errordata  = TextUtils.checkDateString(rownum+i+1, 10, objects[9],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 		    			
	    			
	    			//检查投资额 
	    			errordata  = TextUtils.checkNumber(rownum+i+1, 8, objects[7],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 	
	    			
	    			//检查投期限 
	    			errordata  = TextUtils.checkNumber(rownum+i+1, 11, objects[10],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 		    			

	    			//检查分配收益 
	    			errordata  = TextUtils.checkNumber(rownum+i+1, 13, objects[12],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
	    			//检查开户银行不能为空
	    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 15, objects[14]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			
	    			//检查银行帐号不能为空
	    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 16, objects[15]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			
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
        }).importExcelFile(multipartFile,sheetnum,rownum);

        return importResult;
    }
    
    //鑫丰母基金导入
    @Override
    public Map<String, Object> importExcelFileSpecial(MultipartFile multipartFile) throws Exception {
		int sheetnum =0;
		int rownum =2;
		List<Dictionary> pdics = dictionaryService.findDicByType("passport");
		PageableResult<User> users = userService.findPageUsers(0,1000000);
		PageableResult<Product> prs = productService.findPageProducts(0, 10000);
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "鑫丰母") ){
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
            return SPECIAL_IMPORT_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception {
        	List<Object[]> spayMentList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
	        	for (int i = 0, length = data.size(); i < length; i++) {
	    			Object[] objects = data.get(i);
	        		Object[] temData = new  Object[18];
	        		//错误检验处理
	    			//检测产品
	    			List<Object[]> errordata  = TextUtils.checkEmptyString(rownum+i+1, 3, objects[2]);
	    			boolean isExist = false;
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			
	    			//检测是否存在
	    			isExist = false;
    				for(Product product :prs.getItems()){
    					if(product.getName().equals(objects[2].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
    				
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(rownum+i+1, 3, objects[2].toString()+ "，该产品不存在！");
	    				return errordata;
	    			}
	        		
		    		int passport_type = -1;
		    		
		    		//判断证件类型是否存在
		    		errordata  = TextUtils.checkEmptyString(rownum+i+1, 5, objects[4]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Dictionary dictionary : pdics){
    					if(dictionary.getKey().equals(objects[4].toString().trim())){
    						isExist = true;
    						passport_type = TextUtils.StringtoInteger(dictionary.getValue());
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(rownum+i+1, 5, objects[4].toString()+ ", 该证件类型不存在！");
	    				return errordata;
	    			}
	        		
		    		//判断证件号码是否存在
		    		errordata  = TextUtils.checkEmptyString(rownum+i+1, 6, objects[5]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}

	        		String pcode = objects[5].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		String pcode_encry = EncryptUtils.encryptToDES(key, pcode);
	    			
	    			//检测是否存在
	    			isExist = false;
    				for(User user :users.getItems()){
    					if(user.getLoginRole().trim().equals("customer")){
	    					if(user.getPassportCode().equals(pcode_encry) && user.getPassportTypeId() == passport_type ){
	    						isExist = true;
	    						break;
	    					}
    					}
    				}
    				
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(rownum+i+1, 6, objects[5].toString()+ "，该客户证件号码不存在！");
	    				return errordata;
	    			}
		    		
	    			//检测到账日期
	    			errordata  = TextUtils.checkDateString(rownum+i+1, 1, objects[0],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 	
	    			//检测截止日期
	    			errordata  = TextUtils.checkDateString(rownum+i+1, 11, objects[10],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 		    			
	    			
	    			//检查投资额 
	    			errordata  = TextUtils.checkNumber(rownum+i+1, 8, objects[7],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 	
	    			
	    			//检查投期限 
	    			errordata  = TextUtils.checkNumber(rownum+i+1, 12, objects[11],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 		    			

	    			//检查返款收益 
	    			errordata  = TextUtils.checkNumber(rownum+i+1, 15, objects[14],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
	    			//检查开户银行不能为空
	    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 17, objects[16]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}

	    			//检查银行帐号不能为空
	    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 18, objects[17]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}		    		
	        		
	        		temData[0] = objects[0];										//交易日期 payment_date
	        		temData[1] = objects[1];										//计息日 start_date
	        		temData[2] = objects[2];										//产品名称 product
	        		temData[3] = objects[3];										//投资人姓名 realname	
	        		temData[4] = objects[4];										//证件类型 passport_type
	        		temData[5] = pcode_encry;										//证件号码 passport_code
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
        }).importExcelFile(multipartFile,sheetnum,rownum);

        return importResult;
    }

}
