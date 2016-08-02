package com.fhzc.app.dao.mybatis.model;

public class Customer {
    private Integer customerId;

    private Integer uid;

    private Integer cbId;

    private Integer levelId;

    private String risk;

    private Integer departmentId;

    private Integer bankInfoId;

    private String customerType;

    private String businessLicense;

    private String accountLicense;

    private String contactRalation;

    private String entrustedLetter;

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

    public Integer getCbId() {
        return cbId;
    }

    public void setCbId(Integer cbId) {
        this.cbId = cbId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk == null ? null : risk.trim();
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

    public String getContactRalation() {
        return contactRalation;
    }

    public void setContactRalation(String contactRalation) {
        this.contactRalation = contactRalation == null ? null : contactRalation.trim();
    }

    public String getEntrustedLetter() {
        return entrustedLetter;
    }

    public void setEntrustedLetter(String entrustedLetter) {
        this.entrustedLetter = entrustedLetter == null ? null : entrustedLetter.trim();
    }
}