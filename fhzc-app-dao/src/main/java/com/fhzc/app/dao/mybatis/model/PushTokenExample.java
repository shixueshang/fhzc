package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushTokenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PushTokenExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("`user_id` is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("`user_id` is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("`user_id` =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("`user_id` <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("`user_id` >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`user_id` >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("`user_id` <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("`user_id` <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("`user_id` in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("`user_id` not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("`user_id` between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`user_id` not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("`device_type` is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("`device_type` is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(String value) {
            addCriterion("`device_type` =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(String value) {
            addCriterion("`device_type` <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(String value) {
            addCriterion("`device_type` >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`device_type` >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(String value) {
            addCriterion("`device_type` <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(String value) {
            addCriterion("`device_type` <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLike(String value) {
            addCriterion("`device_type` like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotLike(String value) {
            addCriterion("`device_type` not like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<String> values) {
            addCriterion("`device_type` in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<String> values) {
            addCriterion("`device_type` not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(String value1, String value2) {
            addCriterion("`device_type` between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(String value1, String value2) {
            addCriterion("`device_type` not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenIsNull() {
            addCriterion("`device_token` is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenIsNotNull() {
            addCriterion("`device_token` is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenEqualTo(String value) {
            addCriterion("`device_token` =", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenNotEqualTo(String value) {
            addCriterion("`device_token` <>", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenGreaterThan(String value) {
            addCriterion("`device_token` >", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenGreaterThanOrEqualTo(String value) {
            addCriterion("`device_token` >=", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenLessThan(String value) {
            addCriterion("`device_token` <", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenLessThanOrEqualTo(String value) {
            addCriterion("`device_token` <=", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenLike(String value) {
            addCriterion("`device_token` like", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenNotLike(String value) {
            addCriterion("`device_token` not like", value, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenIn(List<String> values) {
            addCriterion("`device_token` in", values, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenNotIn(List<String> values) {
            addCriterion("`device_token` not in", values, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenBetween(String value1, String value2) {
            addCriterion("`device_token` between", value1, value2, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andDeviceTokenNotBetween(String value1, String value2) {
            addCriterion("`device_token` not between", value1, value2, "deviceToken");
            return (Criteria) this;
        }

        public Criteria andAllowPushIsNull() {
            addCriterion("`allow_push` is null");
            return (Criteria) this;
        }

        public Criteria andAllowPushIsNotNull() {
            addCriterion("`allow_push` is not null");
            return (Criteria) this;
        }

        public Criteria andAllowPushEqualTo(Integer value) {
            addCriterion("`allow_push` =", value, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushNotEqualTo(Integer value) {
            addCriterion("`allow_push` <>", value, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushGreaterThan(Integer value) {
            addCriterion("`allow_push` >", value, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushGreaterThanOrEqualTo(Integer value) {
            addCriterion("`allow_push` >=", value, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushLessThan(Integer value) {
            addCriterion("`allow_push` <", value, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushLessThanOrEqualTo(Integer value) {
            addCriterion("`allow_push` <=", value, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushIn(List<Integer> values) {
            addCriterion("`allow_push` in", values, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushNotIn(List<Integer> values) {
            addCriterion("`allow_push` not in", values, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushBetween(Integer value1, Integer value2) {
            addCriterion("`allow_push` between", value1, value2, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowPushNotBetween(Integer value1, Integer value2) {
            addCriterion("`allow_push` not between", value1, value2, "allowPush");
            return (Criteria) this;
        }

        public Criteria andAllowSoundIsNull() {
            addCriterion("`allow_sound` is null");
            return (Criteria) this;
        }

        public Criteria andAllowSoundIsNotNull() {
            addCriterion("`allow_sound` is not null");
            return (Criteria) this;
        }

        public Criteria andAllowSoundEqualTo(Integer value) {
            addCriterion("`allow_sound` =", value, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundNotEqualTo(Integer value) {
            addCriterion("`allow_sound` <>", value, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundGreaterThan(Integer value) {
            addCriterion("`allow_sound` >", value, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundGreaterThanOrEqualTo(Integer value) {
            addCriterion("`allow_sound` >=", value, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundLessThan(Integer value) {
            addCriterion("`allow_sound` <", value, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundLessThanOrEqualTo(Integer value) {
            addCriterion("`allow_sound` <=", value, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundIn(List<Integer> values) {
            addCriterion("`allow_sound` in", values, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundNotIn(List<Integer> values) {
            addCriterion("`allow_sound` not in", values, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundBetween(Integer value1, Integer value2) {
            addCriterion("`allow_sound` between", value1, value2, "allowSound");
            return (Criteria) this;
        }

        public Criteria andAllowSoundNotBetween(Integer value1, Integer value2) {
            addCriterion("`allow_sound` not between", value1, value2, "allowSound");
            return (Criteria) this;
        }

        public Criteria andBindDateIsNull() {
            addCriterion("`bind_date` is null");
            return (Criteria) this;
        }

        public Criteria andBindDateIsNotNull() {
            addCriterion("`bind_date` is not null");
            return (Criteria) this;
        }

        public Criteria andBindDateEqualTo(Date value) {
            addCriterion("`bind_date` =", value, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateNotEqualTo(Date value) {
            addCriterion("`bind_date` <>", value, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateGreaterThan(Date value) {
            addCriterion("`bind_date` >", value, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`bind_date` >=", value, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateLessThan(Date value) {
            addCriterion("`bind_date` <", value, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateLessThanOrEqualTo(Date value) {
            addCriterion("`bind_date` <=", value, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateIn(List<Date> values) {
            addCriterion("`bind_date` in", values, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateNotIn(List<Date> values) {
            addCriterion("`bind_date` not in", values, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateBetween(Date value1, Date value2) {
            addCriterion("`bind_date` between", value1, value2, "bindDate");
            return (Criteria) this;
        }

        public Criteria andBindDateNotBetween(Date value1, Date value2) {
            addCriterion("`bind_date` not between", value1, value2, "bindDate");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("`is_delete` is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("`is_delete` is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("`is_delete` =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("`is_delete` <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("`is_delete` >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("`is_delete` >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("`is_delete` <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("`is_delete` <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("`is_delete` in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("`is_delete` not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("`is_delete` between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("`is_delete` not between", value1, value2, "isDelete");
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