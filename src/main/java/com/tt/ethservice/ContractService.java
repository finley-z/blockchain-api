package com.tt.ethservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.parity.Parity;
import org.web3j.utils.Collection;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ContractService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Parity parity;

    //Transacting with a smart contract  调用智能合约
    public void transaction(){
        Collection.Function function = null;

        String encodedFunction = FunctionEncoder.encode((Function) function);
        Transaction transaction = Transaction.createFunctionCallTransaction("",null,null, null, "",null,null);

        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = null;
        try {
            transactionResponse = web3j.ethSendTransaction(transaction).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String transactionHash = transactionResponse.getTransactionHash();
    }


    //Querying the state of a smart contract 查询智能合约
    public void query(){
        Function function = null;

        String encodedFunction = FunctionEncoder.encode(function);
        org.web3j.protocol.core.methods.response.EthCall response = null;
        try {
            response = web3j.ethCall(Transaction.createEthCallTransaction("", "", encodedFunction), DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Type> someTypes = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
    }

}
