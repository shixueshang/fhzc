package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PlannerAchivementsDailyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlannerAchivementsDailyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNull() {
            addCriterion("`transfer_date` is null");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNotNull() {
            addCriterion("`transfer_date` is not null");
            return (Criteria) this;
        }

        public Criteria andTransferDateEqualTo(Date value) {
            addCriterionForJDBCDate("`transfer_date` =", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("`transfer_date` <>", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateGreaterThan(Date value) {
            addCriterionForJDBCDate("`transfer_date` >", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`transfer_date` >=", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateLessThan(Date value) {
            addCriterionForJDBCDate("`transfer_date` <", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`transfer_date` <=", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateIn(List<Date> values) {
            addCriterionForJDBCDate("`transfer_date` in", values, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("`transfer_date` not in", values, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`transfer_date` between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`transfer_date` not between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("`area_id` is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("`area_id` is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("`area_id` =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("`area_id` <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("`area_id` >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`area_id` >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("`area_id` <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("`area_id` <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("`area_id` in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("`area_id` not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("`area_id` between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`area_id` not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andPlannerUidIsNull() {
            addCriterion("`planner_uid` is null");
            return (Criteria) this;
        }

        public Criteria andPlannerUidIsNotNull() {
            addCriterion("`planner_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andPlannerUidEqualTo(Integer value) {
            addCriterion("`planner_uid` =", value, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidNotEqualTo(Integer value) {
            addCriterion("`planner_uid` <>", value, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidGreaterThan(Integer value) {
            addCriterion("`planner_uid` >", value, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`planner_uid` >=", value, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidLessThan(Integer value) {
            addCriterion("`planner_uid` <", value, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidLessThanOrEqualTo(Integer value) {
            addCriterion("`planner_uid` <=", value, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidIn(List<Integer> values) {
            addCriterion("`planner_uid` in", values, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidNotIn(List<Integer> values) {
            addCriterion("`planner_uid` not in", values, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidBetween(Integer value1, Integer value2) {
            addCriterion("`planner_uid` between", value1, value2, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andPlannerUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`planner_uid` not between", value1, value2, "plannerUid");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("`product_id` is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("`product_id` is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("`product_id` =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("`product_id` <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("`product_id` >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`product_id` >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("`product_id` <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("`product_id` <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("`product_id` in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("`product_id` not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("`product_id` between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`product_id` not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andAnnualisedIsNull() {
            addCriterion("`annualised` is null");
            return (Criteria) this;
        }

        public Criteria andAnnualisedIsNotNull() {
            addCriterion("`annualised` is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualisedEqualTo(Integer value) {
            addCriterion("`annualised` =", value, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedNotEqualTo(Integer value) {
            addCriterion("`annualised` <>", value, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedGreaterThan(Integer value) {
            addCriterion("`annualised` >", value, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedGreaterThanOrEqualTo(Integer value) {
            addCriterion("`annualised` >=", value, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedLessThan(Integer value) {
            addCriterion("`annualised` <", value, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedLessThanOrEqualTo(Integer value) {
            addCriterion("`annualised` <=", value, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedIn(List<Integer> values) {
            addCriterion("`annualised` in", values, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedNotIn(List<Integer> values) {
            addCriterion("`annualised` not in", values, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedBetween(Integer value1, Integer value2) {
            addCriterion("`annualised` between", value1, value2, "annualised");
            return (Criteria) this;
        }

        public Criteria andAnnualisedNotBetween(Integer value1, Integer value2) {
            addCriterion("`annualised` not between", value1, value2, "annualised");
            return (Criteria) this;
        }

        public Criteria andContractAmountIsNull() {
            addCriterion("`contract_amount` is null");
            return (Criteria) this;
        }

        public Criteria andContractAmountIsNotNull() {
            addCriterion("`contract_amount` is not null");
            return (Criteria) this;
        }

        public Criteria andContractAmountEqualTo(Integer value) {
            addCriterion("`contract_amount` =", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountNotEqualTo(Integer value) {
            addCriterion("`contract_amount` <>", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountGreaterThan(Integer value) {
            addCriterion("`contract_amount` >", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("`contract_amount` >=", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountLessThan(Integer value) {
            addCriterion("`contract_amount` <", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountLessThanOrEqualTo(Integer value) {
            addCriterion("`contract_amount` <=", value, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountIn(List<Integer> values) {
            addCriterion("`contract_amount` in", values, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountNotIn(List<Integer> values) {
            addCriterion("`contract_amount` not in", values, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountBetween(Integer value1, Integer value2) {
            addCriterion("`contract_amount` between", value1, value2, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andContractAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("`contract_amount` not between", value1, value2, "contractAmount");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("`period` is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("`period` is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Integer value) {
            addCriterion("`period` =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Integer value) {
            addCriterion("`period` <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Integer value) {
            addCriterion("`period` >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("`period` >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Integer value) {
            addCriterion("`period` <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("`period` <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Integer> values) {
            addCriterion("`period` in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Integer> values) {
            addCriterion("`period` not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Integer value1, Integer value2) {
            addCriterion("`period` between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("`period` not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("`product_type` is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("`product_type` is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("`product_type` =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("`product_type` <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("`product_type` >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`product_type` >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("`product_type` <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("`product_type` <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("`product_type` like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("`product_type` not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("`product_type` in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("`product_type` not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("`product_type` between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("`product_type` not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("`memo` is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("`memo` is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("`memo` =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("`memo` <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("`memo` >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("`memo` >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("`memo` <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("`memo` <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("`memo` like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("`memo` not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("`memo` in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("`memo` not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("`memo` between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("`memo` not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("`ctime` is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("`ctime` is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(Date value) {
            addCriterion("`ctime` =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Date value) {
            addCriterion("`ctime` <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Date value) {
            addCriterion("`ctime` >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`ctime` >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Date value) {
            addCriterion("`ctime` <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Date value) {
            addCriterion("`ctime` <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Date> values) {
            addCriterion("`ctime` in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Date> values) {
            addCriterion("`ctime` not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Date value1, Date value2) {
            addCriterion("`ctime` between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Date value1, Date value2) {
            addCriterion("`ctime` not between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("`company` is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("`company` is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(Integer value) {
            addCriterion("`company` =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(Integer value) {
            addCriterion("`company` <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(Integer value) {
            addCriterion("`company` >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(Integer value) {
            addCriterion("`company` >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(Integer value) {
            addCriterion("`company` <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(Integer value) {
            addCriterion("`company` <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<Integer> values) {
            addCriterion("`company` in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<Integer> values) {
            addCriterion("`company` not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(Integer value1, Integer value2) {
            addCriterion("`company` between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(Integer value1, Integer value2) {
            addCriterion("`company` not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andTeamIsNull() {
            addCriterion("`team` is null");
            return (Criteria) this;
        }

        public Criteria andTeamIsNotNull() {
            addCriterion("`team` is not null");
            return (Criteria) this;
        }

        public Criteria andTeamEqualTo(Integer value) {
            addCriterion("`team` =", value, "team");
            return (Criteria) this;
        }

        public Criteria andTeamNotEqualTo(Integer value) {
            addCriterion("`team` <>", value, "team");
            return (Criteria) this;
        }

        public Criteria andTeamGreaterThan(Integer value) {
            addCriterion("`team` >", value, "team");
            return (Criteria) this;
        }

        public Criteria andTeamGreaterThanOrEqualTo(Integer value) {
            addCriterion("`team` >=", value, "team");
            return (Criteria) this;
        }

        public Criteria andTeamLessThan(Integer value) {
            addCriterion("`team` <", value, "team");
            return (Criteria) this;
        }

        public Criteria andTeamLessThanOrEqualTo(Integer value) {
            addCriterion("`team` <=", value, "team");
            return (Criteria) this;
        }

        public Criteria andTeamIn(List<Integer> values) {
            addCriterion("`team` in", values, "team");
            return (Criteria) this;
        }

        public Criteria andTeamNotIn(List<Integer> values) {
            addCriterion("`team` not in", values, "team");
            return (Criteria) this;
        }

        public Criteria andTeamBetween(Integer value1, Integer value2) {
            addCriterion("`team` between", value1, value2, "team");
            return (Criteria) this;
        }

        public Criteria andTeamNotBetween(Integer value1, Integer value2) {
            addCriterion("`team` not between", value1, value2, "team");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("`department_id` is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("`department_id` is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("`department_id` =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("`department_id` <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("`department_id` >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`department_id` >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("`department_id` <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("`department_id` <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("`department_id` in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("`department_id` not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("`department_id` between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`department_id` not between", value1, value2, "departmentId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}