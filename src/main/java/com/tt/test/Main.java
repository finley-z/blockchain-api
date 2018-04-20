package com.tt.test;

import com.tt.chainservice.AccountService;
import com.tt.contract.BusinessContract;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.parity.methods.response.ParityAllAccountsInfo;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {



        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AccountService accountService = (AccountService) context.getBean("accountService");
        Web3j web3j = (Web3j) context.getBean("web3j");

      //  BigInteger balance=accountService.getBalance("0xdb9ab489ed6b547121dc179c901d0f83d21e3692");
    //        String password = accountService.createNewAccount("finley1", "123456");
    //        System.out.println(password);

//       List<String> accounts = accountService.getAccountList();
//        ParityAllAccountsInfo.AccountsInfo accountInfo = accountService.getAccountInfo("0xdB11E39AdD1cbfA85E9fCc964a24868d9CF89aAf");
//    //    System.out.println(Arrays.toString(accounts.toArray()));
        Credentials credentials=null;
        try {
             credentials = WalletUtils.loadCredentials("123456", "D:/tt-02/keystore/UTC--2018-04-12T08-55-03.413097800Z--db9ab489ed6b547121dc179c901d0f83d21e3692");
        } catch (Exception e) {
            e.printStackTrace();
        }

        BusinessContract contract = BusinessContract.load("0xb069284221f2423e67938ba1f639bcb7036645b4", web3j, credentials, null,null);
     //   contract.signContract("0xDB9aB489ed6b547121DC179C901D0F83d21e3692","0x38f67949eEB18Ed1A5CF01B4E2f0296afD4e956C",new BigInteger("666"),new BigInteger("12"),new BigInteger("2"));
        try {
         //   List re=contract.getAdopters().send();
          //  System.out.println(contract.getContract("0xDB9aB489ed6b547121DC179C901D0F83d21e3692").send());
           // System.out.println(contract.getSender().send());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}