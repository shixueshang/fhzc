package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class PushToken {
    private Integer id;

    private Integer userId;

    private String deviceType;

    private String deviceToken;

    private Integer allowPush;

    private Integer allowSound;

    private Date bindDate;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public Integer getAllowPush() {
        return allowPush;
    }

    public void setAllowPush(Integer allowPush) {
        this.allowPush = allowPush;
    }

    public Integer getAllowSound() {
        return allowSound;
    }

    public void setAllowSound(Integer allowSound) {
        this.allowSound = allowSound;
    }

    public Date getBindDate() {
        return bindDate;
    }

    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}