package com.tt.ethservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private  Web3j web3j;

    @Autowired
    private  Parity parity;


    public List<String> getAccountList(){
        try{
            return  parity.personalListAccounts().send().getAccountIds();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String createNewAccount(String accountName,String password){
        try {
            NewAccountIdentifier newAccountIdentifier = parity.personalNewAccount(password).send();
            if(newAccountIdentifier!=null){
                String address = newAccountIdentifier.getAccountId();
//                parity.per(address,accountName);
//                Map<String,Object> account = new HashMap<String,Object>();
//                account.put(address,accountInfo);
//                parity.personalSetAccountMeta(address,account);
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

}
