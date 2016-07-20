package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

public class User {
    private Integer uid;

    private String login;

    private String password;

    private String realname;

    private String gender;

    private Date birthday;

    private Integer passportTypeId;

    private String passportCode;

    private String passportAgent;

    private String passportAddress;

    private String passportExpire;

    private String mobile;

    private String phone;

    private String phoneExt;

    private String email;

    private String address;

    private String loginRole;

    private String deviceUuid;

    private Integer areaId;

    private Byte isDel;

    private Date ctime;

    private String avatar;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPassportTypeId() {
        return passportTypeId;
    }

    public void setPassportTypeId(Integer passportTypeId) {
        this.passportTypeId = passportTypeId;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode == null ? null : passportCode.trim();
    }

    public String getPassportAgent() {
        return passportAgent;
    }

    public void setPassportAgent(String passportAgent) {
        this.passportAgent = passportAgent == null ? null : passportAgent.trim();
    }

    public String getPassportAddress() {
        return passportAddress;
    }

    public void setPassportAddress(String passportAddress) {
        this.passportAddress = passportAddress == null ? null : passportAddress.trim();
    }

    public String getPassportExpire() {
        return passportExpire;
    }

    public void setPassportExpire(String passportExpire) {
        this.passportExpire = passportExpire == null ? null : passportExpire.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt == null ? null : phoneExt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(String loginRole) {
        this.loginRole = loginRole == null ? null : loginRole.trim();
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid == null ? null : deviceUuid.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }
}