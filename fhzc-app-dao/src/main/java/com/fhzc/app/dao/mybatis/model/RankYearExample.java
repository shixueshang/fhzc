package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class RankYearExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RankYearExample() {
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

        public Criteria andPlannerIdIsNull() {
            addCriterion("`planner_id` is null");
            return (Criteria) this;
        }

        public Criteria andPlannerIdIsNotNull() {
            addCriterion("`planner_id` is not null");
            return (Criteria) this;
        }

        public Criteria andPlannerIdEqualTo(Integer value) {
            addCriterion("`planner_id` =", value, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdNotEqualTo(Integer value) {
            addCriterion("`planner_id` <>", value, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdGreaterThan(Integer value) {
            addCriterion("`planner_id` >", value, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`planner_id` >=", value, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdLessThan(Integer value) {
            addCriterion("`planner_id` <", value, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdLessThanOrEqualTo(Integer value) {
            addCriterion("`planner_id` <=", value, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdIn(List<Integer> values) {
            addCriterion("`planner_id` in", values, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdNotIn(List<Integer> values) {
            addCriterion("`planner_id` not in", values, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdBetween(Integer value1, Integer value2) {
            addCriterion("`planner_id` between", value1, value2, "plannerId");
            return (Criteria) this;
        }

        public Criteria andPlannerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`planner_id` not between", value1, value2, "plannerId");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("`year` is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("`year` is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("`year` =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("`year` <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("`year` >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("`year` >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("`year` <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("`year` <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("`year` in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("`year` not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("`year` between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("`year` not between", value1, value2, "year");
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

        public Criteria andRankIsNull() {
            addCriterion("`rank` is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("`rank` is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(Integer value) {
            addCriterion("`rank` =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(Integer value) {
            addCriterion("`rank` <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(Integer value) {
            addCriterion("`rank` >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("`rank` >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(Integer value) {
            addCriterion("`rank` <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(Integer value) {
            addCriterion("`rank` <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<Integer> values) {
            addCriterion("`rank` in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<Integer> values) {
            addCriterion("`rank` not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(Integer value1, Integer value2) {
            addCriterion("`rank` between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(Integer value1, Integer value2) {
            addCriterion("`rank` not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankIsNull() {
            addCriterion("`department_rank` is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankIsNotNull() {
            addCriterion("`department_rank` is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankEqualTo(Integer value) {
            addCriterion("`department_rank` =", value, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankNotEqualTo(Integer value) {
            addCriterion("`department_rank` <>", value, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankGreaterThan(Integer value) {
            addCriterion("`department_rank` >", value, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("`department_rank` >=", value, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankLessThan(Integer value) {
            addCriterion("`department_rank` <", value, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankLessThanOrEqualTo(Integer value) {
            addCriterion("`department_rank` <=", value, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankIn(List<Integer> values) {
            addCriterion("`department_rank` in", values, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankNotIn(List<Integer> values) {
            addCriterion("`department_rank` not in", values, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankBetween(Integer value1, Integer value2) {
            addCriterion("`department_rank` between", value1, value2, "departmentRank");
            return (Criteria) this;
        }

        public Criteria andDepartmentRankNotBetween(Integer value1, Integer value2) {
            addCriterion("`department_rank` not between", value1, value2, "departmentRank");
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