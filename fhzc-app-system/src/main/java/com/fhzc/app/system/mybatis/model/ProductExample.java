package com.fhzc.app.system.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("`pid` is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("`pid` is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("`pid` =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("`pid` <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("`pid` >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`pid` >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("`pid` <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("`pid` <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("`pid` in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("`pid` not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("`pid` between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("`pid` not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("`code` is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("`code` is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("`code` =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("`code` <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("`code` >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("`code` >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("`code` <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("`code` <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("`code` like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("`code` not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("`code` in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("`code` not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("`code` between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("`code` not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andValueDayIsNull() {
            addCriterion("`value_day` is null");
            return (Criteria) this;
        }

        public Criteria andValueDayIsNotNull() {
            addCriterion("`value_day` is not null");
            return (Criteria) this;
        }

        public Criteria andValueDayEqualTo(Integer value) {
            addCriterion("`value_day` =", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayNotEqualTo(Integer value) {
            addCriterion("`value_day` <>", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayGreaterThan(Integer value) {
            addCriterion("`value_day` >", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("`value_day` >=", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayLessThan(Integer value) {
            addCriterion("`value_day` <", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayLessThanOrEqualTo(Integer value) {
            addCriterion("`value_day` <=", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayIn(List<Integer> values) {
            addCriterion("`value_day` in", values, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayNotIn(List<Integer> values) {
            addCriterion("`value_day` not in", values, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayBetween(Integer value1, Integer value2) {
            addCriterion("`value_day` between", value1, value2, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayNotBetween(Integer value1, Integer value2) {
            addCriterion("`value_day` not between", value1, value2, "valueDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayIsNull() {
            addCriterion("`expiry_day` is null");
            return (Criteria) this;
        }

        public Criteria andExpiryDayIsNotNull() {
            addCriterion("`expiry_day` is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryDayEqualTo(Integer value) {
            addCriterion("`expiry_day` =", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayNotEqualTo(Integer value) {
            addCriterion("`expiry_day` <>", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayGreaterThan(Integer value) {
            addCriterion("`expiry_day` >", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("`expiry_day` >=", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayLessThan(Integer value) {
            addCriterion("`expiry_day` <", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayLessThanOrEqualTo(Integer value) {
            addCriterion("`expiry_day` <=", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayIn(List<Integer> values) {
            addCriterion("`expiry_day` in", values, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayNotIn(List<Integer> values) {
            addCriterion("`expiry_day` not in", values, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayBetween(Integer value1, Integer value2) {
            addCriterion("`expiry_day` between", value1, value2, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayNotBetween(Integer value1, Integer value2) {
            addCriterion("`expiry_day` not between", value1, value2, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayIsNull() {
            addCriterion("`dividend_day` is null");
            return (Criteria) this;
        }

        public Criteria andDividendDayIsNotNull() {
            addCriterion("`dividend_day` is not null");
            return (Criteria) this;
        }

        public Criteria andDividendDayEqualTo(Integer value) {
            addCriterion("`dividend_day` =", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayNotEqualTo(Integer value) {
            addCriterion("`dividend_day` <>", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayGreaterThan(Integer value) {
            addCriterion("`dividend_day` >", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("`dividend_day` >=", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayLessThan(Integer value) {
            addCriterion("`dividend_day` <", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayLessThanOrEqualTo(Integer value) {
            addCriterion("`dividend_day` <=", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayIn(List<Integer> values) {
            addCriterion("`dividend_day` in", values, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayNotIn(List<Integer> values) {
            addCriterion("`dividend_day` not in", values, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayBetween(Integer value1, Integer value2) {
            addCriterion("`dividend_day` between", value1, value2, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayNotBetween(Integer value1, Integer value2) {
            addCriterion("`dividend_day` not between", value1, value2, "dividendDay");
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

        public Criteria andCtimeEqualTo(Integer value) {
            addCriterion("`ctime` =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Integer value) {
            addCriterion("`ctime` <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Integer value) {
            addCriterion("`ctime` >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`ctime` >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Integer value) {
            addCriterion("`ctime` <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Integer value) {
            addCriterion("`ctime` <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Integer> values) {
            addCriterion("`ctime` in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Integer> values) {
            addCriterion("`ctime` not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Integer value1, Integer value2) {
            addCriterion("`ctime` between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("`ctime` not between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("`sort` is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("`sort` is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("`sort` =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("`sort` <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("`sort` >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("`sort` >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("`sort` <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("`sort` <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("`sort` in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("`sort` not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("`sort` between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("`sort` not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("`level` is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("`level` is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("`level` =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("`level` <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("`level` >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("`level` >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("`level` <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("`level` <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("`level` in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("`level` not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("`level` between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("`level` not between", value1, value2, "level");
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

        public Criteria andRiskEqualTo(Integer value) {
            addCriterion("`risk` =", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotEqualTo(Integer value) {
            addCriterion("`risk` <>", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskGreaterThan(Integer value) {
            addCriterion("`risk` >", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskGreaterThanOrEqualTo(Integer value) {
            addCriterion("`risk` >=", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLessThan(Integer value) {
            addCriterion("`risk` <", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskLessThanOrEqualTo(Integer value) {
            addCriterion("`risk` <=", value, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskIn(List<Integer> values) {
            addCriterion("`risk` in", values, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotIn(List<Integer> values) {
            addCriterion("`risk` not in", values, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskBetween(Integer value1, Integer value2) {
            addCriterion("`risk` between", value1, value2, "risk");
            return (Criteria) this;
        }

        public Criteria andRiskNotBetween(Integer value1, Integer value2) {
            addCriterion("`risk` not between", value1, value2, "risk");
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