package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.PlannerMapper;
import com.fhzc.app.dao.mybatis.model.Areas;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerExample;
import com.fhzc.app.system.service.AreasService;
import com.fhzc.app.system.service.PlannerService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by menghq on 2016/7/26.
 * 
 */
@Service
public class PlannerServiceImpl implements PlannerService {
	
	//导入在职时先生成department
	private static final String IMPORT_DEPART_SQL = "call sp_update_department_leader(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//在职理财师
	private static final String IMPORT_SQL = "call sp_insert_planner(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//离职理财师
	private static final String IMPORT_OFF_SQL = "call sp_removeoffer_planner(?)";
	
	@Resource
	private ExcelImporter importer;
	 
	@Resource
    PlannerMapper plannerMapper;
	
	@Resource
    AreasService areasService;

    /**
     * 获得理财师信息
     * @param id
     * @return
     */
    public Planner getPlanner(Integer id) {
        return plannerMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public PageableResult<Planner> findPagePlanners(List<Integer> departments, int page, int size) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdIn(departments);
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Planner> list = plannerMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Planner>(page, size, plannerMapper.countByExample(example), list);
    }

    @Override
    public void addOrUpdatePlanner(Planner planner) {
        Integer id = planner.getId();
        if(id == null){
            plannerMapper.insertSelective(planner);
        }else{
        	plannerMapper.updateByPrimaryKeySelective(planner);
        }
    }

    /**
     * department导入
     * @param multipartFile
     * @return
     */
    @Override
    public Map<String, Object> importDepartmentExcelFile(MultipartFile multipartFile) throws Exception {
        int sheetnum = 0;
        int rownum = 2;
    	Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	return null;
        }

