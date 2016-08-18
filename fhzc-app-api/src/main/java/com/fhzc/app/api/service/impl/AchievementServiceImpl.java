package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.AchievementService;
import com.fhzc.app.api.tools.MapUtil;
import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsDailyMapper;
import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsMonthlyMapper;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDailyExample;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthly;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsMonthlyExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by freeman on 16/8/14.
 */
@Service
public class AchievementServiceImpl implements AchievementService{

    @Resource
    PlannerAchivementsMonthlyMapper plannerAchivementsMonthlyMapper;

    @Resource
    PlannerAchivementsDailyMapper plannerAchivementsDailyMapper;

    @Override
    public Integer getMonthSale(Integer plannerId, Integer year, Integer month){
        PlannerAchivementsDailyExample example = new PlannerAchivementsDailyExample();
        PlannerAchivementsDailyExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerUidEqualTo(plannerId);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date start = cal.getTime();

        int maxDaysByDate = getDaysByYearMonth(year, month);
        cal.set(Calendar.DAY_OF_MONTH, maxDaysByDate);
        Date end = cal.getTime();
        Integer sum = 0;
        criteria.andTransferDateBetween(start, end);
        List<PlannerAchivementsDaily> list = plannerAchivementsDailyMapper.selectByExample(example);
        for (PlannerAchivementsDaily plannerAchivementsDaily:list){
            sum = sum + plannerAchivementsDaily.getAnnualised();
        }
        return sum;
    }

    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    @Override
    public Integer getYearSale(Integer plannerId,Integer year) {
        PlannerAchivementsMonthlyExample example = new PlannerAchivementsMonthlyExample();
        PlannerAchivementsMonthlyExample.Criteria criteria = example.createCriteria();
        criteria.andPlannerUidEqualTo(plannerId);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date start = cal.getTime();

        cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        Date end = cal.getTime();
        Integer sum = 0;
        criteria.andTransferDateBetween(start, end);
        List<PlannerAchivementsMonthly> list = plannerAchivementsMonthlyMapper.selectByExample(example);
        for (PlannerAchivementsMonthly plannerAchivementsMonthly:list){
            sum = sum + plannerAchivementsMonthly.getAnnualised();
        }
        return sum;
    }

    @Override
    public Map<String,String> getMonthRankList(Integer year, Integer month) {
        List<PlannerAchivementsDaily> plannerAchivementsDailyList = plannerAchivementsDailyMapper.selectDistinctPlannerUid();
        Map plannerSell = new HashMap();
        for (PlannerAchivementsDaily plannerAchivementsDaily:plannerAchivementsDailyList ) {
            Integer sum = getMonthSale(plannerAchivementsDaily.getPlannerUid(), year, month);
            if (sum.equals(0)) {
                continue;
            }
            plannerSell.put(plannerAchivementsDaily.getPlannerUid().toString(), sum.toString());
        }
        return MapUtil.sortMapByValue(plannerSell);
    }

    @Override
    public Map<String, String> getYearRankList(Integer year) {
        List<PlannerAchivementsMonthly> plannerAchivementsMonthlyList= plannerAchivementsMonthlyMapper.selectDistinctPlannerUid();
        Map plannerSell = new HashMap();
        for (PlannerAchivementsMonthly plannerAchivementsMonthly:plannerAchivementsMonthlyList) {
            Integer sum = getYearSale(plannerAchivementsMonthly.getPlannerUid(), year);
            if (sum.equals(0)) {
                continue;
            }
            plannerSell.put(plannerAchivementsMonthly.getPlannerUid().toString(), sum.toString());
        }
        return MapUtil.sortMapByValue(plannerSell);
    }

    @Override
    public Map<String, String> getMonthRankList(Integer year, Integer month, Integer department_id) {
        List<PlannerAchivementsDaily> plannerAchivementsDailyList = plannerAchivementsDailyMapper.selectDistinctPlannerUidByDeptId(department_id);
        Map plannerSell = new HashMap();
        if(plannerAchivementsDailyList == null){
            return plannerSell;
        }
        for (PlannerAchivementsDaily plannerAchivementsDaily:plannerAchivementsDailyList ) {
            Integer sum = getMonthSale(plannerAchivementsDaily.getPlannerUid(), year, month);
            if (sum.equals(0)) {
                continue;
            }
            plannerSell.put(plannerAchivementsDaily.getPlannerUid().toString(), sum.toString());
        }
        return MapUtil.sortMapByValue(plannerSell);
    }

    @Override
    public Map<String, String> getYearRankList(Integer year, Integer department_id) {
         List<PlannerAchivementsMonthly> plannerAchivementsMonthlyList= plannerAchivementsMonthlyMapper.selectDistinctPlannerUidByDeptId(department_id);
        Map plannerSell = new HashMap();
        if(plannerAchivementsMonthlyList == null){
            return plannerSell;
        }
        for (PlannerAchivementsMonthly plannerAchivementsMonthly:plannerAchivementsMonthlyList) {
            Integer sum = getYearSale(plannerAchivementsMonthly.getPlannerUid(), year);
            if (sum.equals(0)) {
                continue;
            }
            plannerSell.put(plannerAchivementsMonthly.getPlannerUid().toString(), sum.toString());
        }
        return MapUtil.sortMapByValue(plannerSell);
    }

    @Override
    public Integer userRankInList(Map<String, String> map, Integer plannerId) {
        Integer rank = 1;
        if (map.size() == 0) {
            return 0;//0代表无排名
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals(plannerId.toString())) {
                return rank;
            }
            rank++;
        }
        return 0;//0代表无排名
    }
}
