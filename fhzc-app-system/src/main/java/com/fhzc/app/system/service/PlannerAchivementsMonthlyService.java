/**
 * 
 */
package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoqiang on 2016/7/22
 */
public interface PlannerAchivementsMonthlyService {

	
	   /**
     * 理财师业绩日报导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importDailyExcelFile(MultipartFile multipartFile) throws Exception;

    /**
     * 查询业绩日报列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<PlannerAchivementsMonthly> findPagePlannerAchivementsDaily(int page, int size);

    /**
     * @param planners
     * @param month
     * @return
     */
    List<PlannerAchivementsMonthly> findAchivementsMonthly(List<Integer> planners, String month);

}