        @Override
        public String getImportSQL() {
        		return IMPORT_DEPART_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception  {
        	List<Object[]> plannerList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
	        	for (Object[] objects : data) {
	        		Object[] temData = new  Object[19];
	        		String phone = TextUtils.IntToDouble(objects[15].toString());
	        		String pcode = objects[16].toString();
	        		String key = pcode.substring(pcode.length()-8);
	        		temData[0] = objects[1];								//工号 work_num,作为初始登录名
	        		temData[1] = DigestUtils.md5Hex(phone);					//手机号 mobile，作为初始密码	
	        		temData[2] = objects[1];								//工号 work_num
	        		temData[3] = objects[2];								//姓名 realname
	        		temData[4] = EncryptUtils.encryptToDES(key, pcode);		//证件号
    				temData[5] = EncryptUtils.encryptToDES(key, phone);		//手机号
	        		temData[6] = objects[3];								//所属公司 company
	        		temData[7] = objects[4];								//所属城市 area
	        		temData[8] = objects[5];								//一级部门 dept1
	        	    temData[9] = objects[6];								//负责人 dept1_leader	
	        		temData[10] = objects[7];								//二级部门 dept2
	        		temData[11] = objects[8];								//负责人 dept2_leader
	        		temData[12] = objects[9];								//三级部门 dept3
	        		temData[13] = objects[10];								//负责人 dept3_leader
	        		temData[14] = objects[11];								//四级部门 dept14
	        		temData[15] = objects[12];								//负责人 dept4_leader
	        		temData[16] = objects[13];								//岗位名称 job_title_cn
	        		temData[17] = objects[14];								//岗位序列 position
	        		temData[18] = key;
	        		plannerList.add(temData);
				}
        	}
            return plannerList;
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
    
    /**
     * 在职理财师导入
     * @param multipartFile
     * @return
     */
    @Override
    public Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception {
    	int sheetnum =0;
		int rownum =2;
		List<Areas> as = areasService.getAllAreas();
    	Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(xwb.getSheetAt(sheetnum).getRow(0) == null || !TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "在职") ){
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
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) throws Exception  {
        	List<Object[]> plannerList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		int i = 0;
	        	for (Object[] objects : data) {
	        		Object[] temData = new  Object[19];
	        		//校验工号不能为空
	        		List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 2, objects[1]);
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			String pcode = "";
	        		String phone = "";
	        		String key = "";
	        		String enpcode = "";
	        		String enphone = "";
	        		if(objects[16]==null||objects[16].toString().trim().equals("")){
	        			enpcode = "";
	        			enphone = TextUtils.IntToDouble(objects[15].toString());
		        		//校验手机号
		        		errordata  = TextUtils.validPhoneNum(i+3, 16, enphone,false);
		        		if (errordata.size() >0){
		    				return errordata;
		    			}
	        		}else{
	        			pcode = objects[16].toString();
	        			key = pcode.substring(pcode.length()-8);
	        			enpcode = EncryptUtils.encryptToDES(key, pcode);
	        			phone = TextUtils.IntToDouble(objects[15].toString());
	        			errordata  = TextUtils.validPhoneNum(i+3, 16, phone,false);
		        		if (errordata.size() >0){
		    				return errordata;
		    			}
		        		enphone = EncryptUtils.encryptToDES(key, phone);
	        		}
	        		
	        		//判断所属城市是否存在
	    			boolean isExist = false;
	    			for(Areas areas : as){
    					if(areas.getAreaName().equals(objects[4].toString().trim())){
    						isExist = true;
    						break;
    					}
    				}
    				
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 5, objects[4].toString()+ "，该城市区域尚未建立公司！");
	    				return errordata;
	    			}
	        		temData[0] = objects[1];						//工号 work_num,作为初始登录名
	        		temData[1] = DigestUtils.md5Hex(phone);			//手机号 mobile，作为初始密码	
	        		temData[2] = objects[1];						//工号 work_num
	        		temData[3] = objects[2];						//姓名 realname
	        		temData[4] = enpcode;							//证件号
	        		temData[5] = enphone;							//手机号
	        		temData[6] = objects[3];						//所属公司 company
	        		temData[7] = objects[4];						//所属城市 area
	        		temData[8] = objects[5];						//一级部门 dept1
	        	    temData[9] = objects[6];						//负责人 dept1_leader	
	        		temData[10] = objects[7];						//二级部门 dept2
	        		temData[11] = objects[8];						//负责人 dept2_leader
	        		temData[12] = objects[9];						//三级部门 dept3
	        		temData[13] = objects[10];						//负责人 dept3_leader
	        		temData[14] = objects[11];						//四级部门 dept14
	        		temData[15] = objects[12];						//负责人 dept4_leader
	        		temData[16] = objects[13];						//岗位名称 job_title_cn
	        		temData[17] = objects[14];						//岗位序列 position
	        		temData[18] = key;	
	        		plannerList.add(temData);
	        		i++;
				}
        	}
            return plannerList;
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
    
    /**
     * 离职理财师导入
     * @param multipartFile
     * @return
     */
    @Override
    public Map<String, Object> importExcelFileOff(MultipartFile multipartFile) throws Exception {
    	int sheetnum = 0;
    	int rownum = 2;
    	Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(xwb.getSheetAt(sheetnum).getRow(0) == null || !TextUtils.validWorkbookTitle(xwb.getSheetAt(sheetnum).getRow(0).getCell(0).toString(), "离职") ){
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
        		return IMPORT_OFF_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {
        	List<Object[]> plannerList = new ArrayList<Object[]>();
        	List<Planner> planners = findAllPlanner();
        	if(data.get(0).length>0){
        		int i = 0;
	        	for (Object[] objects : data) {
	        		Object[] temData = new  Object[1];
	        		//检查理财师编号
	        		List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 2, objects[1]);
	        		boolean isExist = false;
	    			if (errordata.size() >0){
	    				return errordata;
	    			}
	    			//检测理财师编号是否存在
	    			isExist = false;
    				for(Planner planner :planners){
    					if(planner.getWorkNum().equals(objects[1].toString())){
    						isExist = true;
    						break;
    					}
    				}
		    		if(!isExist){
	    				errordata = TextUtils.setErrorMessage(i+3, 2, " 理财师编号"+objects[1].toString()+"不存在！");
	    				return errordata;
	    			}
	        		temData[0] = objects[1];		//工号 work_num
	        		plannerList.add(temData);
	        		i++;
				}
        	}
            return plannerList;
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
	public Planner getPlannerByUid(Integer uid) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        try {
        	return plannerMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	//在职离职理财师一起导入--未使用
	@Override
	public Map<String,Map<String, Object>> importExcel(MultipartFile multipartFile) throws Exception {
		 Map<String, Object> importResultOn = new HashMap<String, Object>();
		 Map<String, Object> importResultOff = new HashMap<String, Object>();
		 Map<String,Map<String, Object>> map = new HashMap<String,Map<String, Object>>();
		 importResultOn = importExcelFile(multipartFile);
		 importResultOff = importExcelFileOff(multipartFile);
		 map.put("on", importResultOn);
		 map.put("off", importResultOff);
		 return map;
	}

    @Override
    public Planner getPlannerByWorkNum(String workNum, String status) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andWorkNumEqualTo(workNum);
        if(status != null){
        	criteria.andStatusEqualTo(status);
        }
        if(plannerMapper.countByExample(example) > 0){
            return plannerMapper.selectByExample(example).get(0);
        }
        return null;
    }

    @Override
    public List<Planner> findPlannerByDepartment(Integer departmentId) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId);
        criteria.andStatusEqualTo(Const.PLANNER_STATUS.ON);
        return plannerMapper.selectByExample(example);
    }

    @Override
    public List<Integer> findPlannerByDepartment(List<Integer> depts, String status) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdIn(depts);
        if(StringUtils.isNotBlank(status) && (status != null)){
        	criteria.andStatusEqualTo(status);
        }
        List<Planner> planners = plannerMapper.selectByExample(example);
        List<Integer> plannerIds = new ArrayList<Integer>();
        for(Planner planner : planners){
            plannerIds.add(planner.getId());
        }
        return plannerIds;
    }
    
    /**
     * 获取所有理财师
     */
    @Override
    public List<Planner> findAllPlanner() {
        PlannerExample example = new PlannerExample();
//        PlannerExample.Criteria criteria = example.createCriteria();
//        criteria.andStatusEqualTo(Const.PLANNER_STATUS.ON);
        return plannerMapper.selectByExample(example);
    }
}
