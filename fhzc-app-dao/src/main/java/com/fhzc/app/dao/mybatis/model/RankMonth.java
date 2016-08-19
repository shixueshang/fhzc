package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class RankMonth {
    private Integer id;

    private Integer plannerId;

    private Date yearMonth;

    private Integer annualised;

    private Integer departmentId;

    private Integer rank;

    private Integer departmentRank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(Integer plannerId) {
        this.plannerId = plannerId;
    }

    public Date getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Date yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getAnnualised() {
        return annualised;
    }

    public void setAnnualised(Integer annualised) {
        this.annualised = annualised;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getDepartmentRank() {
        return departmentRank;
    }

    public void setDepartmentRank(Integer departmentRank) {
        this.departmentRank = departmentRank;
    }
}