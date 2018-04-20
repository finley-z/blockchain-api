package com.tt.api.entity;

import jnr.ffi.annotations.In;

public class Account {
    //用户地址
    private String  userAddress;

    //用户ID
    private Integer userId;

    //余额    精确至分 换算成元  balance/100
    private Long balance;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
