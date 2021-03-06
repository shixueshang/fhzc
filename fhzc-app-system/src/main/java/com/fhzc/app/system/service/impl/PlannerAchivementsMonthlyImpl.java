package com.fhzc.app.system.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsMonthlyMapper;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthlyExample;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.service.PlannerAchivementsMonthlyService;
import com.fhzc.app.system.service.PlannerService;
import com.fhzc.app.system.service.ProductService;

/**
 * @author xiaoqiang 2016-07-22
 *
 */
@Service
public class PlannerAchivementsMonthlyImpl implements PlannerAchivementsMonthlyService {

//	   private static final String IMPORT_SQL = "insert into `planner_achivements_monthly` (`planner_uid`, `planner_percent`, `manager_uid`,`mannager_percent`," 
//		       + "`product_id`, `product_type`,`customer_uid`, `customer_name`,`customer_buy`, `annualised`, `product_cycle`, `transfer_date`, `memo`, `ctime`, `area_id`) "
//		       + " select planner1.uid,?,planner2.uid,?,p.pid,p.product_type,-1,?,?,?,?,?,?,NOW(),null from planner planner1"
//		       + " left join planner planner2 on planner2.work_num=? "
//		       + " left join product p on p.name=?"
//		       + " where  planner1.work_num=?";

	   private static final String IMPORT_SQL = " call sp_add_plannerachivementmonthly(?,?,?,?,?,?,?,?,?,?,?) ";

			    @Resource
			    private ExcelImporter importer;
			    
			    @Resource
			    private ProductService productService;
			    
			    @Resource
			    private PlannerService plannerService;
			    
			    @Resource
			    private PlannerAchivementsMonthlyMapper plannerAchivementsMonthlyMapper;

