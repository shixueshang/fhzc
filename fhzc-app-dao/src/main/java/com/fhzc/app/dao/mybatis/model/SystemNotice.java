package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class SystemNotice {
    private Integer id;

    private String title;

    private Integer hasPushed;

    private String pushChannel;

    private Date publishTime;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getHasPushed() {
        return hasPushed;
    }

    public void setHasPushed(Integer hasPushed) {
        this.hasPushed = hasPushed;
    }

    public String getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(String pushChannel) {
        this.pushChannel = pushChannel == null ? null : pushChannel.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}