package com.fhzc.app.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer pid;

    private Integer status;

    private String code;

    private String name;

    private Date foundDay;

    private String buyDay;

    private String redeemDay;

    private Date valueDay;

    private Date expiryDay;

    private Integer issueType;

    private String productType;

    private String renewDeadline;

    private String dividendDay;

    private String annualYield;

    private String incomeDistributionType;

    private String credit;

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

    private BigDecimal scoreFactor;

    private Integer isRecommend;

    private Integer isDisplay;

    private Integer isRenew;

    private Date ctime;

    private String detailUrl;

    private BigDecimal investThreshold;

    private Integer throwDepartment;

    private Date collectStart;

    private Date collectEnd;

    private String investTerm;

    private String annualInterval;

    private String desc;

    private String detailContent;
    
    private Integer focusNum;

    private Integer orderNum;

    private BigDecimal orderAmount;

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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(String buyDay) {
        this.buyDay = buyDay == null ? null : buyDay.trim();
    }

    public String getRedeemDay() {
        return redeemDay;
    }

    public void setRedeemDay(String redeemDay) {
        this.redeemDay = redeemDay == null ? null : redeemDay.trim();
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

    public Integer getIssueType() {
        return issueType;
    }

    public void setIssueType(Integer issueType) {
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

    public String getDividendDay() {
        return dividendDay;
    }

    public void setDividendDay(String dividendDay) {
        this.dividendDay = dividendDay == null ? null : dividendDay.trim();
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

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit == null ? null : credit.trim();
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

    public BigDecimal getScoreFactor() {
        return scoreFactor;
    }

    public void setScoreFactor(BigDecimal scoreFactor) {
        this.scoreFactor = scoreFactor;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getIsRenew() {
        return isRenew;
    }

    public void setIsRenew(Integer isRenew) {
        this.isRenew = isRenew;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    public BigDecimal getInvestThreshold() {
        return investThreshold;
    }

    public void setInvestThreshold(BigDecimal investThreshold) {
        this.investThreshold = investThreshold;
    }

    public Integer getThrowDepartment() {
        return throwDepartment;
    }

    public void setThrowDepartment(Integer throwDepartment) {
        this.throwDepartment = throwDepartment;
    }

    public Date getCollectStart() {
        return collectStart;
    }

    public void setCollectStart(Date collectStart) {
        this.collectStart = collectStart;
    }

    public Date getCollectEnd() {
        return collectEnd;
    }

    public void setCollectEnd(Date collectEnd) {
        this.collectEnd = collectEnd;
    }

    public String getInvestTerm() {
        return investTerm;
    }

    public void setInvestTerm(String investTerm) {
        this.investTerm = investTerm == null ? null : investTerm.trim();
    }

    public String getAnnualInterval() {
        return annualInterval;
    }

    public void setAnnualInterval(String annualInterval) {
        this.annualInterval = annualInterval == null ? null : annualInterval.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent == null ? null : detailContent.trim();
    }
}