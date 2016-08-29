package com.fhzc.app.dao.mybatis.model;

/**
 * Created by lihongde on 2016/8/26 15:33
 */
public class CustomerScore {

    private Integer customerId;

    private String customerName;

    private Integer avaliableScore;

    private Integer frozeScore;

    private Integer willExpireScore;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getAvaliableScore() {
        return avaliableScore;
    }

    public void setAvaliableScore(Integer avaliableScore) {
        this.avaliableScore = avaliableScore;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getFrozeScore() {
        return frozeScore;
    }

    public void setFrozeScore(Integer frozeScore) {
        this.frozeScore = frozeScore;
    }

    public Integer getWillExpireScore() {
        return willExpireScore;
    }

    public void setWillExpireScore(Integer willExpireScore) {
        this.willExpireScore = willExpireScore;
    }
}
