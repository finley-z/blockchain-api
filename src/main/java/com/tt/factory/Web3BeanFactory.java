package com.tt.factory;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Web3BeanFactory {

        public static Web3j getInstance(String url){
           return  Web3j.build(new HttpService(url));
        }
}
