package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by freeman on 16/8/19.
 */
@Controller
public class RankApiController extends BaseController {

    @Resource
    private PlannerService plannerService;

    @Resource
    private AchievementService achievementService;

    @Resource
    private RankMonthService rankMonthService;

    @Resource
    private RankYearService rankYearService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/api/init/rank/month", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult initRank(@RequestParam(value = "year") Integer year) {
        Integer result = 0;
        for(int month=1;month<=12;month++) {
            List<PlannerAchivementsMonthly> plannerAchivementsMonthlyList = achievementService.getPlanners();
            if (plannerAchivementsMonthlyList == null) {
                continue;
            }
            for (PlannerAchivementsMonthly plannerAchivementsMonthly : plannerAchivementsMonthlyList) {
                Integer plannerId = plannerAchivementsMonthly.getPlannerUid();
                Integer departmentId = plannerAchivementsMonthly.getDepartmentId();
                Integer sale = achievementService.getMonthSale(plannerId, year, month);
                if(sale > 0) {
                    RankMonth rank = new RankMonth();
                    rank.setAnnualised(sale);
                    rank.setDepartmentId(departmentId);
                    rank.setPlannerId(plannerId);

                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month - 1);
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    Date date = cal.getTime();

                    rank.setYearMonth(date);
                    rankMonthService.addOrUpdate(rank);
                }
            }
        }

        List<Date> dateList = rankMonthService.getExistYearMonth();
        for (Date date : dateList) {
            //全公司排名计算
            List<RankMonth> rankList = rankMonthService.getYearMonthRankList(date);

            for (RankMonth rank1:rankList) {
                Integer plannerId = rank1.getPlannerId();
                Integer i = 0;

                for (RankMonth rank : rankList) {
                    if (rank.getAnnualised().equals(0)) {
                        continue;
                    }
                    if (rank.getPlannerId().equals(plannerId)) {
                        RankMonth rankMonth = new RankMonth();
                        rankMonth.setId(rank.getId());
                        rankMonth.setPlannerId(plannerId);
                        rankMonth.setRank(i+1);
                        rankMonth.setYearMonth(date);
                        rankMonth.setAnnualised(rank.getAnnualised());
                        rankMonth.setDepartmentId(rank.getDepartmentId());
                        rankMonthService.addOrUpdate(rankMonth);
                    }
                    i++;
                }
            }

            //部门排名计算
            List<Integer> departmentList = rankMonthService.getExistDepartment();
            for (Integer department : departmentList) {
                List<RankMonth> rankList1 = rankMonthService.getYearMonthRankList(date,department);

                for (RankMonth rank1:rankList1) {
                    Integer plannerId = rank1.getPlannerId();
                    Integer i = 0;

                    for (RankMonth rank : rankList1) {
                        if (rank.getAnnualised().equals(0)) {
                            continue;
                        }
                        if (rank.getPlannerId().equals(plannerId)) {
                            RankMonth rankMonth = rankMonthService.getByPlannerIdYearMonth(plannerId, date);
                            rankMonth.setDepartmentRank(i+1);
                            rankMonthService.addOrUpdate(rankMonth);
                        }
                        i++;
                    }
                }
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }


    @RequestMapping(value = "/api/init/rank/year", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult initRankYear() {
        Integer result = 0;
        for (Integer year = 2016; year < 2067; year++) {
            List<PlannerAchivementsMonthly> plannerAchivementsMonthlyList = achievementService.getPlanners();
            if(plannerAchivementsMonthlyList == null){
                continue;
            }
            for (PlannerAchivementsMonthly plannerAchivementsMonthly : plannerAchivementsMonthlyList) {
                Integer plannerId = plannerAchivementsMonthly.getPlannerUid();
                Integer departmentId = plannerAchivementsMonthly.getDepartmentId();
                Integer sale = achievementService.getYearSale(plannerId, year);
                if(sale > 0) {
                    RankYear rank = new RankYear();
                    rank.setAnnualised(sale);
                    rank.setDepartmentId(departmentId);
                    rank.setPlannerId(plannerId);
                    rank.setYear(year);
                    rankYearService.addOrUpdate(rank);
                }
            }
        }

        List<Integer> yearList = rankYearService.getExistYear();
        for (Integer year : yearList) {
            //全公司排名计算
            List<RankYear> rankList = rankYearService.getYearRankList(year);

            for (RankYear rank1 : rankList) {
                Integer plannerId = rank1.getPlannerId();
                Integer i = 0;

                for (RankYear rank : rankList) {
                    if (rank.getAnnualised().equals(0)) {
                        continue;
                    }
                    if (rank.getPlannerId().equals(plannerId)) {
                        RankYear rankYear = new RankYear();
                        rankYear.setId(rank.getId());
                        rankYear.setPlannerId(plannerId);
                        rankYear.setRank(i + 1);
                        rankYear.setYear(year);
                        rankYear.setAnnualised(rank.getAnnualised());
                        rankYear.setDepartmentId(rank.getDepartmentId());
                        rankYearService.addOrUpdate(rankYear);
                    }
                    i++;
                }
            }

            //部门排名计算
            List<Integer> departmentList = rankMonthService.getExistDepartment();
            for (Integer department : departmentList) {
                List<RankYear> rankList1 = rankYearService.getYearRankList(year, department);

                for (RankYear rank1 : rankList1) {
                    Integer plannerId = rank1.getPlannerId();
                    Integer i = 0;

                    for (RankYear rank : rankList1) {
                        if (rank.getAnnualised().equals(0)) {
                            continue;
                        }
                        if (rank.getPlannerId().equals(plannerId)) {
                            RankYear rankYear = rankYearService.getByPlannerIdYear(plannerId, year);
                            rankYear.setDepartmentRank(i + 1);
                            rankYearService.addOrUpdate(rankYear);
                        }
                        i++;
                    }
                }
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }

    /**
     * 理财师历年排名(全公司、部门)
     * @return
     */
    @RequestMapping(value = "/api/rank/year", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rankYear() {
        User user = super.getCurrentUser();
        if (!user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND, "非理财师用户");
        }
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        List<RankYear> result = rankYearService.getPlannerRankList(planner.getId());

        if (result == null) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND);
        } else {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
        }
    }


    /**
     * 理财师历月排名(全公司、部门)
     * @return
     */
    @RequestMapping(value = "/api/rank/month", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rankMonth() {
        User user = super.getCurrentUser();
        if (!user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
           return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND, "非理财师用户");
        }
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        List<RankMonth> result = rankMonthService.getPlannerRankList(planner.getId());
        if (result == null) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND);
        } else {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
        }
    }

    /**
     * 理财师年度前十
     * @param year
     * @return
     */
    @RequestMapping(value = "/api/rank/ten", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rankTen(@RequestParam (value = "year") Integer year) {
        List<RankYear> yearList = rankYearService.getFirstTenByYear(year);
        List<Map> result = new ArrayList<>();
        if(yearList == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND,"rank_year表无数据,请先初始化数据!");
        }
        for (RankYear record : yearList) {
            Map map = new HashMap();
            Planner planner = plannerService.getPlanner(record.getPlannerId());
            User user = userService.getUser(planner.getUid());
            map.put("name", user.getRealname());
            map.put("annualised", record.getAnnualised());
            map.put("rank", record.getRank());
            result.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }

    @RequestMapping(value = "/api/rank/query", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult rankQuery(@RequestParam(value = "year") Integer year) {
        User user = super.getCurrentUser();
        if (!user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND, "非理财师用户");
        }
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date start= cal.getTime();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        Date end = cal.getTime();

        List<RankMonth> result = rankMonthService.getPlannerRankListByYear(planner.getId(),start,end);

        if (result == null) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.NOT_FOUND);
        } else {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
        }
    }

    @RequestMapping(value = "/api/rank/query/between", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult personalScoreDetail( @RequestParam String start,
                                             @RequestParam String end) {
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sbf.parse(start);
            endDate = sbf.parse(end);
        } catch (ParseException e) {

        }
        List<PlannerAchivementsMonthly> result = rankMonthService.getSortByDate(startDate, endDate);
        List<Map> sortList = new ArrayList<>();
        Integer sort = 1;
        for (PlannerAchivementsMonthly plannerAchivementsMonthly: result) {
            Map map = new HashMap();
            map.put("sort",sort++);
            map.put("annualised", plannerAchivementsMonthly.getAnnualised());
            map.put("plannerUid", plannerAchivementsMonthly.getPlannerUid());
            Planner planner = plannerService.getPlanner(plannerAchivementsMonthly.getPlannerUid());
            User user = userService.getUser(planner.getUid());
            map.put("avatar", user.getAvatar());
            map.put("name", user.getRealname());
            sortList.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,sortList);
    }
}
