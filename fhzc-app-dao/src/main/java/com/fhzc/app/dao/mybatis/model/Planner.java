package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class Planner {
    private Integer id;

    private Integer uid;

    private String workNum;

    private Integer departmentId;

    private String avatar;

    private String status;

    private Integer roleId;

    private Date entryTime;

    private Date leaveTime;

    private Integer mdUid;

    private Integer subMgUid;

    private Integer mgUid;

    private Integer areaUid;

    private Integer dept1;

    private Integer dept2;

    private Integer dept3;

    private Integer dept4;

    private String jobTitleCn;

    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum == null ? null : workNum.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getMdUid() {
        return mdUid;
    }

    public void setMdUid(Integer mdUid) {
        this.mdUid = mdUid;
    }

    public Integer getSubMgUid() {
        return subMgUid;
    }

    public void setSubMgUid(Integer subMgUid) {
        this.subMgUid = subMgUid;
    }

    public Integer getMgUid() {
        return mgUid;
    }

    public void setMgUid(Integer mgUid) {
        this.mgUid = mgUid;
    }

    public Integer getAreaUid() {
        return areaUid;
    }

    public void setAreaUid(Integer areaUid) {
        this.areaUid = areaUid;
    }

    public Integer getDept1() {
        return dept1;
    }

    public void setDept1(Integer dept1) {
        this.dept1 = dept1;
    }

    public Integer getDept2() {
        return dept2;
    }

    public void setDept2(Integer dept2) {
        this.dept2 = dept2;
    }

    public Integer getDept3() {
        return dept3;
    }

    public void setDept3(Integer dept3) {
        this.dept3 = dept3;
    }

    public Integer getDept4() {
        return dept4;
    }

    public void setDept4(Integer dept4) {
        this.dept4 = dept4;
    }

    public String getJobTitleCn() {
        return jobTitleCn;
    }

    public void setJobTitleCn(String jobTitleCn) {
        this.jobTitleCn = jobTitleCn == null ? null : jobTitleCn.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
}