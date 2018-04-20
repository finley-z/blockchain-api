package com.tt.api.entity;

public class ContractContent {
    //合约ID (用户地址加上用户ID)
    private String contractId;

    //甲方地址
    private String  firstParty;

    //乙方地址
    private String  secondParty;

    //甲方ID
    private Integer userId;

    //分润点 0-100 %
    private Integer  shareProfit;

    //有效时间  N 年
    private Integer expireYear;

    //交易哈希值
    private Integer txHash;

    //交易时间戳
    private Long timestamp;

    //备注
    private String  remark;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShareProfit() {
        return shareProfit;
    }

    public void setShareProfit(Integer shareProfit) {
        this.shareProfit = shareProfit;
    }

    public Integer getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(Integer expireYear) {
        this.expireYear = expireYear;
    }

    public Integer getTxHash() {
        return txHash;
    }

    public void setTxHash(Integer txHash) {
        this.txHash = txHash;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
