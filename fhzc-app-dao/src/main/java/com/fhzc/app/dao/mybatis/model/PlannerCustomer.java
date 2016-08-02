package com.fhzc.app.dao.mybatis.model;

public class PlannerCustomer {
    private Integer id;

    private Integer plannerId;

    private Integer customerId;

    private Byte isMain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(Integer plannerId) {
        this.plannerId = plannerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Byte getIsMain() {
        return isMain;
    }

    public void setIsMain(Byte isMain) {
        this.isMain = isMain;
    }
}