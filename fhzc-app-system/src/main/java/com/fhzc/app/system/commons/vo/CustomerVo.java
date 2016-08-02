package com.fhzc.app.system.commons.vo;

/**
 * Created by menghq on 2016/7/31.
 */
public class CustomerVo {
    private String name;
    private String customerLevel;
    private String availableScore;
    private long customerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getAvailableScore() {
        return availableScore;
    }

    public void setAvailableScore(String availableScore) {
        this.availableScore = availableScore;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
