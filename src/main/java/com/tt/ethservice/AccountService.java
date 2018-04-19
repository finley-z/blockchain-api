package com.tt.ethservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private  Web3j web3j;

    @Autowired
    private  Parity parity;

    public String createNewAccount(String accountName,String password,Map<String,Object> metaInfo){
        try {
            NewAccountIdentifier newAccountIdentifier = parity.personalNewAccount(password).send();
            if(newAccountIdentifier!=null){
                String address = newAccountIdentifier.getAccountId();
                parity.paritySetAccountName(address,accountName);
                parity.paritySetAccountMeta(address,metaInfo);
                return  address;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean unlockAccount(String address, String password){
        Admin web3j = Admin.build(new HttpService());
        try {
            PersonalUnlockAccount personalUnlockAccount= web3j.personalUnlockAccount(address,password).send();
            if(personalUnlockAccount.accountUnlocked()){
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getAccountList(){
        try{
            return  parity.personalListAccounts().send().getAccountIds();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BigInteger getBalance(String address){
        try {
            DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(web3j.ethBlockNumber().getId());
//            DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(1748);
            EthGetBalance ethGetBalance =  parity.ethGetBalance(address,defaultBlockParameter).send();
            if(ethGetBalance!=null){
                return ethGetBalance.getBalance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
