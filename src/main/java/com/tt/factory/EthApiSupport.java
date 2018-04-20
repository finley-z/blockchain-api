package com.tt.factory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.parity.Parity;

import java.util.List;
import java.util.Random;

@Service
public class EthApiSupport {
    @Autowired(required = false)
    private List<Web3j> web3j;

    @Autowired(required = false)
    private List<Parity> parity;

    public Web3j getWeb3j(){
        if(web3j!=null){
            int size=web3j.size();
            if(size>0){
                return web3j.get(getIndex(size));
            }
        }
        return null;
    }

    public Parity getParity(){
        if(parity!=null){
            int size=parity.size();
            if(size>0){
                return parity.get(getIndex(size));
            }
        }
        return null;
    }

    private int getIndex(int instanceNumber){
        Random random=new Random();
        return random.nextInt(instanceNumber);
    }
}
