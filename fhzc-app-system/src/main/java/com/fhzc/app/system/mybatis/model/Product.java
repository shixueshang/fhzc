package com.fhzc.app.system.mybatis.model;

import java.util.Date;

public class Product {
    private Integer pid;

    private Byte status;

    private String code;

    private String name;

    private Date foundDay;

    private Date valueDay;

    private Date expiryDay;

    private Date issueType;

    private String productType;

    private String renewDeadline;

    private Date dividendDay;

    private String annualYield;

    private String incomeDistributionType;

    private String investmentOrientation;

    private String increaseTrust;

    private String highlights;

    private String fundManagementFee;

    private String fundSubscriptionFee;

    private String fundManager;

    private String custodian;

    private String investmentType;

    private String projectSource;

    private String issuer;

    private String isRecord;

    private String proveUrl;

    private Integer displayOrder;

    private Integer level;

    private Integer risk;

    private String cover;

    private String notice;

    private Byte scoreFactor;

    private Byte isRecommend;

    private Byte isDisplay;

    private Byte isRenew;

    private Date ctime;

    private String desc;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Date getFoundDay() {
        return foundDay;
    }

    public void setFoundDay(Date foundDay) {
        this.foundDay = foundDay;
    }

    public Date getValueDay() {
        return valueDay;
    }

    public void setValueDay(Date valueDay) {
        this.valueDay = valueDay;
    }

    public Date getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(Date expiryDay) {
        this.expiryDay = expiryDay;
    }

    public Date getIssueType() {
        return issueType;
    }

    public void setIssueType(Date issueType) {
        this.issueType = issueType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getRenewDeadline() {
        return renewDeadline;
    }

    public void setRenewDeadline(String renewDeadline) {
        this.renewDeadline = renewDeadline == null ? null : renewDeadline.trim();
    }

    public Date getDividendDay() {
        return dividendDay;
    }

    public void setDividendDay(Date dividendDay) {
        this.dividendDay = dividendDay;
    }

    public String getAnnualYield() {
        return annualYield;
    }

    public void setAnnualYield(String annualYield) {
        this.annualYield = annualYield == null ? null : annualYield.trim();
    }

    public String getIncomeDistributionType() {
        return incomeDistributionType;
    }

    public void setIncomeDistributionType(String incomeDistributionType) {
        this.incomeDistributionType = incomeDistributionType == null ? null : incomeDistributionType.trim();
    }

    public String getInvestmentOrientation() {
        return investmentOrientation;
    }

    public void setInvestmentOrientation(String investmentOrientation) {
        this.investmentOrientation = investmentOrientation == null ? null : investmentOrientation.trim();
    }

    public String getIncreaseTrust() {
        return increaseTrust;
    }

    public void setIncreaseTrust(String increaseTrust) {
        this.increaseTrust = increaseTrust == null ? null : increaseTrust.trim();
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights == null ? null : highlights.trim();
    }

    public String getFundManagementFee() {
        return fundManagementFee;
    }

    public void setFundManagementFee(String fundManagementFee) {
        this.fundManagementFee = fundManagementFee == null ? null : fundManagementFee.trim();
    }

    public String getFundSubscriptionFee() {
        return fundSubscriptionFee;
    }

    public void setFundSubscriptionFee(String fundSubscriptionFee) {
        this.fundSubscriptionFee = fundSubscriptionFee == null ? null : fundSubscriptionFee.trim();
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager == null ? null : fundManager.trim();
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian == null ? null : custodian.trim();
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType == null ? null : investmentType.trim();
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource == null ? null : projectSource.trim();
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    public String getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(String isRecord) {
        this.isRecord = isRecord == null ? null : isRecord.trim();
    }

    public String getProveUrl() {
        return proveUrl;
    }

    public void setProveUrl(String proveUrl) {
        this.proveUrl = proveUrl == null ? null : proveUrl.trim();
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public Byte getScoreFactor() {
        return scoreFactor;
    }

    public void setScoreFactor(Byte scoreFactor) {
        this.scoreFactor = scoreFactor;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Byte getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Byte isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Byte getIsRenew() {
        return isRenew;
    }

    public void setIsRenew(Byte isRenew) {
        this.isRenew = isRenew;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}