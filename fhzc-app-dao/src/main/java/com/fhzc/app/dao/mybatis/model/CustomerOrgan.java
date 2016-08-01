package com.fhzc.app.dao.mybatis.model;

public class CustomerOrgan {
    private Integer id;

    private Integer customerId;

    private String rightsEnjoyPerson;

    private Integer passportTypeId;

    private String passportCode;

    private String mobile;

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

    public String getRightsEnjoyPerson() {
        return rightsEnjoyPerson;
    }

    public void setRightsEnjoyPerson(String rightsEnjoyPerson) {
        this.rightsEnjoyPerson = rightsEnjoyPerson == null ? null : rightsEnjoyPerson.trim();
    }

    public Integer getPassportTypeId() {
        return passportTypeId;
    }

    public void setPassportTypeId(Integer passportTypeId) {
        this.passportTypeId = passportTypeId;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode == null ? null : passportCode.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}