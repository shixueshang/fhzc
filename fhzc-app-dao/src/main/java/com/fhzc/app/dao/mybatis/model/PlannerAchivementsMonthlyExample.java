package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PlannerAchivementsMonthlyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlannerAchivementsMonthlyExample() {
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

        public Criteria andPlannerPercentIsNull() {
            addCriterion("`planner_percent` is null");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentIsNotNull() {
            addCriterion("`planner_percent` is not null");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentEqualTo(String value) {
            addCriterion("`planner_percent` =", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentNotEqualTo(String value) {
            addCriterion("`planner_percent` <>", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentGreaterThan(String value) {
            addCriterion("`planner_percent` >", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentGreaterThanOrEqualTo(String value) {
            addCriterion("`planner_percent` >=", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentLessThan(String value) {
            addCriterion("`planner_percent` <", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentLessThanOrEqualTo(String value) {
            addCriterion("`planner_percent` <=", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentLike(String value) {
            addCriterion("`planner_percent` like", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentNotLike(String value) {
            addCriterion("`planner_percent` not like", value, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentIn(List<String> values) {
            addCriterion("`planner_percent` in", values, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentNotIn(List<String> values) {
            addCriterion("`planner_percent` not in", values, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentBetween(String value1, String value2) {
            addCriterion("`planner_percent` between", value1, value2, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andPlannerPercentNotBetween(String value1, String value2) {
            addCriterion("`planner_percent` not between", value1, value2, "plannerPercent");
            return (Criteria) this;
        }

        public Criteria andManagerUidIsNull() {
            addCriterion("`manager_uid` is null");
            return (Criteria) this;
        }

        public Criteria andManagerUidIsNotNull() {
            addCriterion("`manager_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andManagerUidEqualTo(Integer value) {
            addCriterion("`manager_uid` =", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotEqualTo(Integer value) {
            addCriterion("`manager_uid` <>", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidGreaterThan(Integer value) {
            addCriterion("`manager_uid` >", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`manager_uid` >=", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidLessThan(Integer value) {
            addCriterion("`manager_uid` <", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidLessThanOrEqualTo(Integer value) {
            addCriterion("`manager_uid` <=", value, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidIn(List<Integer> values) {
            addCriterion("`manager_uid` in", values, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotIn(List<Integer> values) {
            addCriterion("`manager_uid` not in", values, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidBetween(Integer value1, Integer value2) {
            addCriterion("`manager_uid` between", value1, value2, "managerUid");
            return (Criteria) this;
        }

        public Criteria andManagerUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`manager_uid` not between", value1, value2, "managerUid");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentIsNull() {
            addCriterion("`mannager_percent` is null");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentIsNotNull() {
            addCriterion("`mannager_percent` is not null");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentEqualTo(String value) {
            addCriterion("`mannager_percent` =", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentNotEqualTo(String value) {
            addCriterion("`mannager_percent` <>", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentGreaterThan(String value) {
            addCriterion("`mannager_percent` >", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentGreaterThanOrEqualTo(String value) {
            addCriterion("`mannager_percent` >=", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentLessThan(String value) {
            addCriterion("`mannager_percent` <", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentLessThanOrEqualTo(String value) {
            addCriterion("`mannager_percent` <=", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentLike(String value) {
            addCriterion("`mannager_percent` like", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentNotLike(String value) {
            addCriterion("`mannager_percent` not like", value, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentIn(List<String> values) {
            addCriterion("`mannager_percent` in", values, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentNotIn(List<String> values) {
            addCriterion("`mannager_percent` not in", values, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentBetween(String value1, String value2) {
            addCriterion("`mannager_percent` between", value1, value2, "mannagerPercent");
            return (Criteria) this;
        }

        public Criteria andMannagerPercentNotBetween(String value1, String value2) {
            addCriterion("`mannager_percent` not between", value1, value2, "mannagerPercent");
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

        public Criteria andCustomerUidIsNull() {
            addCriterion("`customer_uid` is null");
            return (Criteria) this;
        }

        public Criteria andCustomerUidIsNotNull() {
            addCriterion("`customer_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerUidEqualTo(Integer value) {
            addCriterion("`customer_uid` =", value, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidNotEqualTo(Integer value) {
            addCriterion("`customer_uid` <>", value, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidGreaterThan(Integer value) {
            addCriterion("`customer_uid` >", value, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`customer_uid` >=", value, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidLessThan(Integer value) {
            addCriterion("`customer_uid` <", value, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidLessThanOrEqualTo(Integer value) {
            addCriterion("`customer_uid` <=", value, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidIn(List<Integer> values) {
            addCriterion("`customer_uid` in", values, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidNotIn(List<Integer> values) {
            addCriterion("`customer_uid` not in", values, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidBetween(Integer value1, Integer value2) {
            addCriterion("`customer_uid` between", value1, value2, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`customer_uid` not between", value1, value2, "customerUid");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyIsNull() {
            addCriterion("`customer_buy` is null");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyIsNotNull() {
            addCriterion("`customer_buy` is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyEqualTo(Integer value) {
            addCriterion("`customer_buy` =", value, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyNotEqualTo(Integer value) {
            addCriterion("`customer_buy` <>", value, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyGreaterThan(Integer value) {
            addCriterion("`customer_buy` >", value, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("`customer_buy` >=", value, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyLessThan(Integer value) {
            addCriterion("`customer_buy` <", value, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyLessThanOrEqualTo(Integer value) {
            addCriterion("`customer_buy` <=", value, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyIn(List<Integer> values) {
            addCriterion("`customer_buy` in", values, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyNotIn(List<Integer> values) {
            addCriterion("`customer_buy` not in", values, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyBetween(Integer value1, Integer value2) {
            addCriterion("`customer_buy` between", value1, value2, "customerBuy");
            return (Criteria) this;
        }

        public Criteria andCustomerBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("`customer_buy` not between", value1, value2, "customerBuy");
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

        public Criteria andProductCycleIsNull() {
            addCriterion("`product_cycle` is null");
            return (Criteria) this;
        }

        public Criteria andProductCycleIsNotNull() {
            addCriterion("`product_cycle` is not null");
            return (Criteria) this;
        }

        public Criteria andProductCycleEqualTo(Integer value) {
            addCriterion("`product_cycle` =", value, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleNotEqualTo(Integer value) {
            addCriterion("`product_cycle` <>", value, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleGreaterThan(Integer value) {
            addCriterion("`product_cycle` >", value, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleGreaterThanOrEqualTo(Integer value) {
            addCriterion("`product_cycle` >=", value, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleLessThan(Integer value) {
            addCriterion("`product_cycle` <", value, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleLessThanOrEqualTo(Integer value) {
            addCriterion("`product_cycle` <=", value, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleIn(List<Integer> values) {
            addCriterion("`product_cycle` in", values, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleNotIn(List<Integer> values) {
            addCriterion("`product_cycle` not in", values, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleBetween(Integer value1, Integer value2) {
            addCriterion("`product_cycle` between", value1, value2, "productCycle");
            return (Criteria) this;
        }

        public Criteria andProductCycleNotBetween(Integer value1, Integer value2) {
            addCriterion("`product_cycle` not between", value1, value2, "productCycle");
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