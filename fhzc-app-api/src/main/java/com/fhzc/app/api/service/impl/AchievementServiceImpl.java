package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.AchievementService;
import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsDailyMapper;
import com.fhzc.app.dao.mybatis.inter.PlannerAchivementsMonthlyMapper;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDaily;
import com.fhzc.app.dao.mybatis.model.PlannerAchivementsDailyExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        PlannerAchivementsDailyExample example = new PlannerAchivementsDailyExample();
        PlannerAchivementsDailyExample.Criteria criteria = example.createCriteria();
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
        List<PlannerAchivementsDaily> list = plannerAchivementsDailyMapper.selectByExample(example);
        for (PlannerAchivementsDaily plannerAchivementsDaily:list){
            sum = sum + plannerAchivementsDaily.getAnnualised();
        }
        return sum;
    }
}
