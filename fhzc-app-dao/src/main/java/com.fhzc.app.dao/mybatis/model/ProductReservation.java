package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class ProductReservation {
    private Integer id;

    private Integer productId;

    private Integer customerId;

    private Integer plannerId;

    private Date ctime;

    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(Integer plannerId) {
        this.plannerId = plannerId;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}