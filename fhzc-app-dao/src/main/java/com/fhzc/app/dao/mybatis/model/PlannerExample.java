package com.fhzc.app.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PlannerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlannerExample() {
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

        public Criteria andWorkNumIsNull() {
            addCriterion("`work_num` is null");
            return (Criteria) this;
        }

        public Criteria andWorkNumIsNotNull() {
            addCriterion("`work_num` is not null");
            return (Criteria) this;
        }

        public Criteria andWorkNumEqualTo(String value) {
            addCriterion("`work_num` =", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumNotEqualTo(String value) {
            addCriterion("`work_num` <>", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumGreaterThan(String value) {
            addCriterion("`work_num` >", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumGreaterThanOrEqualTo(String value) {
            addCriterion("`work_num` >=", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumLessThan(String value) {
            addCriterion("`work_num` <", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumLessThanOrEqualTo(String value) {
            addCriterion("`work_num` <=", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumLike(String value) {
            addCriterion("`work_num` like", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumNotLike(String value) {
            addCriterion("`work_num` not like", value, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumIn(List<String> values) {
            addCriterion("`work_num` in", values, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumNotIn(List<String> values) {
            addCriterion("`work_num` not in", values, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumBetween(String value1, String value2) {
            addCriterion("`work_num` between", value1, value2, "workNum");
            return (Criteria) this;
        }

        public Criteria andWorkNumNotBetween(String value1, String value2) {
            addCriterion("`work_num` not between", value1, value2, "workNum");
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

        public Criteria andRoleIdIsNull() {
            addCriterion("`role_id` is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("`role_id` is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("`role_id` =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("`role_id` <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("`role_id` >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`role_id` >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("`role_id` <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("`role_id` <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("`role_id` in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("`role_id` not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("`role_id` between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`role_id` not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andEntryTimeIsNull() {
            addCriterion("`entry_time` is null");
            return (Criteria) this;
        }

        public Criteria andEntryTimeIsNotNull() {
            addCriterion("`entry_time` is not null");
            return (Criteria) this;
        }

        public Criteria andEntryTimeEqualTo(Date value) {
            addCriterionForJDBCDate("`entry_time` =", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("`entry_time` <>", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("`entry_time` >", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`entry_time` >=", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeLessThan(Date value) {
            addCriterionForJDBCDate("`entry_time` <", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`entry_time` <=", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeIn(List<Date> values) {
            addCriterionForJDBCDate("`entry_time` in", values, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("`entry_time` not in", values, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`entry_time` between", value1, value2, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`entry_time` not between", value1, value2, "entryTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIsNull() {
            addCriterion("`leave_time` is null");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIsNotNull() {
            addCriterion("`leave_time` is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeEqualTo(Date value) {
            addCriterionForJDBCDate("`leave_time` =", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("`leave_time` <>", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("`leave_time` >", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`leave_time` >=", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLessThan(Date value) {
            addCriterionForJDBCDate("`leave_time` <", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`leave_time` <=", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIn(List<Date> values) {
            addCriterionForJDBCDate("`leave_time` in", values, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("`leave_time` not in", values, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`leave_time` between", value1, value2, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`leave_time` not between", value1, value2, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andMdUidIsNull() {
            addCriterion("`md_uid` is null");
            return (Criteria) this;
        }

        public Criteria andMdUidIsNotNull() {
            addCriterion("`md_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andMdUidEqualTo(Integer value) {
            addCriterion("`md_uid` =", value, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidNotEqualTo(Integer value) {
            addCriterion("`md_uid` <>", value, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidGreaterThan(Integer value) {
            addCriterion("`md_uid` >", value, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`md_uid` >=", value, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidLessThan(Integer value) {
            addCriterion("`md_uid` <", value, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidLessThanOrEqualTo(Integer value) {
            addCriterion("`md_uid` <=", value, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidIn(List<Integer> values) {
            addCriterion("`md_uid` in", values, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidNotIn(List<Integer> values) {
            addCriterion("`md_uid` not in", values, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidBetween(Integer value1, Integer value2) {
            addCriterion("`md_uid` between", value1, value2, "mdUid");
            return (Criteria) this;
        }

        public Criteria andMdUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`md_uid` not between", value1, value2, "mdUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidIsNull() {
            addCriterion("`sub_mg_uid` is null");
            return (Criteria) this;
        }

        public Criteria andSubMgUidIsNotNull() {
            addCriterion("`sub_mg_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andSubMgUidEqualTo(Integer value) {
            addCriterion("`sub_mg_uid` =", value, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidNotEqualTo(Integer value) {
            addCriterion("`sub_mg_uid` <>", value, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidGreaterThan(Integer value) {
            addCriterion("`sub_mg_uid` >", value, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`sub_mg_uid` >=", value, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidLessThan(Integer value) {
            addCriterion("`sub_mg_uid` <", value, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidLessThanOrEqualTo(Integer value) {
            addCriterion("`sub_mg_uid` <=", value, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidIn(List<Integer> values) {
            addCriterion("`sub_mg_uid` in", values, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidNotIn(List<Integer> values) {
            addCriterion("`sub_mg_uid` not in", values, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidBetween(Integer value1, Integer value2) {
            addCriterion("`sub_mg_uid` between", value1, value2, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andSubMgUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`sub_mg_uid` not between", value1, value2, "subMgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidIsNull() {
            addCriterion("`mg_uid` is null");
            return (Criteria) this;
        }

        public Criteria andMgUidIsNotNull() {
            addCriterion("`mg_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andMgUidEqualTo(Integer value) {
            addCriterion("`mg_uid` =", value, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidNotEqualTo(Integer value) {
            addCriterion("`mg_uid` <>", value, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidGreaterThan(Integer value) {
            addCriterion("`mg_uid` >", value, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`mg_uid` >=", value, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidLessThan(Integer value) {
            addCriterion("`mg_uid` <", value, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidLessThanOrEqualTo(Integer value) {
            addCriterion("`mg_uid` <=", value, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidIn(List<Integer> values) {
            addCriterion("`mg_uid` in", values, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidNotIn(List<Integer> values) {
            addCriterion("`mg_uid` not in", values, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidBetween(Integer value1, Integer value2) {
            addCriterion("`mg_uid` between", value1, value2, "mgUid");
            return (Criteria) this;
        }

        public Criteria andMgUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`mg_uid` not between", value1, value2, "mgUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidIsNull() {
            addCriterion("`area_uid` is null");
            return (Criteria) this;
        }

        public Criteria andAreaUidIsNotNull() {
            addCriterion("`area_uid` is not null");
            return (Criteria) this;
        }

        public Criteria andAreaUidEqualTo(Integer value) {
            addCriterion("`area_uid` =", value, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidNotEqualTo(Integer value) {
            addCriterion("`area_uid` <>", value, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidGreaterThan(Integer value) {
            addCriterion("`area_uid` >", value, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`area_uid` >=", value, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidLessThan(Integer value) {
            addCriterion("`area_uid` <", value, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidLessThanOrEqualTo(Integer value) {
            addCriterion("`area_uid` <=", value, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidIn(List<Integer> values) {
            addCriterion("`area_uid` in", values, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidNotIn(List<Integer> values) {
            addCriterion("`area_uid` not in", values, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidBetween(Integer value1, Integer value2) {
            addCriterion("`area_uid` between", value1, value2, "areaUid");
            return (Criteria) this;
        }

        public Criteria andAreaUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`area_uid` not between", value1, value2, "areaUid");
            return (Criteria) this;
        }

        public Criteria andDept1IsNull() {
            addCriterion("`dept_1` is null");
            return (Criteria) this;
        }

        public Criteria andDept1IsNotNull() {
            addCriterion("`dept_1` is not null");
            return (Criteria) this;
        }

        public Criteria andDept1EqualTo(Integer value) {
            addCriterion("`dept_1` =", value, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1NotEqualTo(Integer value) {
            addCriterion("`dept_1` <>", value, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1GreaterThan(Integer value) {
            addCriterion("`dept_1` >", value, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1GreaterThanOrEqualTo(Integer value) {
            addCriterion("`dept_1` >=", value, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1LessThan(Integer value) {
            addCriterion("`dept_1` <", value, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1LessThanOrEqualTo(Integer value) {
            addCriterion("`dept_1` <=", value, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1In(List<Integer> values) {
            addCriterion("`dept_1` in", values, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1NotIn(List<Integer> values) {
            addCriterion("`dept_1` not in", values, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1Between(Integer value1, Integer value2) {
            addCriterion("`dept_1` between", value1, value2, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept1NotBetween(Integer value1, Integer value2) {
            addCriterion("`dept_1` not between", value1, value2, "dept1");
            return (Criteria) this;
        }

        public Criteria andDept2IsNull() {
            addCriterion("`dept_2` is null");
            return (Criteria) this;
        }

        public Criteria andDept2IsNotNull() {
            addCriterion("`dept_2` is not null");
            return (Criteria) this;
        }

        public Criteria andDept2EqualTo(Integer value) {
            addCriterion("`dept_2` =", value, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2NotEqualTo(Integer value) {
            addCriterion("`dept_2` <>", value, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2GreaterThan(Integer value) {
            addCriterion("`dept_2` >", value, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2GreaterThanOrEqualTo(Integer value) {
            addCriterion("`dept_2` >=", value, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2LessThan(Integer value) {
            addCriterion("`dept_2` <", value, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2LessThanOrEqualTo(Integer value) {
            addCriterion("`dept_2` <=", value, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2In(List<Integer> values) {
            addCriterion("`dept_2` in", values, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2NotIn(List<Integer> values) {
            addCriterion("`dept_2` not in", values, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2Between(Integer value1, Integer value2) {
            addCriterion("`dept_2` between", value1, value2, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept2NotBetween(Integer value1, Integer value2) {
            addCriterion("`dept_2` not between", value1, value2, "dept2");
            return (Criteria) this;
        }

        public Criteria andDept3IsNull() {
            addCriterion("`dept_3` is null");
            return (Criteria) this;
        }

        public Criteria andDept3IsNotNull() {
            addCriterion("`dept_3` is not null");
            return (Criteria) this;
        }

        public Criteria andDept3EqualTo(Integer value) {
            addCriterion("`dept_3` =", value, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3NotEqualTo(Integer value) {
            addCriterion("`dept_3` <>", value, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3GreaterThan(Integer value) {
            addCriterion("`dept_3` >", value, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3GreaterThanOrEqualTo(Integer value) {
            addCriterion("`dept_3` >=", value, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3LessThan(Integer value) {
            addCriterion("`dept_3` <", value, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3LessThanOrEqualTo(Integer value) {
            addCriterion("`dept_3` <=", value, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3In(List<Integer> values) {
            addCriterion("`dept_3` in", values, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3NotIn(List<Integer> values) {
            addCriterion("`dept_3` not in", values, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3Between(Integer value1, Integer value2) {
            addCriterion("`dept_3` between", value1, value2, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept3NotBetween(Integer value1, Integer value2) {
            addCriterion("`dept_3` not between", value1, value2, "dept3");
            return (Criteria) this;
        }

        public Criteria andDept4IsNull() {
            addCriterion("`dept_4` is null");
            return (Criteria) this;
        }

        public Criteria andDept4IsNotNull() {
            addCriterion("`dept_4` is not null");
            return (Criteria) this;
        }

        public Criteria andDept4EqualTo(Integer value) {
            addCriterion("`dept_4` =", value, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4NotEqualTo(Integer value) {
            addCriterion("`dept_4` <>", value, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4GreaterThan(Integer value) {
            addCriterion("`dept_4` >", value, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4GreaterThanOrEqualTo(Integer value) {
            addCriterion("`dept_4` >=", value, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4LessThan(Integer value) {
            addCriterion("`dept_4` <", value, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4LessThanOrEqualTo(Integer value) {
            addCriterion("`dept_4` <=", value, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4In(List<Integer> values) {
            addCriterion("`dept_4` in", values, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4NotIn(List<Integer> values) {
            addCriterion("`dept_4` not in", values, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4Between(Integer value1, Integer value2) {
            addCriterion("`dept_4` between", value1, value2, "dept4");
            return (Criteria) this;
        }

        public Criteria andDept4NotBetween(Integer value1, Integer value2) {
            addCriterion("`dept_4` not between", value1, value2, "dept4");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnIsNull() {
            addCriterion("`job_title_cn` is null");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnIsNotNull() {
            addCriterion("`job_title_cn` is not null");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnEqualTo(String value) {
            addCriterion("`job_title_cn` =", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnNotEqualTo(String value) {
            addCriterion("`job_title_cn` <>", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnGreaterThan(String value) {
            addCriterion("`job_title_cn` >", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnGreaterThanOrEqualTo(String value) {
            addCriterion("`job_title_cn` >=", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnLessThan(String value) {
            addCriterion("`job_title_cn` <", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnLessThanOrEqualTo(String value) {
            addCriterion("`job_title_cn` <=", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnLike(String value) {
            addCriterion("`job_title_cn` like", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnNotLike(String value) {
            addCriterion("`job_title_cn` not like", value, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnIn(List<String> values) {
            addCriterion("`job_title_cn` in", values, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnNotIn(List<String> values) {
            addCriterion("`job_title_cn` not in", values, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnBetween(String value1, String value2) {
            addCriterion("`job_title_cn` between", value1, value2, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andJobTitleCnNotBetween(String value1, String value2) {
            addCriterion("`job_title_cn` not between", value1, value2, "jobTitleCn");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("`position` is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("`position` is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("`position` =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("`position` <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("`position` >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("`position` >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("`position` <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("`position` <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("`position` like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("`position` not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("`position` in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("`position` not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("`position` between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("`position` not between", value1, value2, "position");
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