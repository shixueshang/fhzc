package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class RightsReservation {
    private Integer id;

    private Integer customerId;

    private Integer plannerId;

    private Date markDate;

    private String detail;

    private Integer scoreCost;

    private Integer status;

    private String memo;

    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(Integer plannerId) {
        this.plannerId = plannerId;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getScoreCost() {
        return scoreCost;
    }

    public void setScoreCost(Integer scoreCost) {
        this.scoreCost = scoreCost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}