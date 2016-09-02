package com.fhzc.app.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lihongde on 2016/8/28 15:43.
 */
public class CurrentHistoryHoldings {

    private String productName;

    private BigDecimal amount;

    private Date foundDay;

    private String dividendDay;

    private String buyDay;

    private Date paymentDay;

    private String serial;

    private String bank;

    private String bankAccount;

    private String lot;

    private String investTerm;

    private String earningRate;

    private BigDecimal totalAmount;
    
    private String payment;

    public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getFoundDay() {
        return foundDay;
    }

    public void setFoundDay(Date foundDay) {
        this.foundDay = foundDay;
    }

    public String getDividendDay() {
        return dividendDay;
    }

    public void setDividendDay(String dividendDay) {
        this.dividendDay = dividendDay;
    }

    public String getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(String buyDay) {
        this.buyDay = buyDay;
    }

    public Date getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getInvestTerm() {
        return investTerm;
    }

    public void setInvestTerm(String investTerm) {
        this.investTerm = investTerm;
    }

    public String getEarningRate() {
        return earningRate;
    }

    public void setEarningRate(String earningRate) {
        this.earningRate = earningRate;
    }
}
