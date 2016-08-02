package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.model.ContractExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.ContractMapper;
import com.fhzc.app.system.service.ContractService;

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

    private static final String IMPORT_SQL = "call sp_insert_financialDaily(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Resource
    private ExcelImporter importer;

    @Resource
    private ContractMapper contractMapper;
    
    
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
        	List<Object[]> contractList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		for (Object[] objects : data) {
	        		Object[] temData = new  Object[17];
	        		temData[0] = TextUtils.IntToDouble(objects[5].toString());	// p_login
	        		temData[1] = TextUtils.IntToDouble(objects[5].toString());	// p_password
	 
	        		temData[2] = objects[1];	//产品名称 product_id
	        		temData[3] = objects[2];	//基金管理人	
	        		temData[4] = "身份证";		//证件类型
	        		temData[5] = objects[3];	//证件号码，客户编号(不一定有)
	        		temData[6] = objects[4];	//客户姓名 customer_id
	        		temData[7] = TextUtils.IntToDouble(objects[5].toString());	//手机号码	
	        		temData[8] = TextUtils.StringtoInteger(objects[6].toString());	//出借金额 amount_rmb
	        		temData[9] = TextUtils.StringtoInteger(objects[7].toString());	//年化金额 annualised
	        		temData[10] = objects[8];	//出借期限 period
	        		temData[11] = objects[9];	//年化收益率 earning_rate
	        		temData[12] = objects[10];	//到账日期 buy_time
	        	    temData[13] = objects[11];	//分公司
	        		temData[14] = objects[12];	//理财师 planner_id
	        		temData[15] = objects[13];	//工号 work_num	
	        		temData[16] = objects[20];	//备注 memo
	        		contractList.add(temData);
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
        }).importExcelFile(multipartFile);

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
