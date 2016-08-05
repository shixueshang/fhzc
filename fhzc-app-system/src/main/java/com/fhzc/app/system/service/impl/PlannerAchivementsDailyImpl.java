/**
 * 
 */
package com.fhzc.app.system.service.impl;

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
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDailyExample;
import com.fhzc.app.system.service.PlannerAchivementsDailyService;

import org.apache.commons.lang3.StringUtils;
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
	
	   private static final String IMPORT_SQL = "insert into `planner_achivements_daily` (`transfer_date`, `area_id`, `planner_uid`," 
       + "`product_id`, `annualised`, `contract_amount`,`expire_date`, `product_type`, `memo`, `ctime`) "
       + " select ?,area_id,uid,pid,?,?,?,?,?,NOW() from planner left join areas on area_name=? left join product on product.name=? where work_num=? ";


	    @Resource
	    private ExcelImporter importer;

	    @Resource
	    private PlannerAchivementsDailyMapper plannerAchivementsDailyMapper;

	/* (non-Javadoc)
	 * @see com.fhzc.app.system.service.PlannerAchivementsDailyService#importDailyExcelFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Map<String, Object> importDailyExcelFile(MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
	    Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
	        @Override
	        public String validation(Workbook xwb) {
	        	if(xwb.getSheetAt(1).getRow(0).getCell(0) == null ){
	        		return "报表错误！";
	        	}
	        	else{
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
		    			Object[] newData = new Object[9];
		    			
		    			Object[] error_Data = new Object[2];
		    			//错误检验处理
		    			if (tempData[5] == null || tempData[5].toString().trim().equals("") )
		    			{
		    				List<Object[]> errordata  = new LinkedList<Object[]>();
		    				errordata.add(new Object[]{"error","第" + String.valueOf(i+1) + "行第"+ String.valueOf(6) +"列产品信息为空!"});
		    				return errordata;

		    			}
		    			
		    			newData[0] = tempData[0];		//业绩日期
		    			String strtmp = tempData[6].toString();
		    			newData[1] = TextUtils.Stringto10kInteger(strtmp);		//年化业绩
		    			
		    			strtmp = tempData[7].toString();
		    			newData[2] = TextUtils.Stringto10kInteger(strtmp);		//合同金额
		    			
		    			//失效日期
		    			if(tempData[8] != null && !tempData[8].toString().trim().equals("")){
		    				newData[3] = tempData[8];	
		    			}
		    			else{
		    				newData[3] = null;
		    			}
		    			newData[4] = tempData[9];		//产品类型
		    			newData[5] = tempData[10];		//备注
		    			newData[6] = tempData[1];		//地区
		    			newData[7] = tempData[5];		//产品名称
		    			newData[8] = tempData[3];		//理财师工号
		    			
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
	        }).importExcelFile(multipartFile,1,2);

	        return importResult;
	}

	/* (non-Javadoc)
	 * @see com.fhzc.app.system.service.PlannerAchivementsDailyService#findPagePlannerAchivementsDaily(int, int)
	 */
	@Override
	public PageableResult<PlannerAchivementsDaily> findPagePlannerAchivementsDaily(int page, int size) {
		// TODO Auto-generated method stub
		PlannerAchivementsDailyExample example = new PlannerAchivementsDailyExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<PlannerAchivementsDaily> list = plannerAchivementsDailyMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<PlannerAchivementsDaily>(page, size, list.size(), list);
	}

}
