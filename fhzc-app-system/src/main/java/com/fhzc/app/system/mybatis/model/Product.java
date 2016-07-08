package com.fhzc.app.system.mybatis.model;

public class Product {
    private Integer pid;

    private String code;

    private String name;

    private String status;

    private Integer valueDay;

    private Integer expiryDay;

    private Integer dividendDay;

    private Integer ctime;

    private Integer sort;

    private Integer level;

    private Integer risk;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getValueDay() {
        return valueDay;
    }

    public void setValueDay(Integer valueDay) {
        this.valueDay = valueDay;
    }

    public Integer getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(Integer expiryDay) {
        this.expiryDay = expiryDay;
    }

    public Integer getDividendDay() {
        return dividendDay;
    }

    public void setDividendDay(Integer dividendDay) {
        this.dividendDay = dividendDay;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
    }
}