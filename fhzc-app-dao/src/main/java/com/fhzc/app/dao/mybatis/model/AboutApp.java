package com.fhzc.app.dao.mybatis.model;

public class AboutApp {
    private Integer id;

    private String version;

    private Integer isUsing;

    private String type;

    private String androidUrl;

    private String iosUrl;

    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl == null ? null : androidUrl.trim();
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl == null ? null : iosUrl.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}