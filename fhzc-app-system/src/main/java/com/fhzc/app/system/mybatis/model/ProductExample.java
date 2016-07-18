package com.fhzc.app.system.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("`status` not between", value1, value2, "status");
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

        public Criteria andFoundDayIsNull() {
            addCriterion("`found_day` is null");
            return (Criteria) this;
        }

        public Criteria andFoundDayIsNotNull() {
            addCriterion("`found_day` is not null");
            return (Criteria) this;
        }

        public Criteria andFoundDayEqualTo(Date value) {
            addCriterionForJDBCDate("`found_day` =", value, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`found_day` <>", value, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`found_day` >", value, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`found_day` >=", value, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayLessThan(Date value) {
            addCriterionForJDBCDate("`found_day` <", value, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`found_day` <=", value, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayIn(List<Date> values) {
            addCriterionForJDBCDate("`found_day` in", values, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`found_day` not in", values, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`found_day` between", value1, value2, "foundDay");
            return (Criteria) this;
        }

        public Criteria andFoundDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`found_day` not between", value1, value2, "foundDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayIsNull() {
            addCriterion("`buy_day` is null");
            return (Criteria) this;
        }

        public Criteria andBuyDayIsNotNull() {
            addCriterion("`buy_day` is not null");
            return (Criteria) this;
        }

        public Criteria andBuyDayEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_day` =", value, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_day` <>", value, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`buy_day` >", value, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_day` >=", value, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayLessThan(Date value) {
            addCriterionForJDBCDate("`buy_day` <", value, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_day` <=", value, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayIn(List<Date> values) {
            addCriterionForJDBCDate("`buy_day` in", values, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`buy_day` not in", values, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`buy_day` between", value1, value2, "buyDay");
            return (Criteria) this;
        }

        public Criteria andBuyDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`buy_day` not between", value1, value2, "buyDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayIsNull() {
            addCriterion("`redeem_day` is null");
            return (Criteria) this;
        }

        public Criteria andRedeemDayIsNotNull() {
            addCriterion("`redeem_day` is not null");
            return (Criteria) this;
        }

        public Criteria andRedeemDayEqualTo(Date value) {
            addCriterionForJDBCDate("`redeem_day` =", value, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`redeem_day` <>", value, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`redeem_day` >", value, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`redeem_day` >=", value, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayLessThan(Date value) {
            addCriterionForJDBCDate("`redeem_day` <", value, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`redeem_day` <=", value, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayIn(List<Date> values) {
            addCriterionForJDBCDate("`redeem_day` in", values, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`redeem_day` not in", values, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`redeem_day` between", value1, value2, "redeemDay");
            return (Criteria) this;
        }

        public Criteria andRedeemDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`redeem_day` not between", value1, value2, "redeemDay");
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

        public Criteria andValueDayEqualTo(Date value) {
            addCriterionForJDBCDate("`value_day` =", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`value_day` <>", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`value_day` >", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`value_day` >=", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayLessThan(Date value) {
            addCriterionForJDBCDate("`value_day` <", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`value_day` <=", value, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayIn(List<Date> values) {
            addCriterionForJDBCDate("`value_day` in", values, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`value_day` not in", values, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`value_day` between", value1, value2, "valueDay");
            return (Criteria) this;
        }

        public Criteria andValueDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`value_day` not between", value1, value2, "valueDay");
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

        public Criteria andExpiryDayEqualTo(Date value) {
            addCriterionForJDBCDate("`expiry_day` =", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`expiry_day` <>", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`expiry_day` >", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`expiry_day` >=", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayLessThan(Date value) {
            addCriterionForJDBCDate("`expiry_day` <", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`expiry_day` <=", value, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayIn(List<Date> values) {
            addCriterionForJDBCDate("`expiry_day` in", values, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`expiry_day` not in", values, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`expiry_day` between", value1, value2, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andExpiryDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`expiry_day` not between", value1, value2, "expiryDay");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIsNull() {
            addCriterion("`issue_type` is null");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIsNotNull() {
            addCriterion("`issue_type` is not null");
            return (Criteria) this;
        }

        public Criteria andIssueTypeEqualTo(Date value) {
            addCriterionForJDBCDate("`issue_type` =", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotEqualTo(Date value) {
            addCriterionForJDBCDate("`issue_type` <>", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeGreaterThan(Date value) {
            addCriterionForJDBCDate("`issue_type` >", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`issue_type` >=", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeLessThan(Date value) {
            addCriterionForJDBCDate("`issue_type` <", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`issue_type` <=", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIn(List<Date> values) {
            addCriterionForJDBCDate("`issue_type` in", values, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotIn(List<Date> values) {
            addCriterionForJDBCDate("`issue_type` not in", values, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`issue_type` between", value1, value2, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`issue_type` not between", value1, value2, "issueType");
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

        public Criteria andRenewDeadlineIsNull() {
            addCriterion("`renew_deadline` is null");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineIsNotNull() {
            addCriterion("`renew_deadline` is not null");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineEqualTo(String value) {
            addCriterion("`renew_deadline` =", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineNotEqualTo(String value) {
            addCriterion("`renew_deadline` <>", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineGreaterThan(String value) {
            addCriterion("`renew_deadline` >", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineGreaterThanOrEqualTo(String value) {
            addCriterion("`renew_deadline` >=", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineLessThan(String value) {
            addCriterion("`renew_deadline` <", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineLessThanOrEqualTo(String value) {
            addCriterion("`renew_deadline` <=", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineLike(String value) {
            addCriterion("`renew_deadline` like", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineNotLike(String value) {
            addCriterion("`renew_deadline` not like", value, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineIn(List<String> values) {
            addCriterion("`renew_deadline` in", values, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineNotIn(List<String> values) {
            addCriterion("`renew_deadline` not in", values, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineBetween(String value1, String value2) {
            addCriterion("`renew_deadline` between", value1, value2, "renewDeadline");
            return (Criteria) this;
        }

        public Criteria andRenewDeadlineNotBetween(String value1, String value2) {
            addCriterion("`renew_deadline` not between", value1, value2, "renewDeadline");
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

        public Criteria andDividendDayEqualTo(Date value) {
            addCriterionForJDBCDate("`dividend_day` =", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`dividend_day` <>", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`dividend_day` >", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`dividend_day` >=", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayLessThan(Date value) {
            addCriterionForJDBCDate("`dividend_day` <", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`dividend_day` <=", value, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayIn(List<Date> values) {
            addCriterionForJDBCDate("`dividend_day` in", values, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`dividend_day` not in", values, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`dividend_day` between", value1, value2, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andDividendDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`dividend_day` not between", value1, value2, "dividendDay");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldIsNull() {
            addCriterion("`annual_yield` is null");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldIsNotNull() {
            addCriterion("`annual_yield` is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldEqualTo(String value) {
            addCriterion("`annual_yield` =", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotEqualTo(String value) {
            addCriterion("`annual_yield` <>", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldGreaterThan(String value) {
            addCriterion("`annual_yield` >", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldGreaterThanOrEqualTo(String value) {
            addCriterion("`annual_yield` >=", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldLessThan(String value) {
            addCriterion("`annual_yield` <", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldLessThanOrEqualTo(String value) {
            addCriterion("`annual_yield` <=", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldLike(String value) {
            addCriterion("`annual_yield` like", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotLike(String value) {
            addCriterion("`annual_yield` not like", value, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldIn(List<String> values) {
            addCriterion("`annual_yield` in", values, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotIn(List<String> values) {
            addCriterion("`annual_yield` not in", values, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldBetween(String value1, String value2) {
            addCriterion("`annual_yield` between", value1, value2, "annualYield");
            return (Criteria) this;
        }

        public Criteria andAnnualYieldNotBetween(String value1, String value2) {
            addCriterion("`annual_yield` not between", value1, value2, "annualYield");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeIsNull() {
            addCriterion("`income_distribution_type` is null");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeIsNotNull() {
            addCriterion("`income_distribution_type` is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeEqualTo(String value) {
            addCriterion("`income_distribution_type` =", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeNotEqualTo(String value) {
            addCriterion("`income_distribution_type` <>", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeGreaterThan(String value) {
            addCriterion("`income_distribution_type` >", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`income_distribution_type` >=", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeLessThan(String value) {
            addCriterion("`income_distribution_type` <", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeLessThanOrEqualTo(String value) {
            addCriterion("`income_distribution_type` <=", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeLike(String value) {
            addCriterion("`income_distribution_type` like", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeNotLike(String value) {
            addCriterion("`income_distribution_type` not like", value, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeIn(List<String> values) {
            addCriterion("`income_distribution_type` in", values, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeNotIn(List<String> values) {
            addCriterion("`income_distribution_type` not in", values, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeBetween(String value1, String value2) {
            addCriterion("`income_distribution_type` between", value1, value2, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andIncomeDistributionTypeNotBetween(String value1, String value2) {
            addCriterion("`income_distribution_type` not between", value1, value2, "incomeDistributionType");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("`credit` is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("`credit` is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(String value) {
            addCriterion("`credit` =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(String value) {
            addCriterion("`credit` <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(String value) {
            addCriterion("`credit` >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(String value) {
            addCriterion("`credit` >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(String value) {
            addCriterion("`credit` <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(String value) {
            addCriterion("`credit` <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLike(String value) {
            addCriterion("`credit` like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotLike(String value) {
            addCriterion("`credit` not like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<String> values) {
            addCriterion("`credit` in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<String> values) {
            addCriterion("`credit` not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(String value1, String value2) {
            addCriterion("`credit` between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(String value1, String value2) {
            addCriterion("`credit` not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationIsNull() {
            addCriterion("`investment_orientation` is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationIsNotNull() {
            addCriterion("`investment_orientation` is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationEqualTo(String value) {
            addCriterion("`investment_orientation` =", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationNotEqualTo(String value) {
            addCriterion("`investment_orientation` <>", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationGreaterThan(String value) {
            addCriterion("`investment_orientation` >", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationGreaterThanOrEqualTo(String value) {
            addCriterion("`investment_orientation` >=", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationLessThan(String value) {
            addCriterion("`investment_orientation` <", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationLessThanOrEqualTo(String value) {
            addCriterion("`investment_orientation` <=", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationLike(String value) {
            addCriterion("`investment_orientation` like", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationNotLike(String value) {
            addCriterion("`investment_orientation` not like", value, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationIn(List<String> values) {
            addCriterion("`investment_orientation` in", values, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationNotIn(List<String> values) {
            addCriterion("`investment_orientation` not in", values, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationBetween(String value1, String value2) {
            addCriterion("`investment_orientation` between", value1, value2, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andInvestmentOrientationNotBetween(String value1, String value2) {
            addCriterion("`investment_orientation` not between", value1, value2, "investmentOrientation");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustIsNull() {
            addCriterion("`increase_trust` is null");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustIsNotNull() {
            addCriterion("`increase_trust` is not null");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustEqualTo(String value) {
            addCriterion("`increase_trust` =", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustNotEqualTo(String value) {
            addCriterion("`increase_trust` <>", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustGreaterThan(String value) {
            addCriterion("`increase_trust` >", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustGreaterThanOrEqualTo(String value) {
            addCriterion("`increase_trust` >=", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustLessThan(String value) {
            addCriterion("`increase_trust` <", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustLessThanOrEqualTo(String value) {
            addCriterion("`increase_trust` <=", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustLike(String value) {
            addCriterion("`increase_trust` like", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustNotLike(String value) {
            addCriterion("`increase_trust` not like", value, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustIn(List<String> values) {
            addCriterion("`increase_trust` in", values, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustNotIn(List<String> values) {
            addCriterion("`increase_trust` not in", values, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustBetween(String value1, String value2) {
            addCriterion("`increase_trust` between", value1, value2, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustNotBetween(String value1, String value2) {
            addCriterion("`increase_trust` not between", value1, value2, "increaseTrust");
            return (Criteria) this;
        }

        public Criteria andHighlightsIsNull() {
            addCriterion("`highlights` is null");
            return (Criteria) this;
        }

        public Criteria andHighlightsIsNotNull() {
            addCriterion("`highlights` is not null");
            return (Criteria) this;
        }

        public Criteria andHighlightsEqualTo(String value) {
            addCriterion("`highlights` =", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotEqualTo(String value) {
            addCriterion("`highlights` <>", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsGreaterThan(String value) {
            addCriterion("`highlights` >", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsGreaterThanOrEqualTo(String value) {
            addCriterion("`highlights` >=", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsLessThan(String value) {
            addCriterion("`highlights` <", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsLessThanOrEqualTo(String value) {
            addCriterion("`highlights` <=", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsLike(String value) {
            addCriterion("`highlights` like", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotLike(String value) {
            addCriterion("`highlights` not like", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsIn(List<String> values) {
            addCriterion("`highlights` in", values, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotIn(List<String> values) {
            addCriterion("`highlights` not in", values, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsBetween(String value1, String value2) {
            addCriterion("`highlights` between", value1, value2, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotBetween(String value1, String value2) {
            addCriterion("`highlights` not between", value1, value2, "highlights");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeIsNull() {
            addCriterion("`fund_management_fee` is null");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeIsNotNull() {
            addCriterion("`fund_management_fee` is not null");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeEqualTo(String value) {
            addCriterion("`fund_management_fee` =", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeNotEqualTo(String value) {
            addCriterion("`fund_management_fee` <>", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeGreaterThan(String value) {
            addCriterion("`fund_management_fee` >", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeGreaterThanOrEqualTo(String value) {
            addCriterion("`fund_management_fee` >=", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeLessThan(String value) {
            addCriterion("`fund_management_fee` <", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeLessThanOrEqualTo(String value) {
            addCriterion("`fund_management_fee` <=", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeLike(String value) {
            addCriterion("`fund_management_fee` like", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeNotLike(String value) {
            addCriterion("`fund_management_fee` not like", value, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeIn(List<String> values) {
            addCriterion("`fund_management_fee` in", values, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeNotIn(List<String> values) {
            addCriterion("`fund_management_fee` not in", values, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeBetween(String value1, String value2) {
            addCriterion("`fund_management_fee` between", value1, value2, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundManagementFeeNotBetween(String value1, String value2) {
            addCriterion("`fund_management_fee` not between", value1, value2, "fundManagementFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeIsNull() {
            addCriterion("`fund_subscription_fee` is null");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeIsNotNull() {
            addCriterion("`fund_subscription_fee` is not null");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeEqualTo(String value) {
            addCriterion("`fund_subscription_fee` =", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeNotEqualTo(String value) {
            addCriterion("`fund_subscription_fee` <>", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeGreaterThan(String value) {
            addCriterion("`fund_subscription_fee` >", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeGreaterThanOrEqualTo(String value) {
            addCriterion("`fund_subscription_fee` >=", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeLessThan(String value) {
            addCriterion("`fund_subscription_fee` <", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeLessThanOrEqualTo(String value) {
            addCriterion("`fund_subscription_fee` <=", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeLike(String value) {
            addCriterion("`fund_subscription_fee` like", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeNotLike(String value) {
            addCriterion("`fund_subscription_fee` not like", value, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeIn(List<String> values) {
            addCriterion("`fund_subscription_fee` in", values, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeNotIn(List<String> values) {
            addCriterion("`fund_subscription_fee` not in", values, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeBetween(String value1, String value2) {
            addCriterion("`fund_subscription_fee` between", value1, value2, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundSubscriptionFeeNotBetween(String value1, String value2) {
            addCriterion("`fund_subscription_fee` not between", value1, value2, "fundSubscriptionFee");
            return (Criteria) this;
        }

        public Criteria andFundManagerIsNull() {
            addCriterion("`fund_manager` is null");
            return (Criteria) this;
        }

        public Criteria andFundManagerIsNotNull() {
            addCriterion("`fund_manager` is not null");
            return (Criteria) this;
        }

        public Criteria andFundManagerEqualTo(String value) {
            addCriterion("`fund_manager` =", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerNotEqualTo(String value) {
            addCriterion("`fund_manager` <>", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerGreaterThan(String value) {
            addCriterion("`fund_manager` >", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerGreaterThanOrEqualTo(String value) {
            addCriterion("`fund_manager` >=", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerLessThan(String value) {
            addCriterion("`fund_manager` <", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerLessThanOrEqualTo(String value) {
            addCriterion("`fund_manager` <=", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerLike(String value) {
            addCriterion("`fund_manager` like", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerNotLike(String value) {
            addCriterion("`fund_manager` not like", value, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerIn(List<String> values) {
            addCriterion("`fund_manager` in", values, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerNotIn(List<String> values) {
            addCriterion("`fund_manager` not in", values, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerBetween(String value1, String value2) {
            addCriterion("`fund_manager` between", value1, value2, "fundManager");
            return (Criteria) this;
        }

        public Criteria andFundManagerNotBetween(String value1, String value2) {
            addCriterion("`fund_manager` not between", value1, value2, "fundManager");
            return (Criteria) this;
        }

        public Criteria andCustodianIsNull() {
            addCriterion("`custodian` is null");
            return (Criteria) this;
        }

        public Criteria andCustodianIsNotNull() {
            addCriterion("`custodian` is not null");
            return (Criteria) this;
        }

        public Criteria andCustodianEqualTo(String value) {
            addCriterion("`custodian` =", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianNotEqualTo(String value) {
            addCriterion("`custodian` <>", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianGreaterThan(String value) {
            addCriterion("`custodian` >", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianGreaterThanOrEqualTo(String value) {
            addCriterion("`custodian` >=", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianLessThan(String value) {
            addCriterion("`custodian` <", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianLessThanOrEqualTo(String value) {
            addCriterion("`custodian` <=", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianLike(String value) {
            addCriterion("`custodian` like", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianNotLike(String value) {
            addCriterion("`custodian` not like", value, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianIn(List<String> values) {
            addCriterion("`custodian` in", values, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianNotIn(List<String> values) {
            addCriterion("`custodian` not in", values, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianBetween(String value1, String value2) {
            addCriterion("`custodian` between", value1, value2, "custodian");
            return (Criteria) this;
        }

        public Criteria andCustodianNotBetween(String value1, String value2) {
            addCriterion("`custodian` not between", value1, value2, "custodian");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeIsNull() {
            addCriterion("`investment_type` is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeIsNotNull() {
            addCriterion("`investment_type` is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeEqualTo(String value) {
            addCriterion("`investment_type` =", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeNotEqualTo(String value) {
            addCriterion("`investment_type` <>", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeGreaterThan(String value) {
            addCriterion("`investment_type` >", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`investment_type` >=", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeLessThan(String value) {
            addCriterion("`investment_type` <", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeLessThanOrEqualTo(String value) {
            addCriterion("`investment_type` <=", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeLike(String value) {
            addCriterion("`investment_type` like", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeNotLike(String value) {
            addCriterion("`investment_type` not like", value, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeIn(List<String> values) {
            addCriterion("`investment_type` in", values, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeNotIn(List<String> values) {
            addCriterion("`investment_type` not in", values, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeBetween(String value1, String value2) {
            addCriterion("`investment_type` between", value1, value2, "investmentType");
            return (Criteria) this;
        }

        public Criteria andInvestmentTypeNotBetween(String value1, String value2) {
            addCriterion("`investment_type` not between", value1, value2, "investmentType");
            return (Criteria) this;
        }

        public Criteria andProjectSourceIsNull() {
            addCriterion("`project_source` is null");
            return (Criteria) this;
        }

        public Criteria andProjectSourceIsNotNull() {
            addCriterion("`project_source` is not null");
            return (Criteria) this;
        }

        public Criteria andProjectSourceEqualTo(String value) {
            addCriterion("`project_source` =", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceNotEqualTo(String value) {
            addCriterion("`project_source` <>", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceGreaterThan(String value) {
            addCriterion("`project_source` >", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceGreaterThanOrEqualTo(String value) {
            addCriterion("`project_source` >=", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceLessThan(String value) {
            addCriterion("`project_source` <", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceLessThanOrEqualTo(String value) {
            addCriterion("`project_source` <=", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceLike(String value) {
            addCriterion("`project_source` like", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceNotLike(String value) {
            addCriterion("`project_source` not like", value, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceIn(List<String> values) {
            addCriterion("`project_source` in", values, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceNotIn(List<String> values) {
            addCriterion("`project_source` not in", values, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceBetween(String value1, String value2) {
            addCriterion("`project_source` between", value1, value2, "projectSource");
            return (Criteria) this;
        }

        public Criteria andProjectSourceNotBetween(String value1, String value2) {
            addCriterion("`project_source` not between", value1, value2, "projectSource");
            return (Criteria) this;
        }

        public Criteria andIssuerIsNull() {
            addCriterion("`issuer` is null");
            return (Criteria) this;
        }

        public Criteria andIssuerIsNotNull() {
            addCriterion("`issuer` is not null");
            return (Criteria) this;
        }

        public Criteria andIssuerEqualTo(String value) {
            addCriterion("`issuer` =", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotEqualTo(String value) {
            addCriterion("`issuer` <>", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerGreaterThan(String value) {
            addCriterion("`issuer` >", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerGreaterThanOrEqualTo(String value) {
            addCriterion("`issuer` >=", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerLessThan(String value) {
            addCriterion("`issuer` <", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerLessThanOrEqualTo(String value) {
            addCriterion("`issuer` <=", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerLike(String value) {
            addCriterion("`issuer` like", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotLike(String value) {
            addCriterion("`issuer` not like", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerIn(List<String> values) {
            addCriterion("`issuer` in", values, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotIn(List<String> values) {
            addCriterion("`issuer` not in", values, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerBetween(String value1, String value2) {
            addCriterion("`issuer` between", value1, value2, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotBetween(String value1, String value2) {
            addCriterion("`issuer` not between", value1, value2, "issuer");
            return (Criteria) this;
        }

        public Criteria andIsRecordIsNull() {
            addCriterion("`is_record` is null");
            return (Criteria) this;
        }

        public Criteria andIsRecordIsNotNull() {
            addCriterion("`is_record` is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecordEqualTo(String value) {
            addCriterion("`is_record` =", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotEqualTo(String value) {
            addCriterion("`is_record` <>", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordGreaterThan(String value) {
            addCriterion("`is_record` >", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordGreaterThanOrEqualTo(String value) {
            addCriterion("`is_record` >=", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordLessThan(String value) {
            addCriterion("`is_record` <", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordLessThanOrEqualTo(String value) {
            addCriterion("`is_record` <=", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordLike(String value) {
            addCriterion("`is_record` like", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotLike(String value) {
            addCriterion("`is_record` not like", value, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordIn(List<String> values) {
            addCriterion("`is_record` in", values, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotIn(List<String> values) {
            addCriterion("`is_record` not in", values, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordBetween(String value1, String value2) {
            addCriterion("`is_record` between", value1, value2, "isRecord");
            return (Criteria) this;
        }

        public Criteria andIsRecordNotBetween(String value1, String value2) {
            addCriterion("`is_record` not between", value1, value2, "isRecord");
            return (Criteria) this;
        }

        public Criteria andProveUrlIsNull() {
            addCriterion("`prove_url` is null");
            return (Criteria) this;
        }

        public Criteria andProveUrlIsNotNull() {
            addCriterion("`prove_url` is not null");
            return (Criteria) this;
        }

        public Criteria andProveUrlEqualTo(String value) {
            addCriterion("`prove_url` =", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlNotEqualTo(String value) {
            addCriterion("`prove_url` <>", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlGreaterThan(String value) {
            addCriterion("`prove_url` >", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`prove_url` >=", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlLessThan(String value) {
            addCriterion("`prove_url` <", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlLessThanOrEqualTo(String value) {
            addCriterion("`prove_url` <=", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlLike(String value) {
            addCriterion("`prove_url` like", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlNotLike(String value) {
            addCriterion("`prove_url` not like", value, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlIn(List<String> values) {
            addCriterion("`prove_url` in", values, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlNotIn(List<String> values) {
            addCriterion("`prove_url` not in", values, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlBetween(String value1, String value2) {
            addCriterion("`prove_url` between", value1, value2, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andProveUrlNotBetween(String value1, String value2) {
            addCriterion("`prove_url` not between", value1, value2, "proveUrl");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderIsNull() {
            addCriterion("`display_order` is null");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderIsNotNull() {
            addCriterion("`display_order` is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderEqualTo(Integer value) {
            addCriterion("`display_order` =", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderNotEqualTo(Integer value) {
            addCriterion("`display_order` <>", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderGreaterThan(Integer value) {
            addCriterion("`display_order` >", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("`display_order` >=", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderLessThan(Integer value) {
            addCriterion("`display_order` <", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderLessThanOrEqualTo(Integer value) {
            addCriterion("`display_order` <=", value, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderIn(List<Integer> values) {
            addCriterion("`display_order` in", values, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderNotIn(List<Integer> values) {
            addCriterion("`display_order` not in", values, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderBetween(Integer value1, Integer value2) {
            addCriterion("`display_order` between", value1, value2, "displayOrder");
            return (Criteria) this;
        }

        public Criteria andDisplayOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("`display_order` not between", value1, value2, "displayOrder");
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

        public Criteria andCoverIsNull() {
            addCriterion("`cover` is null");
            return (Criteria) this;
        }

        public Criteria andCoverIsNotNull() {
            addCriterion("`cover` is not null");
            return (Criteria) this;
        }

        public Criteria andCoverEqualTo(String value) {
            addCriterion("`cover` =", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotEqualTo(String value) {
            addCriterion("`cover` <>", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverGreaterThan(String value) {
            addCriterion("`cover` >", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverGreaterThanOrEqualTo(String value) {
            addCriterion("`cover` >=", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLessThan(String value) {
            addCriterion("`cover` <", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLessThanOrEqualTo(String value) {
            addCriterion("`cover` <=", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLike(String value) {
            addCriterion("`cover` like", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotLike(String value) {
            addCriterion("`cover` not like", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverIn(List<String> values) {
            addCriterion("`cover` in", values, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotIn(List<String> values) {
            addCriterion("`cover` not in", values, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverBetween(String value1, String value2) {
            addCriterion("`cover` between", value1, value2, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotBetween(String value1, String value2) {
            addCriterion("`cover` not between", value1, value2, "cover");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNull() {
            addCriterion("`notice` is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNotNull() {
            addCriterion("`notice` is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEqualTo(String value) {
            addCriterion("`notice` =", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotEqualTo(String value) {
            addCriterion("`notice` <>", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThan(String value) {
            addCriterion("`notice` >", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("`notice` >=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThan(String value) {
            addCriterion("`notice` <", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThanOrEqualTo(String value) {
            addCriterion("`notice` <=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLike(String value) {
            addCriterion("`notice` like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotLike(String value) {
            addCriterion("`notice` not like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeIn(List<String> values) {
            addCriterion("`notice` in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotIn(List<String> values) {
            addCriterion("`notice` not in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeBetween(String value1, String value2) {
            addCriterion("`notice` between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotBetween(String value1, String value2) {
            addCriterion("`notice` not between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andScoreFactorIsNull() {
            addCriterion("`score_factor` is null");
            return (Criteria) this;
        }

        public Criteria andScoreFactorIsNotNull() {
            addCriterion("`score_factor` is not null");
            return (Criteria) this;
        }

        public Criteria andScoreFactorEqualTo(Byte value) {
            addCriterion("`score_factor` =", value, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorNotEqualTo(Byte value) {
            addCriterion("`score_factor` <>", value, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorGreaterThan(Byte value) {
            addCriterion("`score_factor` >", value, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorGreaterThanOrEqualTo(Byte value) {
            addCriterion("`score_factor` >=", value, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorLessThan(Byte value) {
            addCriterion("`score_factor` <", value, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorLessThanOrEqualTo(Byte value) {
            addCriterion("`score_factor` <=", value, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorIn(List<Byte> values) {
            addCriterion("`score_factor` in", values, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorNotIn(List<Byte> values) {
            addCriterion("`score_factor` not in", values, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorBetween(Byte value1, Byte value2) {
            addCriterion("`score_factor` between", value1, value2, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andScoreFactorNotBetween(Byte value1, Byte value2) {
            addCriterion("`score_factor` not between", value1, value2, "scoreFactor");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIsNull() {
            addCriterion("`is_ recommend` is null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIsNotNull() {
            addCriterion("`is_ recommend` is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendEqualTo(Byte value) {
            addCriterion("`is_ recommend` =", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotEqualTo(Byte value) {
            addCriterion("`is_ recommend` <>", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendGreaterThan(Byte value) {
            addCriterion("`is_ recommend` >", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendGreaterThanOrEqualTo(Byte value) {
            addCriterion("`is_ recommend` >=", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendLessThan(Byte value) {
            addCriterion("`is_ recommend` <", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendLessThanOrEqualTo(Byte value) {
            addCriterion("`is_ recommend` <=", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIn(List<Byte> values) {
            addCriterion("`is_ recommend` in", values, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotIn(List<Byte> values) {
            addCriterion("`is_ recommend` not in", values, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendBetween(Byte value1, Byte value2) {
            addCriterion("`is_ recommend` between", value1, value2, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotBetween(Byte value1, Byte value2) {
            addCriterion("`is_ recommend` not between", value1, value2, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsDisplayIsNull() {
            addCriterion("`is_display` is null");
            return (Criteria) this;
        }

        public Criteria andIsDisplayIsNotNull() {
            addCriterion("`is_display` is not null");
            return (Criteria) this;
        }

        public Criteria andIsDisplayEqualTo(Byte value) {
            addCriterion("`is_display` =", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotEqualTo(Byte value) {
            addCriterion("`is_display` <>", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayGreaterThan(Byte value) {
            addCriterion("`is_display` >", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayGreaterThanOrEqualTo(Byte value) {
            addCriterion("`is_display` >=", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayLessThan(Byte value) {
            addCriterion("`is_display` <", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayLessThanOrEqualTo(Byte value) {
            addCriterion("`is_display` <=", value, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayIn(List<Byte> values) {
            addCriterion("`is_display` in", values, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotIn(List<Byte> values) {
            addCriterion("`is_display` not in", values, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayBetween(Byte value1, Byte value2) {
            addCriterion("`is_display` between", value1, value2, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsDisplayNotBetween(Byte value1, Byte value2) {
            addCriterion("`is_display` not between", value1, value2, "isDisplay");
            return (Criteria) this;
        }

        public Criteria andIsRenewIsNull() {
            addCriterion("`is_renew` is null");
            return (Criteria) this;
        }

        public Criteria andIsRenewIsNotNull() {
            addCriterion("`is_renew` is not null");
            return (Criteria) this;
        }

        public Criteria andIsRenewEqualTo(Byte value) {
            addCriterion("`is_renew` =", value, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewNotEqualTo(Byte value) {
            addCriterion("`is_renew` <>", value, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewGreaterThan(Byte value) {
            addCriterion("`is_renew` >", value, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewGreaterThanOrEqualTo(Byte value) {
            addCriterion("`is_renew` >=", value, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewLessThan(Byte value) {
            addCriterion("`is_renew` <", value, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewLessThanOrEqualTo(Byte value) {
            addCriterion("`is_renew` <=", value, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewIn(List<Byte> values) {
            addCriterion("`is_renew` in", values, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewNotIn(List<Byte> values) {
            addCriterion("`is_renew` not in", values, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewBetween(Byte value1, Byte value2) {
            addCriterion("`is_renew` between", value1, value2, "isRenew");
            return (Criteria) this;
        }

        public Criteria andIsRenewNotBetween(Byte value1, Byte value2) {
            addCriterion("`is_renew` not between", value1, value2, "isRenew");
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

        public Criteria andDetailUrlIsNull() {
            addCriterion("`detail_url` is null");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIsNotNull() {
            addCriterion("`detail_url` is not null");
            return (Criteria) this;
        }

        public Criteria andDetailUrlEqualTo(String value) {
            addCriterion("`detail_url` =", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotEqualTo(String value) {
            addCriterion("`detail_url` <>", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlGreaterThan(String value) {
            addCriterion("`detail_url` >", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`detail_url` >=", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLessThan(String value) {
            addCriterion("`detail_url` <", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("`detail_url` <=", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLike(String value) {
            addCriterion("`detail_url` like", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotLike(String value) {
            addCriterion("`detail_url` not like", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIn(List<String> values) {
            addCriterion("`detail_url` in", values, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotIn(List<String> values) {
            addCriterion("`detail_url` not in", values, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlBetween(String value1, String value2) {
            addCriterion("`detail_url` between", value1, value2, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotBetween(String value1, String value2) {
            addCriterion("`detail_url` not between", value1, value2, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinIsNull() {
            addCriterion("`invest_term_min` is null");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinIsNotNull() {
            addCriterion("`invest_term_min` is not null");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinEqualTo(Integer value) {
            addCriterion("`invest_term_min` =", value, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinNotEqualTo(Integer value) {
            addCriterion("`invest_term_min` <>", value, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinGreaterThan(Integer value) {
            addCriterion("`invest_term_min` >", value, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("`invest_term_min` >=", value, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinLessThan(Integer value) {
            addCriterion("`invest_term_min` <", value, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinLessThanOrEqualTo(Integer value) {
            addCriterion("`invest_term_min` <=", value, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinIn(List<Integer> values) {
            addCriterion("`invest_term_min` in", values, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinNotIn(List<Integer> values) {
            addCriterion("`invest_term_min` not in", values, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinBetween(Integer value1, Integer value2) {
            addCriterion("`invest_term_min` between", value1, value2, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMinNotBetween(Integer value1, Integer value2) {
            addCriterion("`invest_term_min` not between", value1, value2, "investTermMin");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxIsNull() {
            addCriterion("`invest_term_max` is null");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxIsNotNull() {
            addCriterion("`invest_term_max` is not null");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxEqualTo(Integer value) {
            addCriterion("`invest_term_max` =", value, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxNotEqualTo(Integer value) {
            addCriterion("`invest_term_max` <>", value, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxGreaterThan(Integer value) {
            addCriterion("`invest_term_max` >", value, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("`invest_term_max` >=", value, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxLessThan(Integer value) {
            addCriterion("`invest_term_max` <", value, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxLessThanOrEqualTo(Integer value) {
            addCriterion("`invest_term_max` <=", value, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxIn(List<Integer> values) {
            addCriterion("`invest_term_max` in", values, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxNotIn(List<Integer> values) {
            addCriterion("`invest_term_max` not in", values, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxBetween(Integer value1, Integer value2) {
            addCriterion("`invest_term_max` between", value1, value2, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestTermMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("`invest_term_max` not between", value1, value2, "investTermMax");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdIsNull() {
            addCriterion("`invest_threshold` is null");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdIsNotNull() {
            addCriterion("`invest_threshold` is not null");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdEqualTo(BigDecimal value) {
            addCriterion("`invest_threshold` =", value, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdNotEqualTo(BigDecimal value) {
            addCriterion("`invest_threshold` <>", value, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdGreaterThan(BigDecimal value) {
            addCriterion("`invest_threshold` >", value, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("`invest_threshold` >=", value, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdLessThan(BigDecimal value) {
            addCriterion("`invest_threshold` <", value, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("`invest_threshold` <=", value, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdIn(List<BigDecimal> values) {
            addCriterion("`invest_threshold` in", values, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdNotIn(List<BigDecimal> values) {
            addCriterion("`invest_threshold` not in", values, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`invest_threshold` between", value1, value2, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andInvestThresholdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`invest_threshold` not between", value1, value2, "investThreshold");
            return (Criteria) this;
        }

        public Criteria andExpectedMinIsNull() {
            addCriterion("`expected_min` is null");
            return (Criteria) this;
        }

        public Criteria andExpectedMinIsNotNull() {
            addCriterion("`expected_min` is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedMinEqualTo(Byte value) {
            addCriterion("`expected_min` =", value, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinNotEqualTo(Byte value) {
            addCriterion("`expected_min` <>", value, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinGreaterThan(Byte value) {
            addCriterion("`expected_min` >", value, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinGreaterThanOrEqualTo(Byte value) {
            addCriterion("`expected_min` >=", value, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinLessThan(Byte value) {
            addCriterion("`expected_min` <", value, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinLessThanOrEqualTo(Byte value) {
            addCriterion("`expected_min` <=", value, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinIn(List<Byte> values) {
            addCriterion("`expected_min` in", values, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinNotIn(List<Byte> values) {
            addCriterion("`expected_min` not in", values, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinBetween(Byte value1, Byte value2) {
            addCriterion("`expected_min` between", value1, value2, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMinNotBetween(Byte value1, Byte value2) {
            addCriterion("`expected_min` not between", value1, value2, "expectedMin");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxIsNull() {
            addCriterion("`expected_max` is null");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxIsNotNull() {
            addCriterion("`expected_max` is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxEqualTo(Byte value) {
            addCriterion("`expected_max` =", value, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxNotEqualTo(Byte value) {
            addCriterion("`expected_max` <>", value, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxGreaterThan(Byte value) {
            addCriterion("`expected_max` >", value, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxGreaterThanOrEqualTo(Byte value) {
            addCriterion("`expected_max` >=", value, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxLessThan(Byte value) {
            addCriterion("`expected_max` <", value, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxLessThanOrEqualTo(Byte value) {
            addCriterion("`expected_max` <=", value, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxIn(List<Byte> values) {
            addCriterion("`expected_max` in", values, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxNotIn(List<Byte> values) {
            addCriterion("`expected_max` not in", values, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxBetween(Byte value1, Byte value2) {
            addCriterion("`expected_max` between", value1, value2, "expectedMax");
            return (Criteria) this;
        }

        public Criteria andExpectedMaxNotBetween(Byte value1, Byte value2) {
            addCriterion("`expected_max` not between", value1, value2, "expectedMax");
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