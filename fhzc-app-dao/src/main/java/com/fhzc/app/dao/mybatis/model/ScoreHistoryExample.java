package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ScoreHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScoreHistoryExample() {
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

        public Criteria andScoreIsNull() {
            addCriterion("`score` is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("`score` is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("`score` =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("`score` <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("`score` >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("`score` >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("`score` <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("`score` <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("`score` in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("`score` not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("`score` between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("`score` not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andEventIdIsNull() {
            addCriterion("`event_id` is null");
            return (Criteria) this;
        }

        public Criteria andEventIdIsNotNull() {
            addCriterion("`event_id` is not null");
            return (Criteria) this;
        }

        public Criteria andEventIdEqualTo(Integer value) {
            addCriterion("`event_id` =", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotEqualTo(Integer value) {
            addCriterion("`event_id` <>", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThan(Integer value) {
            addCriterion("`event_id` >", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`event_id` >=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThan(Integer value) {
            addCriterion("`event_id` <", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThanOrEqualTo(Integer value) {
            addCriterion("`event_id` <=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdIn(List<Integer> values) {
            addCriterion("`event_id` in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotIn(List<Integer> values) {
            addCriterion("`event_id` not in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdBetween(Integer value1, Integer value2) {
            addCriterion("`event_id` between", value1, value2, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`event_id` not between", value1, value2, "eventId");
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

        public Criteria andOperatorTypeIsNull() {
            addCriterion("`operator_type` is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNotNull() {
            addCriterion("`operator_type` is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeEqualTo(String value) {
            addCriterion("`operator_type` =", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotEqualTo(String value) {
            addCriterion("`operator_type` <>", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThan(String value) {
            addCriterion("`operator_type` >", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`operator_type` >=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThan(String value) {
            addCriterion("`operator_type` <", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThanOrEqualTo(String value) {
            addCriterion("`operator_type` <=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLike(String value) {
            addCriterion("`operator_type` like", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotLike(String value) {
            addCriterion("`operator_type` not like", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIn(List<String> values) {
            addCriterion("`operator_type` in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotIn(List<String> values) {
            addCriterion("`operator_type` not in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeBetween(String value1, String value2) {
            addCriterion("`operator_type` between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotBetween(String value1, String value2) {
            addCriterion("`operator_type` not between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("`operator_id` is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("`operator_id` is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("`operator_id` =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("`operator_id` <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("`operator_id` >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`operator_id` >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("`operator_id` <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("`operator_id` <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("`operator_id` in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("`operator_id` not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("`operator_id` between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`operator_id` not between", value1, value2, "operatorId");
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

        public Criteria andFromTypeIsNull() {
            addCriterion("`from_type` is null");
            return (Criteria) this;
        }

        public Criteria andFromTypeIsNotNull() {
            addCriterion("`from_type` is not null");
            return (Criteria) this;
        }

        public Criteria andFromTypeEqualTo(String value) {
            addCriterion("`from_type` =", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotEqualTo(String value) {
            addCriterion("`from_type` <>", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeGreaterThan(String value) {
            addCriterion("`from_type` >", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`from_type` >=", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeLessThan(String value) {
            addCriterion("`from_type` <", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeLessThanOrEqualTo(String value) {
            addCriterion("`from_type` <=", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeLike(String value) {
            addCriterion("`from_type` like", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotLike(String value) {
            addCriterion("`from_type` not like", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeIn(List<String> values) {
            addCriterion("`from_type` in", values, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotIn(List<String> values) {
            addCriterion("`from_type` not in", values, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeBetween(String value1, String value2) {
            addCriterion("`from_type` between", value1, value2, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotBetween(String value1, String value2) {
            addCriterion("`from_type` not between", value1, value2, "fromType");
            return (Criteria) this;
        }

        public Criteria andVaildTimeIsNull() {
            addCriterion("`vaild_time` is null");
            return (Criteria) this;
        }

        public Criteria andVaildTimeIsNotNull() {
            addCriterion("`vaild_time` is not null");
            return (Criteria) this;
        }

        public Criteria andVaildTimeEqualTo(Date value) {
            addCriterionForJDBCDate("`vaild_time` =", value, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("`vaild_time` <>", value, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("`vaild_time` >", value, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`vaild_time` >=", value, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeLessThan(Date value) {
            addCriterionForJDBCDate("`vaild_time` <", value, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`vaild_time` <=", value, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeIn(List<Date> values) {
            addCriterionForJDBCDate("`vaild_time` in", values, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("`vaild_time` not in", values, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`vaild_time` between", value1, value2, "vaildTime");
            return (Criteria) this;
        }

        public Criteria andVaildTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`vaild_time` not between", value1, value2, "vaildTime");
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

        public Criteria andIsVaildIsNull() {
            addCriterion("`is_vaild` is null");
            return (Criteria) this;
        }

        public Criteria andIsVaildIsNotNull() {
            addCriterion("`is_vaild` is not null");
            return (Criteria) this;
        }

        public Criteria andIsVaildEqualTo(Integer value) {
            addCriterion("`is_vaild` =", value, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildNotEqualTo(Integer value) {
            addCriterion("`is_vaild` <>", value, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildGreaterThan(Integer value) {
            addCriterion("`is_vaild` >", value, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildGreaterThanOrEqualTo(Integer value) {
            addCriterion("`is_vaild` >=", value, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildLessThan(Integer value) {
            addCriterion("`is_vaild` <", value, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildLessThanOrEqualTo(Integer value) {
            addCriterion("`is_vaild` <=", value, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildIn(List<Integer> values) {
            addCriterion("`is_vaild` in", values, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildNotIn(List<Integer> values) {
            addCriterion("`is_vaild` not in", values, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildBetween(Integer value1, Integer value2) {
            addCriterion("`is_vaild` between", value1, value2, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsVaildNotBetween(Integer value1, Integer value2) {
            addCriterion("`is_vaild` not between", value1, value2, "isVaild");
            return (Criteria) this;
        }

        public Criteria andIsApproveIsNull() {
            addCriterion("`is_approve` is null");
            return (Criteria) this;
        }

        public Criteria andIsApproveIsNotNull() {
            addCriterion("`is_approve` is not null");
            return (Criteria) this;
        }

        public Criteria andIsApproveEqualTo(Integer value) {
            addCriterion("`is_approve` =", value, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveNotEqualTo(Integer value) {
            addCriterion("`is_approve` <>", value, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveGreaterThan(Integer value) {
            addCriterion("`is_approve` >", value, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveGreaterThanOrEqualTo(Integer value) {
            addCriterion("`is_approve` >=", value, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveLessThan(Integer value) {
            addCriterion("`is_approve` <", value, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveLessThanOrEqualTo(Integer value) {
            addCriterion("`is_approve` <=", value, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveIn(List<Integer> values) {
            addCriterion("`is_approve` in", values, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveNotIn(List<Integer> values) {
            addCriterion("`is_approve` not in", values, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveBetween(Integer value1, Integer value2) {
            addCriterion("`is_approve` between", value1, value2, "isApprove");
            return (Criteria) this;
        }

        public Criteria andIsApproveNotBetween(Integer value1, Integer value2) {
            addCriterion("`is_approve` not between", value1, value2, "isApprove");
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