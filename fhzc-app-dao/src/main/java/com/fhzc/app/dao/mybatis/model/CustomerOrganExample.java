package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrganExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerOrganExample() {
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

        public Criteria andRightsEnjoyPersonIsNull() {
            addCriterion("`rights_enjoy_person` is null");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonIsNotNull() {
            addCriterion("`rights_enjoy_person` is not null");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonEqualTo(String value) {
            addCriterion("`rights_enjoy_person` =", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonNotEqualTo(String value) {
            addCriterion("`rights_enjoy_person` <>", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonGreaterThan(String value) {
            addCriterion("`rights_enjoy_person` >", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonGreaterThanOrEqualTo(String value) {
            addCriterion("`rights_enjoy_person` >=", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonLessThan(String value) {
            addCriterion("`rights_enjoy_person` <", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonLessThanOrEqualTo(String value) {
            addCriterion("`rights_enjoy_person` <=", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonLike(String value) {
            addCriterion("`rights_enjoy_person` like", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonNotLike(String value) {
            addCriterion("`rights_enjoy_person` not like", value, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonIn(List<String> values) {
            addCriterion("`rights_enjoy_person` in", values, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonNotIn(List<String> values) {
            addCriterion("`rights_enjoy_person` not in", values, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonBetween(String value1, String value2) {
            addCriterion("`rights_enjoy_person` between", value1, value2, "rightsEnjoyPerson");
            return (Criteria) this;
        }

        public Criteria andRightsEnjoyPersonNotBetween(String value1, String value2) {
            addCriterion("`rights_enjoy_person` not between", value1, value2, "rightsEnjoyPerson");
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