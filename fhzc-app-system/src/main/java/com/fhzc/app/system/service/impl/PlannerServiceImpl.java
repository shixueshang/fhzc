package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.PlannerMapper;
import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.model.PlannerExample;
import com.fhzc.app.system.service.PlannerService;

import org.apache.commons.codec.digest.DigestUtils;
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
	private static final String IMPORT_DEPART_SQL = "call sp_update_department_leader(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//在职理财师
	private static final String IMPORT_SQL = "call sp_insert_planner(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//离职理财师
	private static final String IMPORT_OFF_SQL = "call sp_removeoffer_planner(?)";
	
	@Resource
	private ExcelImporter importer;
	 
	@Resource
    PlannerMapper plannerMapper;

    /**
     * 获得理财师信息
     * @param id
     * @return
     */
    public Planner getPlanner(Integer id) {
        return plannerMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public PageableResult<Planner> findPagePlanners(int page, int size) {
        PlannerExample example = new PlannerExample();
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
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data)  {
        	List<Object[]> plannerList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
	        	for (Object[] objects : data) {
	        		Object[] temData = new  Object[18];
	        		String phone = TextUtils.IntToDouble(objects[4].toString());
	        		temData[0] = objects[1];						//工号 work_num,作为初始登录名
	        		temData[1] = DigestUtils.md5Hex(phone);			//手机号 mobile，作为初始密码	
	        		temData[2] = objects[1];						//工号 work_num
	        		temData[3] = objects[2];						//姓名 realname
					temData[4] = objects[3];						//证件号
					temData[5] = phone;								//手机号
	        		temData[6] = objects[5];						//所属公司 company
	        		temData[7] = objects[6];						//所属城市 area
	        		temData[8] = objects[7];						//一级部门 dept1
	        	    temData[9] = objects[8];						//负责人 dept1_leader	
	        		temData[10] = objects[9];						//二级部门 dept2
	        		temData[11] = objects[10];						//负责人 dept2_leader
	        		temData[12] = objects[11];						//三级部门 dept3
	        		temData[13] = objects[12];						//负责人 dept3_leader
	        		temData[14] = objects[13];						//四级部门 dept14
	        		temData[15] = objects[14];						//负责人 dept4_leader
	        		temData[16] = objects[15];						//岗位名称 job_title_cn
	        		temData[17] = objects[16];						//岗位序列 position
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
        }).importExcelFile(multipartFile,0,2);

        return importResult;
    }
    
    /**
     * 在职理财师导入
     * @param multipartFile
     * @return
     */
    @Override
    public Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception {
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
    		if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(0).getRow(0).getCell(0).toString(), "在职")){
    			return "请查看Excel表头确认导入的报表是否是《在职理财师花名册》！";
         	}else{
         		return null;
         	}
        }

        @Override
        public String getImportSQL() {
        		return IMPORT_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data)  {
        	List<Object[]> plannerList = new ArrayList<Object[]>();
        	if(data.get(0).length>0){
        		int i = 0;
	        	for (Object[] objects : data) {
	        		Object[] temData = new  Object[18];
	        		String phone = TextUtils.IntToDouble(objects[4].toString());
	        		//校验工号不能为空
	        		if(objects[1] == null || objects[1].toString().trim().equals("")){
	        			String errorMessage = "工号不能为空!";
	        			return TextUtils.setErrorMessage(i+3,2,errorMessage);
	        		} 
	        		//校验手机号
	        		List<Object[]> errordata  = TextUtils.validPhoneNum(i+3, 5, phone);
	        		if (errordata.size() >0)
	    			{
	    				return errordata;
	    			}
	        		temData[0] = objects[1];						//工号 work_num,作为初始登录名
	        		temData[1] = DigestUtils.md5Hex(phone);			//手机号 mobile，作为初始密码	
	        		temData[2] = objects[1];						//工号 work_num
	        		temData[3] = objects[2];						//姓名 realname
					temData[4] = objects[3];						//证件号
					temData[5] = phone;								//手机号
	        		temData[6] = objects[5];						//所属公司 company
	        		temData[7] = objects[6];						//所属城市 area
	        		temData[8] = objects[7];						//一级部门 dept1
	        	    temData[9] = objects[8];						//负责人 dept1_leader	
	        		temData[10] = objects[9];						//二级部门 dept2
	        		temData[11] = objects[10];						//负责人 dept2_leader
	        		temData[12] = objects[11];						//三级部门 dept3
	        		temData[13] = objects[12];						//负责人 dept3_leader
	        		temData[14] = objects[13];						//四级部门 dept14
	        		temData[15] = objects[14];						//负责人 dept4_leader
	        		temData[16] = objects[15];						//岗位名称 job_title_cn
	        		temData[17] = objects[16];						//岗位序列 position
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
        }).importExcelFile(multipartFile,0,2);

        return importResult;
    }
    
    /**
     * 离职理财师导入
     * @param multipartFile
     * @return
     */
    @Override
    public Map<String, Object> importExcelFileOff(MultipartFile multipartFile) throws Exception {
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
        	if(!TextUtils.validWorkbookTitle(xwb.getSheetAt(0).getRow(0).getCell(0).toString(), "离职")){
    			return "请查看Excel表头确认导入的报表是否是《离职理财师花名册》！";
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
        	PageableResult<Planner> planners =  findPagePlanners(0, 10000);
        	if(data.get(0).length>0){
        		int i = 0;
	        	for (Object[] objects : data) {
	        		Object[] temData = new  Object[1];
	        		//检查理财师编号
	        		List<Object[]> errordata  = TextUtils.checkEmptyString(i+3, 2, objects[1]);
	        		boolean isExist = false;
	    			if (errordata.size() >0)
	    			{
	    				return errordata;
	    			}
	    			//检测理财师编号是否存在
	    			isExist = false;
    				for(Planner planner :planners.getItems()){
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
        }).importExcelFile(multipartFile);

        return importResult;
    }

	@Override
	public Planner getPlannerByUid(Integer uid) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
		return plannerMapper.selectByExample(example).get(0);
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
    public Planner getPlannerByWorkNum(String workNum) {
        return plannerMapper.selectByWorkNum(workNum);
    }

    @Override
    public List<Planner> findPlannerByDepartment(Integer departmentId) {
        PlannerExample example = new PlannerExample();
        PlannerExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentIdEqualTo(departmentId);
        return plannerMapper.selectByExample(example);
    }
}
