package com.tt.ethservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;

@Service
public class TransactionService {
    @Autowired
    private Web3j web3j;

    @Autowired
    private Parity parity;

    public void sendTransaction(){

        Transaction transaction = Transaction.createContractTransaction("d",null,null,null,null,"");

        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = null;
        try {
            transactionResponse = parity.ethSendTransaction(null).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String transactionHash = transactionResponse.getTransactionHash();

        // poll for transaction response via org.web3j.protocol.Web3j.ethGetTransactionReceipt(<txHash>)
    }

}
