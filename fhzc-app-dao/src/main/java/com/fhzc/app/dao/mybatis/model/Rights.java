package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class Rights {
    private Integer id;

    private String rightsNum;

    private Integer cid;

    private String name;

    private Integer spendScore;

    private String spendType;

    private Integer level;

    private Date ctime;

    private String supply;

    private String cover;

    private String url;

    private Integer isRecommend;

    private String notice;

    private String advanceDay;

    private String supplyPhone;

    private String summary;
    
    private Integer focusNum;

    private Integer orderNum;

    public Integer getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(Integer focusNum) {
        this.focusNum = focusNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRightsNum() {
        return rightsNum;
    }

    public void setRightsNum(String rightsNum) {
        this.rightsNum = rightsNum;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSpendScore() {
        return spendScore;
    }

    public void setSpendScore(Integer spendScore) {
        this.spendScore = spendScore;
    }

    public String getSpendType() {
        return spendType;
    }

    public void setSpendType(String spendType) {
        this.spendType = spendType == null ? null : spendType.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply == null ? null : supply.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getAdvanceDay() {
        return advanceDay;
    }

    public void setAdvanceDay(String advanceDay) {
        this.advanceDay = advanceDay == null ? null : advanceDay.trim();
    }

    public String getSupplyPhone() {
        return supplyPhone;
    }

    public void setSupplyPhone(String supplyPhone) {
        this.supplyPhone = supplyPhone == null ? null : supplyPhone.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}