package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class AssetsHistory {
    private Integer id;

    private Integer customerId;

    private Integer productId;

    private String type;

    private Integer amount;

    private Date ctime;

    private Date deadDate;

    private Date paymentDate;

    private String serial;

    private String customerName;

    private Integer plannerId;

    private Date buyTime;

    private Integer amountUsd;

    private Integer amountRmb;

    private Integer annualised;

    private String period;

    private Byte invaild;

    private Date productFoundDay;

    private Date valueDate;

    private Date productExpireDay;

    private Date expireDay;

    private String bank;

    private String bankAccount;

    private String lot;

    private Integer durationMonth;

    private Integer durationDay;

    private String pubAgent;

    private String branchAgent;

    private Byte isMember;

    private String memo;

    private String earningRate;

    private String distributeEarning;

    private String payment;

    private String productName;

    private String productCode;

    private String planner;

    private String customerNum;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getPlanner() {
        return planner;
    }

    public void setPlanner(String planner) {
        this.planner = planner;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(Integer plannerId) {
        this.plannerId = plannerId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Integer getAmountUsd() {
        return amountUsd;
    }

    public void setAmountUsd(Integer amountUsd) {
        this.amountUsd = amountUsd;
    }

    public Integer getAmountRmb() {
        return amountRmb;
    }

    public void setAmountRmb(Integer amountRmb) {
        this.amountRmb = amountRmb;
    }

    public Integer getAnnualised() {
        return annualised;
    }

    public void setAnnualised(Integer annualised) {
        this.annualised = annualised;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public Byte getInvaild() {
        return invaild;
    }

    public void setInvaild(Byte invaild) {
        this.invaild = invaild;
    }

    public Date getProductFoundDay() {
        return productFoundDay;
    }

    public void setProductFoundDay(Date productFoundDay) {
        this.productFoundDay = productFoundDay;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getProductExpireDay() {
        return productExpireDay;
    }

    public void setProductExpireDay(Date productExpireDay) {
        this.productExpireDay = productExpireDay;
    }

    public Date getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Date expireDay) {
        this.expireDay = expireDay;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot == null ? null : lot.trim();
    }

    public Integer getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(Integer durationMonth) {
        this.durationMonth = durationMonth;
    }

    public Integer getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(Integer durationDay) {
        this.durationDay = durationDay;
    }

    public String getPubAgent() {
        return pubAgent;
    }

    public void setPubAgent(String pubAgent) {
        this.pubAgent = pubAgent == null ? null : pubAgent.trim();
    }

    public String getBranchAgent() {
        return branchAgent;
    }

    public void setBranchAgent(String branchAgent) {
        this.branchAgent = branchAgent == null ? null : branchAgent.trim();
    }

    public Byte getIsMember() {
        return isMember;
    }

    public void setIsMember(Byte isMember) {
        this.isMember = isMember;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getEarningRate() {
        return earningRate;
    }

    public void setEarningRate(String earningRate) {
        this.earningRate = earningRate == null ? null : earningRate.trim();
    }

    public String getDistributeEarning() {
        return distributeEarning;
    }

    public void setDistributeEarning(String distributeEarning) {
        this.distributeEarning = distributeEarning == null ? null : distributeEarning.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }
}