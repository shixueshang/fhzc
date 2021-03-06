package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class AboutAppExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AboutAppExample() {
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

        public Criteria andVersionIsNull() {
            addCriterion("`version` is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("`version` is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("`version` =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("`version` <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("`version` >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("`version` >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("`version` <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("`version` <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("`version` like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("`version` not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("`version` in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("`version` not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("`version` between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("`version` not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andIsUsingIsNull() {
            addCriterion("`is_using` is null");
            return (Criteria) this;
        }

        public Criteria andIsUsingIsNotNull() {
            addCriterion("`is_using` is not null");
            return (Criteria) this;
        }

        public Criteria andIsUsingEqualTo(Integer value) {
            addCriterion("`is_using` =", value, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingNotEqualTo(Integer value) {
            addCriterion("`is_using` <>", value, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingGreaterThan(Integer value) {
            addCriterion("`is_using` >", value, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingGreaterThanOrEqualTo(Integer value) {
            addCriterion("`is_using` >=", value, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingLessThan(Integer value) {
            addCriterion("`is_using` <", value, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingLessThanOrEqualTo(Integer value) {
            addCriterion("`is_using` <=", value, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingIn(List<Integer> values) {
            addCriterion("`is_using` in", values, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingNotIn(List<Integer> values) {
            addCriterion("`is_using` not in", values, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingBetween(Integer value1, Integer value2) {
            addCriterion("`is_using` between", value1, value2, "isUsing");
            return (Criteria) this;
        }

        public Criteria andIsUsingNotBetween(Integer value1, Integer value2) {
            addCriterion("`is_using` not between", value1, value2, "isUsing");
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

        public Criteria andAndroidUrlIsNull() {
            addCriterion("`android_url` is null");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlIsNotNull() {
            addCriterion("`android_url` is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlEqualTo(String value) {
            addCriterion("`android_url` =", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlNotEqualTo(String value) {
            addCriterion("`android_url` <>", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlGreaterThan(String value) {
            addCriterion("`android_url` >", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`android_url` >=", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlLessThan(String value) {
            addCriterion("`android_url` <", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlLessThanOrEqualTo(String value) {
            addCriterion("`android_url` <=", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlLike(String value) {
            addCriterion("`android_url` like", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlNotLike(String value) {
            addCriterion("`android_url` not like", value, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlIn(List<String> values) {
            addCriterion("`android_url` in", values, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlNotIn(List<String> values) {
            addCriterion("`android_url` not in", values, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlBetween(String value1, String value2) {
            addCriterion("`android_url` between", value1, value2, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andAndroidUrlNotBetween(String value1, String value2) {
            addCriterion("`android_url` not between", value1, value2, "androidUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlIsNull() {
            addCriterion("`ios_url` is null");
            return (Criteria) this;
        }

        public Criteria andIosUrlIsNotNull() {
            addCriterion("`ios_url` is not null");
            return (Criteria) this;
        }

        public Criteria andIosUrlEqualTo(String value) {
            addCriterion("`ios_url` =", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlNotEqualTo(String value) {
            addCriterion("`ios_url` <>", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlGreaterThan(String value) {
            addCriterion("`ios_url` >", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`ios_url` >=", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlLessThan(String value) {
            addCriterion("`ios_url` <", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlLessThanOrEqualTo(String value) {
            addCriterion("`ios_url` <=", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlLike(String value) {
            addCriterion("`ios_url` like", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlNotLike(String value) {
            addCriterion("`ios_url` not like", value, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlIn(List<String> values) {
            addCriterion("`ios_url` in", values, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlNotIn(List<String> values) {
            addCriterion("`ios_url` not in", values, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlBetween(String value1, String value2) {
            addCriterion("`ios_url` between", value1, value2, "iosUrl");
            return (Criteria) this;
        }

        public Criteria andIosUrlNotBetween(String value1, String value2) {
            addCriterion("`ios_url` not between", value1, value2, "iosUrl");
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