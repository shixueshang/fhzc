package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.model.ContractExample;
import com.fhzc.app.dao.mybatis.model.Department;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.ContractMapper;
import com.fhzc.app.system.service.ContractService;
import com.fhzc.app.system.service.DepartmentService;
import com.fhzc.app.system.service.DictionaryService;
import com.fhzc.app.system.service.PlannerService;
import com.fhzc.app.system.service.ProductService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Double_J on 2016/7/22 
 */
@Service("contractService")
public class ContractServiceImpl implements ContractService {

    private static final String IMPORT_SQL = "call sp_insert_financialDaily(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Resource
    private ExcelImporter importer;

    @Resource
    private ContractMapper contractMapper;
    
    @Resource
    private ProductService productService;
    
    @Resource
    private PlannerService plannerService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private DepartmentService departmentService;
    
    @Override
    public PageableResult<Contract> findPageContracts(int page, int size) {
        ContractExample example = new ContractExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Contract> list = contractMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Contract>(page, size, list.size(), list);
    }

    @Override
    public void addOrUpdateContract(Contract contract) {
        Integer pid = contract.getId();
        if(pid == null){
            contractMapper.insertSelective(contract);
        }else{
            contractMapper.updateByPrimaryKeySelective(contract);
        }
    }

    @Override
    public Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception {
    	List<Product> prs = productService.findAllProduct();
    	List<Planner> planners =  plannerService.findAllPlanner();
    	List<Dictionary> dics = dictionaryService.findDicByType("passport");
    	List<Department> deps = departmentService.findAllDepartment();
    	int sheetnum = 0;
    	int rownum = 3;
    	Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "发行产品统计表") ){
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
        	List<Object[]> contractList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		int i = 0;
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[19];
	        		String pcode = "";
	        		String phone = "";
	        		String key = "";
	        		String enpcode = "";
	        		String enphone = "";
	        		List<Object[]> errordata =new ArrayList<Object[]>();
	        		if(objects[4]==null||objects[4].toString().trim().equals("")){
	        			enpcode = "";
	        			enphone = TextUtils.IntToDouble(objects[6].toString());
		        		//校验手机号
		        		errordata  = TextUtils.validPhoneNum(i+4, 7, enphone,true);
		        		if (errordata.size() >0){
		    				return errordata;
		    			}
	        		}else{
	        			pcode = objects[4].toString();
	        			key = pcode.substring(pcode.length()-8);
	        			enpcode = EncryptUtils.encryptToDES(key, pcode);
	        			phone = TextUtils.IntToDouble(objects[6].toString());
	        			errordata  = TextUtils.validPhoneNum(i+4, 7, phone,true);
		        		if (errordata.size() >0){
		    				return errordata;
		    			}
		        		enphone = EncryptUtils.encryptToDES(key, phone);
	        		}
	        		
	        		//检验产品列不为空,且购买产品存在
	        		errordata  = TextUtils.checkEmptyString(i+4, 2, objects[1]);
	    			boolean isExist = false;
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Product product :prs){
    					if(product.getName().equals(objects[1].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+4, 2, objects[1].toString() +",该产品不存在！");
	    				return errordata;
	    			}
		    		
	        		//判断证件类型的写法与数据库保持一致
		    		errordata  = TextUtils.checkEmptyString(i+4, 4, objects[3]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Dictionary dictionary : dics){
    					if(dictionary.getKey().equals(objects[3].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+4, 4, objects[3].toString()+ ", 该证件类型不存在！");
	    				return errordata;
	    			}
		    		
	        		//检验理财师是否存在
	    			errordata  = TextUtils.checkEmptyString(i+4, 16, objects[15]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Planner planner :planners){
    					if(planner.getWorkNum().equals(objects[15].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+4, 16, objects[15].toString()+ ", 该理财师编号不存在！");
	    				return errordata;
	    			}
		    		
	        		//校验日期格式，到账日期不能为空
		    		errordata  = TextUtils.checkDateString(i+4, 13, objects[12],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
	        		//校验金额格式，投资额,年化金额不能为空
	    			errordata  = TextUtils.checkNumber(i+4, 9, objects[8],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			errordata  = TextUtils.checkNumber(i+4, 10, objects[9],false);
	    			if (errordata.size() >0){
	    				return errordata;
	    			} 
	    			
	        		//投资期限必填
	    			errordata  = TextUtils.checkNumber(i+4, 11, objects[10],false);
	    			if (errordata.size() >0){
	    				return errordata; 
	    			}
	    			
	    			//校验分公司
	    			errordata  = TextUtils.checkEmptyString(i+4, 14, objects[13]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			isExist = false;
    				for(Department department :deps){
    					if(department.getTitle().equals(objects[13].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+4, 14, objects[13].toString()+ ", 该分公司不存在！");
	    				return errordata;
	    			}
		    		
	        		temData[0] = phone;												// p_login
	        		temData[1] = DigestUtils.md5Hex(phone);							// p_password
	        		temData[2] = objects[1];										//产品名称 product_id
	        		temData[3] = objects[2];										//基金管理人	
	        		temData[4] = objects[3];										//证件类型
	        		temData[5] = enpcode;											//证件号码
	        		temData[6] = objects[5];										//客户姓名 customer_id
	        		temData[7] = enphone;											//手机号码	
	        		temData[8] = objects[7];										//客户类型
	        		temData[9] = TextUtils.StringtoInteger(objects[8].toString());	//出借金额 amount_rmb
	        		temData[10] = TextUtils.StringtoInteger(objects[9].toString());	//年化金额 annualised
	        		temData[11] = TextUtils.StringtoInteger(objects[10].toString());//出借期限 period
	        		temData[12] = objects[11];										//年化收益率 earning_rate
	        		temData[13] = objects[12];										//到账日期 buy_time
	        	    temData[14] = objects[13];										//分公司
	        		temData[15] = objects[14];										//理财师 planner_id
	        		temData[16] = objects[15];										//工号 work_num	
	        		temData[17] = objects[22];										//备注 memo
	        		temData[18]	= key;
	        		contractList.add(temData);
	        		i++;
        		}
        	}
            return contractList; 
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
    public Contract getContract(Integer id,String period) {
        return contractMapper.selectByPrimaryKey(id, period);
    }

    @Override
    public boolean isNameExists(String name) {
        return false;
    }

}
