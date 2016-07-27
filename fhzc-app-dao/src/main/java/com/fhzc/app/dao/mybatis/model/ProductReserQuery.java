package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

/**
 * Created by menghq on 2016/7/26.
 */
public class ProductReserQuery {
    private long productId;
    private String productName;
    private String customerIdCardNum;
    private Date startDate;
    private Date endDate;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerIdCardNum() {
        return customerIdCardNum;
    }

    public void setCustomerIdCardNum(String customerIdCardNum) {
        this.customerIdCardNum = customerIdCardNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
