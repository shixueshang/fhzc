package com.fhzc.app.dao.mybatis.bo;

/**
 * Created by menghq on 2016/7/26.
 */
public class ProductReservationBo {
    long id;
    long productId;
    String reservationTime;
    String productName;
    String reservationAmount;
    String clientId;
    String clientName;
    String planerId;
    String planerName;
    String planerPhone;
    String reservationStatus;
    String salt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(String reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPlanerId() {
        return planerId;
    }

    public void setPlanerId(String planerId) {
        this.planerId = planerId;
    }

    public String getPlanerName() {
        return planerName;
    }

    public void setPlanerName(String planerName) {
        this.planerName = planerName;
    }

    public String getPlanerPhone() {
        return planerPhone;
    }

    public void setPlanerPhone(String planerPhone) {
        this.planerPhone = planerPhone;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
