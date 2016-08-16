package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class PlannerAchivementsMonthly {
    private Integer id;

    private Integer plannerUid;

    private String plannerPercent;

    private Integer managerUid;

    private String mannagerPercent;

    private Integer productId;

    private String productType;

    private Integer customerUid;

    private String customerName;

    private Integer customerBuy;

    private Integer annualised;

    private Integer productCycle;

    private Date transferDate;

    private String memo;

    private Date ctime;

    private Integer areaId;

    private Integer company;

    private Integer team;

    private Integer departmentId;

    private Integer area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlannerUid() {
        return plannerUid;
    }

    public void setPlannerUid(Integer plannerUid) {
        this.plannerUid = plannerUid;
    }

    public String getPlannerPercent() {
        return plannerPercent;
    }

    public void setPlannerPercent(String plannerPercent) {
        this.plannerPercent = plannerPercent == null ? null : plannerPercent.trim();
    }

    public Integer getManagerUid() {
        return managerUid;
    }

    public void setManagerUid(Integer managerUid) {
        this.managerUid = managerUid;
    }

    public String getMannagerPercent() {
        return mannagerPercent;
    }

    public void setMannagerPercent(String mannagerPercent) {
        this.mannagerPercent = mannagerPercent == null ? null : mannagerPercent.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(Integer customerUid) {
        this.customerUid = customerUid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getCustomerBuy() {
        return customerBuy;
    }

    public void setCustomerBuy(Integer customerBuy) {
        this.customerBuy = customerBuy;
    }

    public Integer getAnnualised() {
        return annualised;
    }

    public void setAnnualised(Integer annualised) {
        this.annualised = annualised;
    }

    public Integer getProductCycle() {
        return productCycle;
    }

    public void setProductCycle(Integer productCycle) {
        this.productCycle = productCycle;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}