/**
 * 
 */
package com.fhzc.app.system.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsDailyMapper;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDailyExample;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.system.service.PlannerAchivementsDailyService;
import com.fhzc.app.system.service.PlannerService;
import com.fhzc.app.system.service.ProductService;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiaoqiang 2016-07-22
 *
 */
@Service
public class PlannerAchivementsDailyImpl implements PlannerAchivementsDailyService {
	
	   private static final String IMPORT_SQL = " call sp_add_plannerachivementdaily(?,?,?,?,?,?,?,?,?,?,?)";


	    @Resource
	    private ExcelImporter importer;
	    
	    @Resource
	    private ProductService productService;
	    
	    @Resource
	    private PlannerService plannerService;

	    @Resource
	    private PlannerAchivementsDailyMapper plannerAchivementsDailyMapper;

	/* (non-Javadoc)
	 * @see com.fhzc.app.system.service.PlannerAchivementsDailyService#importDailyExcelFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Map<String, Object> importDailyExcelFile(MultipartFile multipartFile) throws Exception {
		int sheetnum =1;
		int rownum =2;
		List<Product> prs = productService.findAllProduct();
		List<Planner> planners =  plannerService.findAllPlanner();
	    Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
	        @Override
	        public String validation(Workbook xwb) {
	        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "业绩统计报表") ){
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
	        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {
	        	List<Object[]> sqldata  = new LinkedList<Object[]>();
	        	for (int i = 0, length = data.size(); i < length; i++) {
	    			Object[] tempData = data.get(i);
	    			//判断是否为空，如果为空则不处理
	    			if(tempData[0] != null && !tempData[0].toString().trim().equals("")){
		    			Object[] newData = new Object[11];
		    			//错误检验处理
		    			//检测产品
		    			List<Object[]> errordata  = TextUtils.checkEmptyString(rownum+i+1, 6, tempData[5]);
		    			boolean isExist = false;
		    			if (errordata.size() >0){
		    				return errordata;
		    			}
		    			
		    			//检测是否存在
		    			isExist = false;
	    				for(Product product :prs){
	    					if(product.getName().equals(tempData[5].toString().trim())){
	    						isExist = true;
	    						break;
	    					}
	    				}
			    		if(!isExist){
		    				errordata = TextUtils.setErrorMessage(rownum+i+1, 6, tempData[5].toString() +",该产品不存在！");
		    				return errordata;
		    			}
			    		
		    			//检查理财师编号
		    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 4, tempData[3]);
		    			if (errordata.size() >0){
		    				return errordata;
		    			}
		    			
		    			//检测理财师编号是否存在
		    			isExist = false;
	    				for(Planner planner :planners){
	    					if(planner.getWorkNum().equals(tempData[3].toString().trim())){
	    						isExist = true;
	    						break;
	    					}
	    				}
			    		if(!isExist){
		    				errordata = TextUtils.setErrorMessage(rownum+i+1, 4, tempData[3].toString()+ ", 该理财师编号不存在！");
		    				return errordata;
		    			}
			    		
		    			//检测日期
		    			errordata  = TextUtils.checkDateString(rownum+i+1, 1, tempData[0],false);
		    			if (errordata.size() >0){
		    				return errordata;
		    			} 
		    			
		    			//检查年化业绩
		    			errordata  = TextUtils.checkNumber(rownum+i+1, 7, tempData[6],false);
		    			if (errordata.size() >0){
		    				return errordata;
		    			} 
		    			
		    			//检查合同金额
		    			errordata  = TextUtils.checkNumber(rownum+i+1, 8, tempData[7],true);
		    			if (errordata.size() >0){
		    				return errordata;
		    			} 	
		    			
		    			//检查期限
		    			errordata  = TextUtils.checkNumber(rownum+i+1, 9, tempData[8],true);
		    			if (errordata.size() >0){
		    				return errordata;
		    			} 
		    			
		    			newData[0] = tempData[0];								//业绩日期
		    			newData[1] = tempData[1].toString().trim();				//地区
		    			newData[2] = tempData[2].toString().trim();				//理财师姓名
		    			newData[3] = tempData[3].toString().trim();				//理财师工号
		    			newData[4] = tempData[4].toString().trim();				//所属市场总监
		    			newData[5] = tempData[5].toString().trim();				//产品名称
		    			String strtmp = tempData[6].toString();
		    			newData[6] = TextUtils.Stringto10kInteger(strtmp);		//年化业绩
		    			strtmp = tempData[7].toString();
		    			newData[7] = TextUtils.Stringto10kInteger(strtmp);		//合同金额
		    			strtmp = tempData[8].toString();
		    			newData[8] = TextUtils.StringtoInteger(strtmp);			//合同金额	
		    			newData[9] = tempData[9].toString().trim();				//产品类型
		    			newData[10] = tempData[10].toString().trim();			//备注
		    			sqldata.add(newData);
	    			}
	        	}
	       		return sqldata;
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

	/* (non-Javadoc)
	 * @see com.fhzc.app.system.service.PlannerAchivementsDailyService#findPagePlannerAchivementsDaily(int, int)
	 */
	@Override
	public PageableResult<PlannerAchivementsDaily> findPagePlannerAchivementsDaily(int page, int size) {
		PlannerAchivementsDailyExample example = new PlannerAchivementsDailyExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<PlannerAchivementsDaily> list = plannerAchivementsDailyMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<PlannerAchivementsDaily>(page, size, list.size(), list);
	}

    @Override
    public List<PlannerAchivementsDaily> findAchiveDailyBySub(List<Integer> subCompanys) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("company", subCompanys);
        return plannerAchivementsDailyMapper.findAchiveDailyBySub(param);
    }

    @Override
    public List<PlannerAchivementsDaily> findAchiveDailyByTeam(List<Integer> team) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("team", team);
        return plannerAchivementsDailyMapper.findAchiveDailyByTeam(param);
    }

    @Override
    public List<PlannerAchivementsDaily> findAchiveDailyByPlanner(List<Integer> planners) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("planners", planners);
        return plannerAchivementsDailyMapper.findAchiveDailyByPlanner(param);
    }

}
