package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AssetsHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssetsHistoryExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("`amount` is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("`amount` is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("`amount` =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("`amount` <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("`amount` >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("`amount` >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("`amount` <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("`amount` <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("`amount` in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("`amount` not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("`amount` between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("`amount` not between", value1, value2, "amount");
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

        public Criteria andDeadDateIsNull() {
            addCriterion("`dead_date` is null");
            return (Criteria) this;
        }

        public Criteria andDeadDateIsNotNull() {
            addCriterion("`dead_date` is not null");
            return (Criteria) this;
        }

        public Criteria andDeadDateEqualTo(Date value) {
            addCriterionForJDBCDate("`dead_date` =", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("`dead_date` <>", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateGreaterThan(Date value) {
            addCriterionForJDBCDate("`dead_date` >", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`dead_date` >=", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateLessThan(Date value) {
            addCriterionForJDBCDate("`dead_date` <", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`dead_date` <=", value, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateIn(List<Date> values) {
            addCriterionForJDBCDate("`dead_date` in", values, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("`dead_date` not in", values, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`dead_date` between", value1, value2, "deadDate");
            return (Criteria) this;
        }

        public Criteria andDeadDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`dead_date` not between", value1, value2, "deadDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNull() {
            addCriterion("`payment_date` is null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNotNull() {
            addCriterion("`payment_date` is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateEqualTo(Date value) {
            addCriterionForJDBCDate("`payment_date` =", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("`payment_date` <>", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThan(Date value) {
            addCriterionForJDBCDate("`payment_date` >", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`payment_date` >=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThan(Date value) {
            addCriterionForJDBCDate("`payment_date` <", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`payment_date` <=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIn(List<Date> values) {
            addCriterionForJDBCDate("`payment_date` in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("`payment_date` not in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`payment_date` between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`payment_date` not between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andSerialIsNull() {
            addCriterion("`serial` is null");
            return (Criteria) this;
        }

        public Criteria andSerialIsNotNull() {
            addCriterion("`serial` is not null");
            return (Criteria) this;
        }

        public Criteria andSerialEqualTo(String value) {
            addCriterion("`serial` =", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotEqualTo(String value) {
            addCriterion("`serial` <>", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThan(String value) {
            addCriterion("`serial` >", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThanOrEqualTo(String value) {
            addCriterion("`serial` >=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThan(String value) {
            addCriterion("`serial` <", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThanOrEqualTo(String value) {
            addCriterion("`serial` <=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLike(String value) {
            addCriterion("`serial` like", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotLike(String value) {
            addCriterion("`serial` not like", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialIn(List<String> values) {
            addCriterion("`serial` in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotIn(List<String> values) {
            addCriterion("`serial` not in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialBetween(String value1, String value2) {
            addCriterion("`serial` between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotBetween(String value1, String value2) {
            addCriterion("`serial` not between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("`customer_name` is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("`customer_name` is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("`customer_name` =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("`customer_name` <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("`customer_name` >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("`customer_name` >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("`customer_name` <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("`customer_name` <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("`customer_name` like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("`customer_name` not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("`customer_name` in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("`customer_name` not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("`customer_name` between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("`customer_name` not between", value1, value2, "customerName");
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

        public Criteria andBuyTimeIsNull() {
            addCriterion("`buy_time` is null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNotNull() {
            addCriterion("`buy_time` is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_time` =", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_time` <>", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("`buy_time` >", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_time` >=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThan(Date value) {
            addCriterionForJDBCDate("`buy_time` <", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`buy_time` <=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIn(List<Date> values) {
            addCriterionForJDBCDate("`buy_time` in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("`buy_time` not in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`buy_time` between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`buy_time` not between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andAmountUsdIsNull() {
            addCriterion("`amount_usd` is null");
            return (Criteria) this;
        }

        public Criteria andAmountUsdIsNotNull() {
            addCriterion("`amount_usd` is not null");
            return (Criteria) this;
        }

        public Criteria andAmountUsdEqualTo(Integer value) {
            addCriterion("`amount_usd` =", value, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdNotEqualTo(Integer value) {
            addCriterion("`amount_usd` <>", value, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdGreaterThan(Integer value) {
            addCriterion("`amount_usd` >", value, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`amount_usd` >=", value, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdLessThan(Integer value) {
            addCriterion("`amount_usd` <", value, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdLessThanOrEqualTo(Integer value) {
            addCriterion("`amount_usd` <=", value, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdIn(List<Integer> values) {
            addCriterion("`amount_usd` in", values, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdNotIn(List<Integer> values) {
            addCriterion("`amount_usd` not in", values, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdBetween(Integer value1, Integer value2) {
            addCriterion("`amount_usd` between", value1, value2, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountUsdNotBetween(Integer value1, Integer value2) {
            addCriterion("`amount_usd` not between", value1, value2, "amountUsd");
            return (Criteria) this;
        }

        public Criteria andAmountRmbIsNull() {
            addCriterion("`amount_rmb` is null");
            return (Criteria) this;
        }

        public Criteria andAmountRmbIsNotNull() {
            addCriterion("`amount_rmb` is not null");
            return (Criteria) this;
        }

        public Criteria andAmountRmbEqualTo(Integer value) {
            addCriterion("`amount_rmb` =", value, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbNotEqualTo(Integer value) {
            addCriterion("`amount_rmb` <>", value, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbGreaterThan(Integer value) {
            addCriterion("`amount_rmb` >", value, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbGreaterThanOrEqualTo(Integer value) {
            addCriterion("`amount_rmb` >=", value, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbLessThan(Integer value) {
            addCriterion("`amount_rmb` <", value, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbLessThanOrEqualTo(Integer value) {
            addCriterion("`amount_rmb` <=", value, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbIn(List<Integer> values) {
            addCriterion("`amount_rmb` in", values, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbNotIn(List<Integer> values) {
            addCriterion("`amount_rmb` not in", values, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbBetween(Integer value1, Integer value2) {
            addCriterion("`amount_rmb` between", value1, value2, "amountRmb");
            return (Criteria) this;
        }

        public Criteria andAmountRmbNotBetween(Integer value1, Integer value2) {
            addCriterion("`amount_rmb` not between", value1, value2, "amountRmb");
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

        public Criteria andPeriodIsNull() {
            addCriterion("`period` is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("`period` is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("`period` =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("`period` <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("`period` >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("`period` >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("`period` <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("`period` <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("`period` like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("`period` not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("`period` in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("`period` not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("`period` between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("`period` not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andInvaildIsNull() {
            addCriterion("`invaild` is null");
            return (Criteria) this;
        }

        public Criteria andInvaildIsNotNull() {
            addCriterion("`invaild` is not null");
            return (Criteria) this;
        }

        public Criteria andInvaildEqualTo(Byte value) {
            addCriterion("`invaild` =", value, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildNotEqualTo(Byte value) {
            addCriterion("`invaild` <>", value, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildGreaterThan(Byte value) {
            addCriterion("`invaild` >", value, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildGreaterThanOrEqualTo(Byte value) {
            addCriterion("`invaild` >=", value, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildLessThan(Byte value) {
            addCriterion("`invaild` <", value, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildLessThanOrEqualTo(Byte value) {
            addCriterion("`invaild` <=", value, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildIn(List<Byte> values) {
            addCriterion("`invaild` in", values, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildNotIn(List<Byte> values) {
            addCriterion("`invaild` not in", values, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildBetween(Byte value1, Byte value2) {
            addCriterion("`invaild` between", value1, value2, "invaild");
            return (Criteria) this;
        }

        public Criteria andInvaildNotBetween(Byte value1, Byte value2) {
            addCriterion("`invaild` not between", value1, value2, "invaild");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayIsNull() {
            addCriterion("`product_found_day` is null");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayIsNotNull() {
            addCriterion("`product_found_day` is not null");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayEqualTo(Date value) {
            addCriterionForJDBCDate("`product_found_day` =", value, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`product_found_day` <>", value, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`product_found_day` >", value, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`product_found_day` >=", value, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayLessThan(Date value) {
            addCriterionForJDBCDate("`product_found_day` <", value, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`product_found_day` <=", value, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayIn(List<Date> values) {
            addCriterionForJDBCDate("`product_found_day` in", values, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`product_found_day` not in", values, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`product_found_day` between", value1, value2, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andProductFoundDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`product_found_day` not between", value1, value2, "productFoundDay");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNull() {
            addCriterion("`value_date` is null");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNotNull() {
            addCriterion("`value_date` is not null");
            return (Criteria) this;
        }

        public Criteria andValueDateEqualTo(Date value) {
            addCriterionForJDBCDate("`value_date` =", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("`value_date` <>", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThan(Date value) {
            addCriterionForJDBCDate("`value_date` >", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`value_date` >=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThan(Date value) {
            addCriterionForJDBCDate("`value_date` <", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`value_date` <=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateIn(List<Date> values) {
            addCriterionForJDBCDate("`value_date` in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("`value_date` not in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`value_date` between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`value_date` not between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayIsNull() {
            addCriterion("`product_expire_day` is null");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayIsNotNull() {
            addCriterion("`product_expire_day` is not null");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayEqualTo(Date value) {
            addCriterionForJDBCDate("`product_expire_day` =", value, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`product_expire_day` <>", value, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`product_expire_day` >", value, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`product_expire_day` >=", value, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayLessThan(Date value) {
            addCriterionForJDBCDate("`product_expire_day` <", value, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`product_expire_day` <=", value, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayIn(List<Date> values) {
            addCriterionForJDBCDate("`product_expire_day` in", values, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`product_expire_day` not in", values, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`product_expire_day` between", value1, value2, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andProductExpireDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`product_expire_day` not between", value1, value2, "productExpireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayIsNull() {
            addCriterion("`expire_day` is null");
            return (Criteria) this;
        }

        public Criteria andExpireDayIsNotNull() {
            addCriterion("`expire_day` is not null");
            return (Criteria) this;
        }

        public Criteria andExpireDayEqualTo(Date value) {
            addCriterionForJDBCDate("`expire_day` =", value, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`expire_day` <>", value, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayGreaterThan(Date value) {
            addCriterionForJDBCDate("`expire_day` >", value, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`expire_day` >=", value, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayLessThan(Date value) {
            addCriterionForJDBCDate("`expire_day` <", value, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`expire_day` <=", value, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayIn(List<Date> values) {
            addCriterionForJDBCDate("`expire_day` in", values, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`expire_day` not in", values, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`expire_day` between", value1, value2, "expireDay");
            return (Criteria) this;
        }

        public Criteria andExpireDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`expire_day` not between", value1, value2, "expireDay");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("`bank` is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("`bank` is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("`bank` =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("`bank` <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("`bank` >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("`bank` >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("`bank` <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("`bank` <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("`bank` like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("`bank` not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("`bank` in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("`bank` not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("`bank` between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("`bank` not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("`bank_account` is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("`bank_account` is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("`bank_account` =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("`bank_account` <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("`bank_account` >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("`bank_account` >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("`bank_account` <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("`bank_account` <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("`bank_account` like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("`bank_account` not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("`bank_account` in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("`bank_account` not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("`bank_account` between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("`bank_account` not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andLotIsNull() {
            addCriterion("`lot` is null");
            return (Criteria) this;
        }

        public Criteria andLotIsNotNull() {
            addCriterion("`lot` is not null");
            return (Criteria) this;
        }

        public Criteria andLotEqualTo(String value) {
            addCriterion("`lot` =", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotNotEqualTo(String value) {
            addCriterion("`lot` <>", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotGreaterThan(String value) {
            addCriterion("`lot` >", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotGreaterThanOrEqualTo(String value) {
            addCriterion("`lot` >=", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotLessThan(String value) {
            addCriterion("`lot` <", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotLessThanOrEqualTo(String value) {
            addCriterion("`lot` <=", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotLike(String value) {
            addCriterion("`lot` like", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotNotLike(String value) {
            addCriterion("`lot` not like", value, "lot");
            return (Criteria) this;
        }

        public Criteria andLotIn(List<String> values) {
            addCriterion("`lot` in", values, "lot");
            return (Criteria) this;
        }

        public Criteria andLotNotIn(List<String> values) {
            addCriterion("`lot` not in", values, "lot");
            return (Criteria) this;
        }

        public Criteria andLotBetween(String value1, String value2) {
            addCriterion("`lot` between", value1, value2, "lot");
            return (Criteria) this;
        }

        public Criteria andLotNotBetween(String value1, String value2) {
            addCriterion("`lot` not between", value1, value2, "lot");
            return (Criteria) this;
        }

        public Criteria andDurationMonthIsNull() {
            addCriterion("`duration_month` is null");
            return (Criteria) this;
        }

        public Criteria andDurationMonthIsNotNull() {
            addCriterion("`duration_month` is not null");
            return (Criteria) this;
        }

        public Criteria andDurationMonthEqualTo(Integer value) {
            addCriterion("`duration_month` =", value, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthNotEqualTo(Integer value) {
            addCriterion("`duration_month` <>", value, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthGreaterThan(Integer value) {
            addCriterion("`duration_month` >", value, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("`duration_month` >=", value, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthLessThan(Integer value) {
            addCriterion("`duration_month` <", value, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthLessThanOrEqualTo(Integer value) {
            addCriterion("`duration_month` <=", value, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthIn(List<Integer> values) {
            addCriterion("`duration_month` in", values, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthNotIn(List<Integer> values) {
            addCriterion("`duration_month` not in", values, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthBetween(Integer value1, Integer value2) {
            addCriterion("`duration_month` between", value1, value2, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("`duration_month` not between", value1, value2, "durationMonth");
            return (Criteria) this;
        }

        public Criteria andDurationDayIsNull() {
            addCriterion("`duration_day` is null");
            return (Criteria) this;
        }

        public Criteria andDurationDayIsNotNull() {
            addCriterion("`duration_day` is not null");
            return (Criteria) this;
        }

        public Criteria andDurationDayEqualTo(Integer value) {
            addCriterion("`duration_day` =", value, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayNotEqualTo(Integer value) {
            addCriterion("`duration_day` <>", value, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayGreaterThan(Integer value) {
            addCriterion("`duration_day` >", value, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("`duration_day` >=", value, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayLessThan(Integer value) {
            addCriterion("`duration_day` <", value, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayLessThanOrEqualTo(Integer value) {
            addCriterion("`duration_day` <=", value, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayIn(List<Integer> values) {
            addCriterion("`duration_day` in", values, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayNotIn(List<Integer> values) {
            addCriterion("`duration_day` not in", values, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayBetween(Integer value1, Integer value2) {
            addCriterion("`duration_day` between", value1, value2, "durationDay");
            return (Criteria) this;
        }

        public Criteria andDurationDayNotBetween(Integer value1, Integer value2) {
            addCriterion("`duration_day` not between", value1, value2, "durationDay");
            return (Criteria) this;
        }

        public Criteria andPubAgentIsNull() {
            addCriterion("`pub_agent` is null");
            return (Criteria) this;
        }

        public Criteria andPubAgentIsNotNull() {
            addCriterion("`pub_agent` is not null");
            return (Criteria) this;
        }

        public Criteria andPubAgentEqualTo(String value) {
            addCriterion("`pub_agent` =", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentNotEqualTo(String value) {
            addCriterion("`pub_agent` <>", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentGreaterThan(String value) {
            addCriterion("`pub_agent` >", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentGreaterThanOrEqualTo(String value) {
            addCriterion("`pub_agent` >=", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentLessThan(String value) {
            addCriterion("`pub_agent` <", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentLessThanOrEqualTo(String value) {
            addCriterion("`pub_agent` <=", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentLike(String value) {
            addCriterion("`pub_agent` like", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentNotLike(String value) {
            addCriterion("`pub_agent` not like", value, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentIn(List<String> values) {
            addCriterion("`pub_agent` in", values, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentNotIn(List<String> values) {
            addCriterion("`pub_agent` not in", values, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentBetween(String value1, String value2) {
            addCriterion("`pub_agent` between", value1, value2, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andPubAgentNotBetween(String value1, String value2) {
            addCriterion("`pub_agent` not between", value1, value2, "pubAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentIsNull() {
            addCriterion("`branch_agent` is null");
            return (Criteria) this;
        }

        public Criteria andBranchAgentIsNotNull() {
            addCriterion("`branch_agent` is not null");
            return (Criteria) this;
        }

        public Criteria andBranchAgentEqualTo(String value) {
            addCriterion("`branch_agent` =", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentNotEqualTo(String value) {
            addCriterion("`branch_agent` <>", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentGreaterThan(String value) {
            addCriterion("`branch_agent` >", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentGreaterThanOrEqualTo(String value) {
            addCriterion("`branch_agent` >=", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentLessThan(String value) {
            addCriterion("`branch_agent` <", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentLessThanOrEqualTo(String value) {
            addCriterion("`branch_agent` <=", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentLike(String value) {
            addCriterion("`branch_agent` like", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentNotLike(String value) {
            addCriterion("`branch_agent` not like", value, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentIn(List<String> values) {
            addCriterion("`branch_agent` in", values, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentNotIn(List<String> values) {
            addCriterion("`branch_agent` not in", values, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentBetween(String value1, String value2) {
            addCriterion("`branch_agent` between", value1, value2, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andBranchAgentNotBetween(String value1, String value2) {
            addCriterion("`branch_agent` not between", value1, value2, "branchAgent");
            return (Criteria) this;
        }

        public Criteria andIsMemberIsNull() {
            addCriterion("`is_member` is null");
            return (Criteria) this;
        }

        public Criteria andIsMemberIsNotNull() {
            addCriterion("`is_member` is not null");
            return (Criteria) this;
        }

        public Criteria andIsMemberEqualTo(Byte value) {
            addCriterion("`is_member` =", value, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberNotEqualTo(Byte value) {
            addCriterion("`is_member` <>", value, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberGreaterThan(Byte value) {
            addCriterion("`is_member` >", value, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberGreaterThanOrEqualTo(Byte value) {
            addCriterion("`is_member` >=", value, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberLessThan(Byte value) {
            addCriterion("`is_member` <", value, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberLessThanOrEqualTo(Byte value) {
            addCriterion("`is_member` <=", value, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberIn(List<Byte> values) {
            addCriterion("`is_member` in", values, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberNotIn(List<Byte> values) {
            addCriterion("`is_member` not in", values, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberBetween(Byte value1, Byte value2) {
            addCriterion("`is_member` between", value1, value2, "isMember");
            return (Criteria) this;
        }

        public Criteria andIsMemberNotBetween(Byte value1, Byte value2) {
            addCriterion("`is_member` not between", value1, value2, "isMember");
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

        public Criteria andEarningRateIsNull() {
            addCriterion("`earning_rate` is null");
            return (Criteria) this;
        }

        public Criteria andEarningRateIsNotNull() {
            addCriterion("`earning_rate` is not null");
            return (Criteria) this;
        }

        public Criteria andEarningRateEqualTo(String value) {
            addCriterion("`earning_rate` =", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateNotEqualTo(String value) {
            addCriterion("`earning_rate` <>", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateGreaterThan(String value) {
            addCriterion("`earning_rate` >", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateGreaterThanOrEqualTo(String value) {
            addCriterion("`earning_rate` >=", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateLessThan(String value) {
            addCriterion("`earning_rate` <", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateLessThanOrEqualTo(String value) {
            addCriterion("`earning_rate` <=", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateLike(String value) {
            addCriterion("`earning_rate` like", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateNotLike(String value) {
            addCriterion("`earning_rate` not like", value, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateIn(List<String> values) {
            addCriterion("`earning_rate` in", values, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateNotIn(List<String> values) {
            addCriterion("`earning_rate` not in", values, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateBetween(String value1, String value2) {
            addCriterion("`earning_rate` between", value1, value2, "earningRate");
            return (Criteria) this;
        }

        public Criteria andEarningRateNotBetween(String value1, String value2) {
            addCriterion("`earning_rate` not between", value1, value2, "earningRate");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningIsNull() {
            addCriterion("`distribute_earning` is null");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningIsNotNull() {
            addCriterion("`distribute_earning` is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningEqualTo(String value) {
            addCriterion("`distribute_earning` =", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningNotEqualTo(String value) {
            addCriterion("`distribute_earning` <>", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningGreaterThan(String value) {
            addCriterion("`distribute_earning` >", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningGreaterThanOrEqualTo(String value) {
            addCriterion("`distribute_earning` >=", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningLessThan(String value) {
            addCriterion("`distribute_earning` <", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningLessThanOrEqualTo(String value) {
            addCriterion("`distribute_earning` <=", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningLike(String value) {
            addCriterion("`distribute_earning` like", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningNotLike(String value) {
            addCriterion("`distribute_earning` not like", value, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningIn(List<String> values) {
            addCriterion("`distribute_earning` in", values, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningNotIn(List<String> values) {
            addCriterion("`distribute_earning` not in", values, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningBetween(String value1, String value2) {
            addCriterion("`distribute_earning` between", value1, value2, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andDistributeEarningNotBetween(String value1, String value2) {
            addCriterion("`distribute_earning` not between", value1, value2, "distributeEarning");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("`payment` is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("`payment` is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(String value) {
            addCriterion("`payment` =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(String value) {
            addCriterion("`payment` <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(String value) {
            addCriterion("`payment` >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(String value) {
            addCriterion("`payment` >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(String value) {
            addCriterion("`payment` <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(String value) {
            addCriterion("`payment` <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLike(String value) {
            addCriterion("`payment` like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotLike(String value) {
            addCriterion("`payment` not like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<String> values) {
            addCriterion("`payment` in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<String> values) {
            addCriterion("`payment` not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(String value1, String value2) {
            addCriterion("`payment` between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(String value1, String value2) {
            addCriterion("`payment` not between", value1, value2, "payment");
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