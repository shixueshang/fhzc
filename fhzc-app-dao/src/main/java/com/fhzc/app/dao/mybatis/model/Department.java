package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class Department {
    private Integer departmentId;

    private String title;

    private Integer parentDeptId;

    private Date ctime;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}