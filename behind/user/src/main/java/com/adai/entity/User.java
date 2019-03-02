package com.adai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;

/**
 * author Adai
 * create 2019-02-18  13:47
 */
public class User {
    private String account;
    private String realname;
    private String password;
    private String phone;
    private String email;
    private String sex;
    private String registerTime;
    private Integer balance;

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public User() {

    }

    public User(String account, String realname, String password, String phone, String email, String sex, String registerTime, Integer balance) {

        this.account = account;
        this.realname = realname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.registerTime = registerTime;
        this.balance = balance;
    }
}
