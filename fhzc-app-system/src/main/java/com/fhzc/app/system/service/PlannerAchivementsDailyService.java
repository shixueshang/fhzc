package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoqiang on 2016/7/22
 */
public interface PlannerAchivementsDailyService {

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
    PageableResult<PlannerAchivementsDaily> findPagePlannerAchivementsDaily(int page, int size);

    /**
     * 根据区总统计理财师业绩
     * @param areas
     * @return
     */
    List<PlannerAchivementsDaily> findAchiveDailyByArea(List<Integer> areas);

    /**
     * 根据分公司统计理财师业绩
     * @param subCompanys
     * @return
     */
    List<PlannerAchivementsDaily> findAchiveDailyBySub(List<Integer> subCompanys);

    /**
     * 根据团队统计理财师业绩
     * @param team
     * @return
     */
    List<PlannerAchivementsDaily> findAchiveDailyByTeam(List<Integer> team);

    /**
     * 根据理财师统计理财师业绩
     * @param planners
     * @return
     */
    List<PlannerAchivementsDaily> findAchiveDailyByPlanner(List<Integer> planners);

}