			/* (non-Javadoc)
			 * @see com.fhzc.app.system.service.PlannerAchivementsDailyService#importDailyExcelFile(org.springframework.web.multipart.MultipartFile)
			 */
			@Override
			public Map<String, Object> importDailyExcelFile(MultipartFile multipartFile) throws Exception {
				int sheetnum =0;
				int rownum =3;
				List<Product> prs = productService.findAllProduct();
				List<Planner> planners =  plannerService.findAllPlanner();
				Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
			        @Override
			        public String validation(Workbook xwb) {
			        	if(xwb.getSheetAt(sheetnum).getRow(0) == null || !TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "业绩统计汇总表") ){
			        		if(xwb.getSheetAt(sheetnum).getRow(0) != null && xwb.getSheetAt(sheetnum).getRow(0).getCell(0) != null){
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
//			    			if(tempData[2] != null && !tempData[2].toString().trim().equals("")){
				    			Object[] newData = new Object[11];
				    			Object[] newData1 = new Object[11];
				    			//判断是否为空，如果为空则不处理
				    			//错误检验处理
				    			//检测产品
				    			List<Object[]> errordata  = TextUtils.checkEmptyString(rownum+i+1, 13, tempData[12]);
				    			boolean isExist = false;
				    			if (errordata.size() >0){
				    				return errordata;
				    			}
				    			
				    			//检测是否存在
				    			isExist = false;
			    				for(Product product :prs){
			    					if(product.getName().equals(tempData[12].toString().trim())){
			    						isExist = true;
			    						break;
			    					}
			    				}
					    		if(!isExist){
				    				errordata = TextUtils.setErrorMessage(rownum+i+1, 13, tempData[12].toString()+ "，该产品不存在！");
				    				return errordata;
				    			}
				    			//检查理财师编号
//				    			errordata  = TextUtils.checkEmptyString(rownum+i+1, 3, tempData[2]);
//				    			if (errordata.size() >0){
//				    				return errordata;
//				    			} 
				    			
				    			//检测理财师编号是否存在
					    		if(!tempData[2].toString().trim().equals("")){
					    			isExist = false;
				    				for(Planner planner :planners){
				    					if(planner.getWorkNum().equals(tempData[2].toString().trim())){
				    						isExist = true;
				    						break;
				    					}
				    				}
						    		if(!isExist){
					    				errordata = TextUtils.setErrorMessage(rownum+i+1, 3, tempData[2].toString()+",该理财师编号不存在！");
					    				return errordata;
					    			}
						    		if(!isExist){
					    				errordata = TextUtils.setErrorMessage(rownum+i+1, 4, tempData[3].toString()+",理财师分单比例不能为空！");
					    				return errordata;
					    			}
					    		}
				    			//检测客户经理编号不为空的时候，是否存在
					    		if(!tempData[6].toString().trim().equals("")){
					    			isExist = false;
				    				for(Planner planner :planners){
				    					if(planner.getWorkNum().equals(tempData[6].toString().trim())){
				    						isExist = true;
				    						break;
				    					}
				    				}
						    		if(!isExist){
					    				errordata = TextUtils.setErrorMessage(rownum+i+1, 7, tempData[6].toString() + "，该客户经理编号不存在！");
					    				return errordata;
					    			}
						    		if(!isExist){
					    				errordata = TextUtils.setErrorMessage(rownum+i+1, 8, tempData[7].toString()+",客户经理分单比例不能为空！");
					    				return errordata;
					    			}
					    		}
					    		
				    			//检查认购金额
				    			errordata  = TextUtils.checkNumber(rownum+i+1, 15, tempData[14],false);
				    			if (errordata.size() >0){
				    				return errordata;
				    			} 	
				    			
				    			//检查期限
				    			errordata  = TextUtils.checkNumber(rownum+i+1, 16, tempData[15],false);
				    			if (errordata.size() >0){
				    				return errordata;
				    			} 
				    			
				    			//检测到账日期
				    			errordata  = TextUtils.checkDateString(rownum+i+1, 17, tempData[16],false);
				    			if (errordata.size() >0){
				    				return errordata;
				    			} 
				    			
				    			if(StringUtils.isNotBlank(tempData[2].toString()) && StringUtils.isNotBlank(tempData[6].toString())){
				    				newData[0] = tempData[2].toString().trim();																				//理财师工号
					    			newData[1] = tempData[3];  																								//理财师分担比例
					    			newData[2] = "";																				//客户经理工号
					    			newData[3] = "";  																								//客户经理分担比例1
					    			newData[4] = tempData[12].toString().trim();																			//产品名称
					    			newData[5] = tempData[13].toString().trim();																			// 购买人id
					    			newData[6] = TextUtils.StringtoInteger(tempData[14].toString());														//购买金额
					    			newData[7] = TextUtils.StringtoInteger(tempData[14].toString())*(Double.parseDouble(tempData[3].toString()))* TextUtils.StringtoInteger(tempData[15].toString())/12;	//业绩
					    			newData[8] = tempData[15];																								//周期
					    			newData[9] = tempData[16];																								//到账日期
					    			newData[10] = tempData[17].toString().trim();
					    			sqldata.add(newData);
					    			newData1[0] = tempData[6].toString().trim();																				//理财师工号
					    			newData1[1] = tempData[7];  																								//理财师分担比例
					    			newData1[2] = "";																					//客户经理工号
					    			newData1[3] = "";	 																								//客户经理分担比例1
					    			newData1[4] = tempData[12].toString().trim();																			//产品名称
					    			newData1[5] = tempData[13].toString().trim();																			// 购买人id
					    			newData1[6] = TextUtils.StringtoInteger(tempData[14].toString());														//购买金额
					    			newData1[7] = TextUtils.StringtoInteger(tempData[14].toString()) * (Double.parseDouble(tempData[7].toString())) * TextUtils.StringtoInteger(tempData[15].toString())/12;	//业绩
					    			newData1[8] = tempData[15];																								//周期
					    			newData1[9] = tempData[16];																								//到账日期
					    			newData1[10] = tempData[17].toString().trim();
					    			sqldata.add(newData1);
				    			}else if(StringUtils.isNotBlank(tempData[2].toString()) && (!StringUtils.isNotBlank(tempData[6].toString()))){
				    				newData[0] = tempData[2].toString().trim();																				//理财师工号
					    			newData[1] = tempData[3];  																								//理财师分担比例
					    			newData[2] = "";																				//客户经理工号
					    			newData[3] = "";  																								//客户经理分担比例1
					    			newData[4] = tempData[12].toString().trim();																			//产品名称
					    			newData[5] = tempData[13].toString().trim();																			// 购买人id
					    			newData[6] = TextUtils.StringtoInteger(tempData[14].toString());														//购买金额
					    			newData[7] = TextUtils.StringtoInteger(tempData[14].toString())*(Double.parseDouble(tempData[3].toString()))* TextUtils.StringtoInteger(tempData[15].toString())/12;	//业绩
					    			newData[8] = tempData[15];																								//周期
					    			newData[9] = tempData[16];																								//到账日期
					    			newData[10] = tempData[17].toString().trim();	
					    			sqldata.add(newData);
				    			}else if((!StringUtils.isNotBlank(tempData[2].toString())) && StringUtils.isNotBlank(tempData[6].toString())){
				    				newData[0] = tempData[6].toString().trim();																				//理财师工号
					    			newData[1] = tempData[7]; 																								//理财师分担比例
					    			newData[2] = "";																										//客户经理工号
					    			newData[3] = "";  																										//客户经理分担比例1
					    			newData[4] = tempData[12].toString().trim();																			//产品名称
					    			newData[5] = tempData[13].toString().trim();																			// 购买人id
					    			newData[6] = TextUtils.StringtoInteger(tempData[14].toString());														//购买金额
					    			newData[7] = TextUtils.StringtoInteger(tempData[14].toString()) * (Double.parseDouble(tempData[7].toString())) * TextUtils.StringtoInteger(tempData[15].toString())/12;	//业绩
					    			newData[8] = tempData[15];																								//周期
					    			newData[9] = tempData[16];																								//到账日期
					    			newData[10] = tempData[17].toString().trim();
					    			sqldata.add(newData);
				    			}
	
			    			}
//			        	}
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
	public PageableResult<PlannerAchivementsMonthly> findPagePlannerAchivementsDaily(int page, int size) {
		PlannerAchivementsMonthlyExample example = new PlannerAchivementsMonthlyExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<PlannerAchivementsMonthly> list = plannerAchivementsMonthlyMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<PlannerAchivementsMonthly>(page, size, list.size(), list);
	}

    @Override
    public List<PlannerAchivementsMonthly> findAchiveMonthlyByArea(List<Integer> areas, String month) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("area", areas);
        param.put("month", month);
        return plannerAchivementsMonthlyMapper.findAchiveMonthlyByArea(param);
    }

    @Override
    public List<PlannerAchivementsMonthly> findAchiveMonthlyByCompany(List<Integer> subCompanys, String month) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("company", subCompanys);
        param.put("month", month);
        return plannerAchivementsMonthlyMapper.findAchiveMonthlyBySub(param);
    }

    @Override
    public List<PlannerAchivementsMonthly> findAchiveMonthlyByTeam(List<Integer> team, String month) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("team", team);
        param.put("month", month);
        return plannerAchivementsMonthlyMapper.findAchiveMonthlyByTeam(param);
    }


    @Override
    public List<PlannerAchivementsMonthly> findAchiveMonthlyByPlanner(List<Integer> planners, String month) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("planners", planners);
        param.put("month", month);
        return plannerAchivementsMonthlyMapper.findAchiveMonthlyByPlanner(param);
    }


}
