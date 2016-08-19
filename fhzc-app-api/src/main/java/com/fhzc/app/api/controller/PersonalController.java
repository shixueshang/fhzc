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
import java.util.*;

/**
 * Created by lihongde on 2016/8/15 16:05
 */
@Controller
public class PersonalController extends BaseController {

    @Resource
    private PlannerService plannerService;

    @Resource
    private AchievementService achievementService;

    @Resource
    private AssetsService assetsService;

    @Resource
    private PlannerCustomerService plannerCustomerService;

    @Resource
    private CustomerService customerService;

    @Resource
    private ProductService productService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private RankMonthService rankMonthService;

    @Resource
    private RankYearService rankYearService;

    /**
     * 我的工作
     * @return
     */
    @RequestMapping(value = "/api/achievement", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult achievement(){
        User user = super.getCurrentUser();
        if (!user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"非理财师用户");
        }
        Planner planner = plannerService.getPlannerByUid(user.getUid());
        Map map = new HashMap();

        Calendar cal = Calendar.getInstance();
        Integer currentYear = cal.get(Calendar.YEAR);
        Integer currentMonth = cal.get(Calendar.MONTH)+1;

        //月销售额
        map.put("monthSale", achievementService.getMonthSale(planner.getId(),currentYear,currentMonth));
        //年销售额
        map.put("yearSale", achievementService.getYearSale(planner.getId(),currentYear));

        //月度总排名
        Map<String,String> monthRankList = achievementService.getMonthRankList(currentYear, currentMonth);
        map.put("monthRank", monthRankList);
        map.put("monthMyRank", achievementService.userRankInList(monthRankList,planner.getId()));

        //年度总排名
        Map<String,String> yearRankList = achievementService.getYearRankList(currentYear);
        map.put("yearRank", yearRankList);
        map.put("yearMyRank", achievementService.userRankInList(yearRankList,planner.getId()));


        //取用户的第三级部门
        Department department = departmentService.getDeparent(planner.getDepartmentId());
        Integer deparment_id = department.getParentDeptId();

        if(deparment_id > 0) {
            //月度部门排名
            planner.getDepartmentId();
            Map<String, String> monthDeptRankList = achievementService.getMonthRankList(currentYear, currentMonth, deparment_id);
            map.put("monthDeptRank", monthDeptRankList);
            map.put("monthDeptMyRank", achievementService.userRankInList(monthDeptRankList, planner.getId()));

            //年度部门排名
            Map<String, String> yearDeptRankList = achievementService.getYearRankList(currentYear, deparment_id);
            map.put("yearDeptRank", yearDeptRankList);
            map.put("yearDeptMyRank", achievementService.userRankInList(yearDeptRankList, planner.getId()));
        }else{
            map.put("monthDeptMyRank", 0);
            map.put("yearDeptMyRank", 0);
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, map);
    }

    /**
     * 我的财富
     * @param customer_id
     * @return
     */
    @RequestMapping(value = "/api/personal/assets",method = RequestMethod.GET)
    @ResponseBody
    public  ApiJsonResult personalAssets(Integer customer_id) {
        User user = getCurrentUser();

        //校验是否是登陆理财师的客户请求
        if(user.getLoginRole().equals(Const.USER_ROLE.PLANNER)) {
            Planner planner = plannerService.getPlannerByUid(user.getUid());
            List<PlannerCustomer> plannerCustomers= plannerCustomerService.getPlannerCustomerList(planner.getId());
            if(plannerCustomers == null){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"登陆理财师与请求客户无对应关系");
            }
            boolean isCustomer = false;
            for (PlannerCustomer pl: plannerCustomers){
                if(pl.getCustomerId().equals(customer_id)){
                    isCustomer = true;
                    break;
                }
            }
            if(!isCustomer){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"您不是此客户的理财师");
            }
        }

        if(user.getLoginRole().equals(Const.USER_ROLE.CUSTOMER)) {
            Customer customer = customerService.getCustomerByUid(user.getUid());
            if(!customer.getCustomerId().equals(customer_id)){
                return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"不得其他查看客户资料");
            }
        }

        List<AssetsHistory> assetsHistoryList = assetsService.getHistory(customer_id);
        List<Map> result = new ArrayList<>();
        if(assetsHistoryList == null){
            return new ApiJsonResult(APIConstants.API_JSON_RESULT.BAD_REQUEST,"无资产数据信息");
        }
        for (AssetsHistory asset:assetsHistoryList){
            Map map = new HashMap();
            Product product = productService.getProduct(asset.getProductId());
            if(product == null) continue;

            map.put("productId",asset.getProductId());
            map.put("serial",asset.getSerial());
            map.put("assetType",asset.getType());
            map.put("amount",asset.getAmount());
            map.put("amountUsd",asset.getAmountUsd());
            map.put("amountRMB",asset.getAmountRmb());
            map.put("period",asset.getPeriod());

            map.put("name",product.getName());
            map.put("foundDay",product.getFoundDay());
            map.put("valueDay",product.getValueDay());
            map.put("redeemDay",product.getRedeemDay());
            map.put("dividendDay",product.getDividendDay());
            map.put("annualYield",product.getAnnualYield());//年化收益率
            map.put("buyDay",product.getBuyDay());//开放申购日
            map.put("deadDate",asset.getDeadDate());//截止日期
            map.put("expireDay",asset.getExpireDay());//合同到期日期
            map.put("paymentDate",asset.getPaymentDate());//预期到账时间
            map.put("buyTime",asset.getBuyTime());//开放认购时间


            map.put("notice",product.getNotice());//产品成立公告
            map.put("proveUrl",product.getProveUrl());//备案证明

            result.add(map);
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }


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
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
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
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK,result);
    }
}
