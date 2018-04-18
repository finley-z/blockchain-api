package com.tt.factory;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

public class ParityBeanFactory {

        public static Web3j getInstance(String url){
           return  Parity.build(new HttpService(url));
        }
}
