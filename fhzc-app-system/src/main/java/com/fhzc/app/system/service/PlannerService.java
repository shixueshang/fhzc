package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Planner;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/15.
 */
public interface PlannerService {
    
	/**
     * 获得理财师信息
     * @param id
     * @return
     */
    Planner getPlanner(Integer id);
   
    /**
     * 查询理财师列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Planner> findPagePlanners(int page, int size);

    /**
     * 添加理财师
     * @param planner
     */
    void addOrUpdatePlanner(Planner planner);

    /**
     * department导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importDepartmentExcelFile(MultipartFile multipartFile) throws Exception;
    /**
     * 在职理财师导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;

    /**
     * 离职理财师导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFileOff(MultipartFile multipartFile) throws Exception;
    
    /**
     * 在职离职理财师一步导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String,Map<String, Object>> importExcel(MultipartFile multipartFile) throws Exception;
    
    /**
     * 获得理财师信息
     * @param uid
     * @return
     */
    Planner getPlannerByUid(Integer uid);

    /**
     * 获得理财师信息
     * @param workNum
     * @return
     */
    Planner getPlannerByWorkNum(String workNum);

    /**
     * 查询某团队的理财师
     * @param departmentId
     * @return
     */
    List<Planner> findPlannerByDepartment(Integer departmentId);

    /**
     * 根据部门查询理财师
     * @param depts
     * @return
     */
    List<Integer> findPlannerByDepartment(List<Integer> depts);
}
