package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andLoginIsNull() {
            addCriterion("`login` is null");
            return (Criteria) this;
        }

        public Criteria andLoginIsNotNull() {
            addCriterion("`login` is not null");
            return (Criteria) this;
        }

        public Criteria andLoginEqualTo(String value) {
            addCriterion("`login` =", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotEqualTo(String value) {
            addCriterion("`login` <>", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginGreaterThan(String value) {
            addCriterion("`login` >", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginGreaterThanOrEqualTo(String value) {
            addCriterion("`login` >=", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginLessThan(String value) {
            addCriterion("`login` <", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginLessThanOrEqualTo(String value) {
            addCriterion("`login` <=", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginLike(String value) {
            addCriterion("`login` like", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotLike(String value) {
            addCriterion("`login` not like", value, "login");
            return (Criteria) this;
        }

        public Criteria andLoginIn(List<String> values) {
            addCriterion("`login` in", values, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotIn(List<String> values) {
            addCriterion("`login` not in", values, "login");
            return (Criteria) this;
        }

        public Criteria andLoginBetween(String value1, String value2) {
            addCriterion("`login` between", value1, value2, "login");
            return (Criteria) this;
        }

        public Criteria andLoginNotBetween(String value1, String value2) {
            addCriterion("`login` not between", value1, value2, "login");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("`realname` is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("`realname` is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("`realname` =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("`realname` <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("`realname` >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("`realname` >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("`realname` <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("`realname` <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("`realname` like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("`realname` not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("`realname` in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("`realname` not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("`realname` between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("`realname` not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("`gender` is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("`gender` is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("`gender` =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("`gender` <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("`gender` >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("`gender` >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("`gender` <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("`gender` <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("`gender` like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("`gender` not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("`gender` in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("`gender` not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("`gender` between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("`gender` not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("`birthday` is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("`birthday` is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("`birthday` =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("`birthday` <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("`birthday` >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`birthday` >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("`birthday` <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`birthday` <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("`birthday` in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("`birthday` not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`birthday` between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`birthday` not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdIsNull() {
            addCriterion("`passport_type_id` is null");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdIsNotNull() {
            addCriterion("`passport_type_id` is not null");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdEqualTo(Integer value) {
            addCriterion("`passport_type_id` =", value, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdNotEqualTo(Integer value) {
            addCriterion("`passport_type_id` <>", value, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdGreaterThan(Integer value) {
            addCriterion("`passport_type_id` >", value, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`passport_type_id` >=", value, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdLessThan(Integer value) {
            addCriterion("`passport_type_id` <", value, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("`passport_type_id` <=", value, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdIn(List<Integer> values) {
            addCriterion("`passport_type_id` in", values, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdNotIn(List<Integer> values) {
            addCriterion("`passport_type_id` not in", values, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("`passport_type_id` between", value1, value2, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`passport_type_id` not between", value1, value2, "passportTypeId");
            return (Criteria) this;
        }

        public Criteria andPassportCodeIsNull() {
            addCriterion("`passport_code` is null");
            return (Criteria) this;
        }

        public Criteria andPassportCodeIsNotNull() {
            addCriterion("`passport_code` is not null");
            return (Criteria) this;
        }

        public Criteria andPassportCodeEqualTo(String value) {
            addCriterion("`passport_code` =", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeNotEqualTo(String value) {
            addCriterion("`passport_code` <>", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeGreaterThan(String value) {
            addCriterion("`passport_code` >", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeGreaterThanOrEqualTo(String value) {
            addCriterion("`passport_code` >=", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeLessThan(String value) {
            addCriterion("`passport_code` <", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeLessThanOrEqualTo(String value) {
            addCriterion("`passport_code` <=", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeLike(String value) {
            addCriterion("`passport_code` like", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeNotLike(String value) {
            addCriterion("`passport_code` not like", value, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeIn(List<String> values) {
            addCriterion("`passport_code` in", values, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeNotIn(List<String> values) {
            addCriterion("`passport_code` not in", values, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeBetween(String value1, String value2) {
            addCriterion("`passport_code` between", value1, value2, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportCodeNotBetween(String value1, String value2) {
            addCriterion("`passport_code` not between", value1, value2, "passportCode");
            return (Criteria) this;
        }

        public Criteria andPassportAgentIsNull() {
            addCriterion("`passport_agent` is null");
            return (Criteria) this;
        }

        public Criteria andPassportAgentIsNotNull() {
            addCriterion("`passport_agent` is not null");
            return (Criteria) this;
        }

        public Criteria andPassportAgentEqualTo(String value) {
            addCriterion("`passport_agent` =", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentNotEqualTo(String value) {
            addCriterion("`passport_agent` <>", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentGreaterThan(String value) {
            addCriterion("`passport_agent` >", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentGreaterThanOrEqualTo(String value) {
            addCriterion("`passport_agent` >=", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentLessThan(String value) {
            addCriterion("`passport_agent` <", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentLessThanOrEqualTo(String value) {
            addCriterion("`passport_agent` <=", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentLike(String value) {
            addCriterion("`passport_agent` like", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentNotLike(String value) {
            addCriterion("`passport_agent` not like", value, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentIn(List<String> values) {
            addCriterion("`passport_agent` in", values, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentNotIn(List<String> values) {
            addCriterion("`passport_agent` not in", values, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentBetween(String value1, String value2) {
            addCriterion("`passport_agent` between", value1, value2, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAgentNotBetween(String value1, String value2) {
            addCriterion("`passport_agent` not between", value1, value2, "passportAgent");
            return (Criteria) this;
        }

        public Criteria andPassportAddressIsNull() {
            addCriterion("`passport_address` is null");
            return (Criteria) this;
        }

        public Criteria andPassportAddressIsNotNull() {
            addCriterion("`passport_address` is not null");
            return (Criteria) this;
        }

        public Criteria andPassportAddressEqualTo(String value) {
            addCriterion("`passport_address` =", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressNotEqualTo(String value) {
            addCriterion("`passport_address` <>", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressGreaterThan(String value) {
            addCriterion("`passport_address` >", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressGreaterThanOrEqualTo(String value) {
            addCriterion("`passport_address` >=", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressLessThan(String value) {
            addCriterion("`passport_address` <", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressLessThanOrEqualTo(String value) {
            addCriterion("`passport_address` <=", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressLike(String value) {
            addCriterion("`passport_address` like", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressNotLike(String value) {
            addCriterion("`passport_address` not like", value, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressIn(List<String> values) {
            addCriterion("`passport_address` in", values, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressNotIn(List<String> values) {
            addCriterion("`passport_address` not in", values, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressBetween(String value1, String value2) {
            addCriterion("`passport_address` between", value1, value2, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportAddressNotBetween(String value1, String value2) {
            addCriterion("`passport_address` not between", value1, value2, "passportAddress");
            return (Criteria) this;
        }

        public Criteria andPassportExpireIsNull() {
            addCriterion("`passport_expire` is null");
            return (Criteria) this;
        }

        public Criteria andPassportExpireIsNotNull() {
            addCriterion("`passport_expire` is not null");
            return (Criteria) this;
        }

        public Criteria andPassportExpireEqualTo(String value) {
            addCriterion("`passport_expire` =", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireNotEqualTo(String value) {
            addCriterion("`passport_expire` <>", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireGreaterThan(String value) {
            addCriterion("`passport_expire` >", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireGreaterThanOrEqualTo(String value) {
            addCriterion("`passport_expire` >=", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireLessThan(String value) {
            addCriterion("`passport_expire` <", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireLessThanOrEqualTo(String value) {
            addCriterion("`passport_expire` <=", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireLike(String value) {
            addCriterion("`passport_expire` like", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireNotLike(String value) {
            addCriterion("`passport_expire` not like", value, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireIn(List<String> values) {
            addCriterion("`passport_expire` in", values, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireNotIn(List<String> values) {
            addCriterion("`passport_expire` not in", values, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireBetween(String value1, String value2) {
            addCriterion("`passport_expire` between", value1, value2, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andPassportExpireNotBetween(String value1, String value2) {
            addCriterion("`passport_expire` not between", value1, value2, "passportExpire");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("`mobile` is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("`mobile` is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("`mobile` =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("`mobile` <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("`mobile` >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("`mobile` >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("`mobile` <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("`mobile` <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("`mobile` like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("`mobile` not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("`mobile` in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("`mobile` not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("`mobile` between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("`mobile` not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("`phone` is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("`phone` is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("`phone` =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("`phone` <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("`phone` >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("`phone` >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("`phone` <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("`phone` <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("`phone` like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("`phone` not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("`phone` in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("`phone` not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("`phone` between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("`phone` not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneExtIsNull() {
            addCriterion("`phone_ext` is null");
            return (Criteria) this;
        }

        public Criteria andPhoneExtIsNotNull() {
            addCriterion("`phone_ext` is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneExtEqualTo(String value) {
            addCriterion("`phone_ext` =", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtNotEqualTo(String value) {
            addCriterion("`phone_ext` <>", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtGreaterThan(String value) {
            addCriterion("`phone_ext` >", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtGreaterThanOrEqualTo(String value) {
            addCriterion("`phone_ext` >=", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtLessThan(String value) {
            addCriterion("`phone_ext` <", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtLessThanOrEqualTo(String value) {
            addCriterion("`phone_ext` <=", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtLike(String value) {
            addCriterion("`phone_ext` like", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtNotLike(String value) {
            addCriterion("`phone_ext` not like", value, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtIn(List<String> values) {
            addCriterion("`phone_ext` in", values, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtNotIn(List<String> values) {
            addCriterion("`phone_ext` not in", values, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtBetween(String value1, String value2) {
            addCriterion("`phone_ext` between", value1, value2, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andPhoneExtNotBetween(String value1, String value2) {
            addCriterion("`phone_ext` not between", value1, value2, "phoneExt");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("`email` is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("`email` is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("`email` =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("`email` <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("`email` >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("`email` >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("`email` <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("`email` <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("`email` like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("`email` not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("`email` in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("`email` not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("`email` between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("`email` not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("`address` is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("`address` is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("`address` =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("`address` <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("`address` >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("`address` >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("`address` <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("`address` <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("`address` like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("`address` not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("`address` in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("`address` not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("`address` between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("`address` not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLoginRoleIsNull() {
            addCriterion("`login_role` is null");
            return (Criteria) this;
        }

        public Criteria andLoginRoleIsNotNull() {
            addCriterion("`login_role` is not null");
            return (Criteria) this;
        }

        public Criteria andLoginRoleEqualTo(String value) {
            addCriterion("`login_role` =", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleNotEqualTo(String value) {
            addCriterion("`login_role` <>", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleGreaterThan(String value) {
            addCriterion("`login_role` >", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleGreaterThanOrEqualTo(String value) {
            addCriterion("`login_role` >=", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleLessThan(String value) {
            addCriterion("`login_role` <", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleLessThanOrEqualTo(String value) {
            addCriterion("`login_role` <=", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleLike(String value) {
            addCriterion("`login_role` like", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleNotLike(String value) {
            addCriterion("`login_role` not like", value, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleIn(List<String> values) {
            addCriterion("`login_role` in", values, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleNotIn(List<String> values) {
            addCriterion("`login_role` not in", values, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleBetween(String value1, String value2) {
            addCriterion("`login_role` between", value1, value2, "loginRole");
            return (Criteria) this;
        }

        public Criteria andLoginRoleNotBetween(String value1, String value2) {
            addCriterion("`login_role` not between", value1, value2, "loginRole");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidIsNull() {
            addCriterion("`device_uuid` is null");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidIsNotNull() {
            addCriterion("`device_uuid` is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidEqualTo(String value) {
            addCriterion("`device_uuid` =", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidNotEqualTo(String value) {
            addCriterion("`device_uuid` <>", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidGreaterThan(String value) {
            addCriterion("`device_uuid` >", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidGreaterThanOrEqualTo(String value) {
            addCriterion("`device_uuid` >=", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidLessThan(String value) {
            addCriterion("`device_uuid` <", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidLessThanOrEqualTo(String value) {
            addCriterion("`device_uuid` <=", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidLike(String value) {
            addCriterion("`device_uuid` like", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidNotLike(String value) {
            addCriterion("`device_uuid` not like", value, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidIn(List<String> values) {
            addCriterion("`device_uuid` in", values, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidNotIn(List<String> values) {
            addCriterion("`device_uuid` not in", values, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidBetween(String value1, String value2) {
            addCriterion("`device_uuid` between", value1, value2, "deviceUuid");
            return (Criteria) this;
        }

        public Criteria andDeviceUuidNotBetween(String value1, String value2) {
            addCriterion("`device_uuid` not between", value1, value2, "deviceUuid");
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

        public Criteria andIsDelIsNull() {
            addCriterion("`is_del` is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("`is_del` is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Byte value) {
            addCriterion("`is_del` =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Byte value) {
            addCriterion("`is_del` <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Byte value) {
            addCriterion("`is_del` >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Byte value) {
            addCriterion("`is_del` >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Byte value) {
            addCriterion("`is_del` <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Byte value) {
            addCriterion("`is_del` <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Byte> values) {
            addCriterion("`is_del` in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Byte> values) {
            addCriterion("`is_del` not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Byte value1, Byte value2) {
            addCriterion("`is_del` between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Byte value1, Byte value2) {
            addCriterion("`is_del` not between", value1, value2, "isDel");
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

        public Criteria andAvatarIsNull() {
            addCriterion("`avatar` is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("`avatar` is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("`avatar` =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("`avatar` <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("`avatar` >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("`avatar` >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("`avatar` <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("`avatar` <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("`avatar` like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("`avatar` not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("`avatar` in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("`avatar` not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("`avatar` between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("`avatar` not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("`salt` is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("`salt` is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("`salt` =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("`salt` <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("`salt` >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("`salt` >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("`salt` <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("`salt` <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("`salt` like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("`salt` not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("`salt` in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("`salt` not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("`salt` between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("`salt` not between", value1, value2, "salt");
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