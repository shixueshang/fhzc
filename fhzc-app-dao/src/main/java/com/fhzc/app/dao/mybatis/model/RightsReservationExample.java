package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RightsReservationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RightsReservationExample() {
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

        public Criteria andMarkDateIsNull() {
            addCriterion("`mark_date` is null");
            return (Criteria) this;
        }

        public Criteria andMarkDateIsNotNull() {
            addCriterion("`mark_date` is not null");
            return (Criteria) this;
        }

        public Criteria andMarkDateEqualTo(Date value) {
            addCriterion("`mark_date` =", value, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateNotEqualTo(Date value) {
            addCriterion("`mark_date` <>", value, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateGreaterThan(Date value) {
            addCriterion("`mark_date` >", value, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`mark_date` >=", value, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateLessThan(Date value) {
            addCriterion("`mark_date` <", value, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateLessThanOrEqualTo(Date value) {
            addCriterion("`mark_date` <=", value, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateIn(List<Date> values) {
            addCriterion("`mark_date` in", values, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateNotIn(List<Date> values) {
            addCriterion("`mark_date` not in", values, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateBetween(Date value1, Date value2) {
            addCriterion("`mark_date` between", value1, value2, "markDate");
            return (Criteria) this;
        }

        public Criteria andMarkDateNotBetween(Date value1, Date value2) {
            addCriterion("`mark_date` not between", value1, value2, "markDate");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("`detail` is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("`detail` is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("`detail` =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("`detail` <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("`detail` >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("`detail` >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("`detail` <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("`detail` <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("`detail` like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("`detail` not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("`detail` in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("`detail` not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("`detail` between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("`detail` not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andScoreCostIsNull() {
            addCriterion("`score_cost` is null");
            return (Criteria) this;
        }

        public Criteria andScoreCostIsNotNull() {
            addCriterion("`score_cost` is not null");
            return (Criteria) this;
        }

        public Criteria andScoreCostEqualTo(String value) {
            addCriterion("`score_cost` =", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostNotEqualTo(String value) {
            addCriterion("`score_cost` <>", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostGreaterThan(String value) {
            addCriterion("`score_cost` >", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostGreaterThanOrEqualTo(String value) {
            addCriterion("`score_cost` >=", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostLessThan(String value) {
            addCriterion("`score_cost` <", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostLessThanOrEqualTo(String value) {
            addCriterion("`score_cost` <=", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostLike(String value) {
            addCriterion("`score_cost` like", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostNotLike(String value) {
            addCriterion("`score_cost` not like", value, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostIn(List<String> values) {
            addCriterion("`score_cost` in", values, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostNotIn(List<String> values) {
            addCriterion("`score_cost` not in", values, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostBetween(String value1, String value2) {
            addCriterion("`score_cost` between", value1, value2, "scoreCost");
            return (Criteria) this;
        }

        public Criteria andScoreCostNotBetween(String value1, String value2) {
            addCriterion("`score_cost` not between", value1, value2, "scoreCost");
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