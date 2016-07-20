package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class customerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public customerExample() {
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

        public Criteria andCustomerIdIsNull() {
            addCriterion("`customer_id` is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("`customer_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("`customer_id` =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("`customer_id` <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("`customer_id` >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`customer_id` >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("`customer_id` <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("`customer_id` <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("`customer_id` in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("`customer_id` not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("`customer_id` between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`customer_id` not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("`uid` is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("`uid` is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("`uid` =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("`uid` <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("`uid` >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`uid` >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("`uid` <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("`uid` <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("`uid` in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("`uid` not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("`uid` between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`uid` not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andCbIdIsNull() {
            addCriterion("`cb_id` is null");
            return (Criteria) this;
        }

        public Criteria andCbIdIsNotNull() {
            addCriterion("`cb_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCbIdEqualTo(Integer value) {
            addCriterion("`cb_id` =", value, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdNotEqualTo(Integer value) {
            addCriterion("`cb_id` <>", value, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdGreaterThan(Integer value) {
            addCriterion("`cb_id` >", value, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`cb_id` >=", value, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdLessThan(Integer value) {
            addCriterion("`cb_id` <", value, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdLessThanOrEqualTo(Integer value) {
            addCriterion("`cb_id` <=", value, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdIn(List<Integer> values) {
            addCriterion("`cb_id` in", values, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdNotIn(List<Integer> values) {
            addCriterion("`cb_id` not in", values, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdBetween(Integer value1, Integer value2) {
            addCriterion("`cb_id` between", value1, value2, "cbId");
            return (Criteria) this;
        }

        public Criteria andCbIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`cb_id` not between", value1, value2, "cbId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNull() {
            addCriterion("`level_id` is null");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNotNull() {
            addCriterion("`level_id` is not null");
            return (Criteria) this;
        }

        public Criteria andLevelIdEqualTo(Integer value) {
            addCriterion("`level_id` =", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotEqualTo(Integer value) {
            addCriterion("`level_id` <>", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThan(Integer value) {
            addCriterion("`level_id` >", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`level_id` >=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThan(Integer value) {
            addCriterion("`level_id` <", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("`level_id` <=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIn(List<Integer> values) {
            addCriterion("`level_id` in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotIn(List<Integer> values) {
            addCriterion("`level_id` not in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("`level_id` between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`level_id` not between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andRiskIsNull() {
            addCriterion("`risk` is null");
            return (Criteria) this;
        }

        public Criteria andRiskIsNotNull() {
            addCriterion("`risk` is not null");
            return (Criteria) this;
        }

        public Criteria andRiskEqualTo(String value) {
            addCriterion("`risk` =", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotEqualTo(String value) {
            addCriterion("`risk` <>", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskGreaterThan(String value) {
            addCriterion("`risk` >", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskGreaterThanOrEqualTo(String value) {
            addCriterion("`risk` >=", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLessThan(String value) {
            addCriterion("`risk` <", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLessThanOrEqualTo(String value) {
            addCriterion("`risk` <=", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLike(String value) {
            addCriterion("`risk` like", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotLike(String value) {
            addCriterion("`risk` not like", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskIn(List<String> values) {
            addCriterion("`risk` in", values, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotIn(List<String> values) {
            addCriterion("`risk` not in", values, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskBetween(String value1, String value2) {
            addCriterion("`risk` between", value1, value2, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotBetween(String value1, String value2) {
            addCriterion("`risk` not between", value1, value2, "risk");
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

        public Criteria andBankInfoIdIsNull() {
            addCriterion("`bank_info_id` is null");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdIsNotNull() {
            addCriterion("`bank_info_id` is not null");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdEqualTo(Integer value) {
            addCriterion("`bank_info_id` =", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdNotEqualTo(Integer value) {
            addCriterion("`bank_info_id` <>", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdGreaterThan(Integer value) {
            addCriterion("`bank_info_id` >", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`bank_info_id` >=", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdLessThan(Integer value) {
            addCriterion("`bank_info_id` <", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("`bank_info_id` <=", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdIn(List<Integer> values) {
            addCriterion("`bank_info_id` in", values, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdNotIn(List<Integer> values) {
            addCriterion("`bank_info_id` not in", values, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("`bank_info_id` between", value1, value2, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`bank_info_id` not between", value1, value2, "bankInfoId");
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