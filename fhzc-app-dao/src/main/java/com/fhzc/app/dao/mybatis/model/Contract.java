package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class Contract {
    private Integer id;

    private String period;

    private String serial;

    private Integer productId;

    private Integer customerId;

    private String customerName;

    private Integer plannerId;

    private Date buyTime;

    private Integer amountUsd;

    private Integer amountRmb;

    private Integer annualised;

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

    private Date ctime;

    private String earningRate;

    private String distributeEarning;

    private String payment;

    private Byte hasPassport;
    
    private String plannerName;
    
    private String productName;

    public String getPlannerName() {
		return plannerName;
	}

	public void setPlannerName(String plannerName) {
		this.plannerName = plannerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
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

    public Byte getHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(Byte hasPassport) {
        this.hasPassport = hasPassport;
    }
}