package com.fhzc.app.system.commons.vo;

import java.util.Date;

/**
 * Created by menghq on 2016/8/13.
 */
public class FocusVo {
    long id;
    String contentName;
    String contentType;
    Date focusTime;
    String userName;
    String userType;
    String status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(Date focusTime) {
        this.focusTime = focusTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
