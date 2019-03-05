package com.adai.vo.request;

/**
 * author Adai
 * create 2019-03-04  16:32
 */
public class RegisterUser {
    private String account;
    private String realname;
    private String password;
    private String phone;
    private String email;
    private String sex;
    private Long registerTime;
    private Integer balance;

    @Override
    public String toString() {
        return "RegisterUser{" +
                "account='" + account + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", registerTime=" + registerTime +
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

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public RegisterUser() {

    }

    public RegisterUser(String account, String realname, String password, String phone, String email, String sex, Long registerTime, Integer balance) {

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
