package com.fhzc.app.dao.mybatis.model;

public class Customer {
    private Integer customerId;

    private Integer uid;

    private String cbId;

    private Integer levelId;

    private Integer risk;

    private Integer departmentId;

    private Integer bankInfoId;

    private String organName;

    private String customerType;

    private String businessLicense;

    private String accountLicense;

    private String contactRelation;

    private String entrustedLetter;

    private String customerName;

    private Integer availableScore;

    private String levelName;

    private String oldPlanner;

    public String getOldPlanner() {
        return oldPlanner;
    }

    public void setOldPlanner(String oldPlanner) {
        this.oldPlanner = oldPlanner;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getAvailableScore() {
        return availableScore;
    }

    public void setAvailableScore(Integer availableScore) {
        this.availableScore = availableScore;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCbId() {
        return cbId;
    }

    public void setCbId(String cbId) {
        this.cbId = cbId == null ? null : cbId.trim();
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getBankInfoId() {
        return bankInfoId;
    }

    public void setBankInfoId(Integer bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getAccountLicense() {
        return accountLicense;
    }

    public void setAccountLicense(String accountLicense) {
        this.accountLicense = accountLicense == null ? null : accountLicense.trim();
    }

    public String getContactRelation() {
        return contactRelation;
    }

    public void setContactRelation(String contactRelation) {
        this.contactRelation = contactRelation == null ? null : contactRelation.trim();
    }

    public String getEntrustedLetter() {
        return entrustedLetter;
    }

    public void setEntrustedLetter(String entrustedLetter) {
        this.entrustedLetter = entrustedLetter == null ? null : entrustedLetter.trim();
    }
}