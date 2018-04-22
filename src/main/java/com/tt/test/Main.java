package com.tt.test;

import com.tt.chainservice.AccountService;
import com.tt.contract.TTVIP;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.StaticArray7;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.parity.methods.response.ParityAllAccountsInfo;
import org.web3j.tuples.generated.Tuple2;

import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");

        AccountService accountService = (AccountService) context.getBean("accountService");
        Web3j web3j = (Web3j) context.getBean("web3j");

        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials("123456", "/Users/finley/Documents/blockchain/chain01/keystore/UTC--2018-04-17T14-25-53.908410982Z--3b44fc7839a68a7773bd3dfbe9c71f3b06517318");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //加载合约                                                                                                                                                      94349406
            TTVIP contract = TTVIP.load("0x9763e85201a1316638ba63c94fed6ea9a894ca8d", web3j, credentials, new BigInteger("1"), new BigInteger("90349406"));
        try {

            //测试签署合约 String firstParty, String secondParty, BigInteger userId, BigInteger shareProfit, BigInteger expireYear, String remark
//            contract.signContract("0x3b44fc7839a68a7773bd3dfbe9c71f3b06517318", "0x38f67949eEB18Ed1A5CF01B4E2f0296afD4e956C", new BigInteger("666"), new BigInteger("12"), new BigInteger("2"), "测试签署").send();
            //测试交易查询
//            List ls2 = contract.getContracts().send();
//            System.out.println(ls2);

            //测试加款
//            contract.addFund("0xf96679550736c9a5c6284709ca166c9497a3cf98", new BigInteger("10000"), "test addfund").send();
//            Tuple2<BigInteger, BigInteger> yy = contract.getAccountInfo().send();

            //测试交易
//            contract.transform("0xf96679550736c9a5c6284709ca166c9497a3cf98",new BigInteger("1900"),"交易测试").send();

            //测试交易查询
            List ls = contract.getTransactions("0xf96679550736c9a5c6284709ca166c9497a3cf98").send();
            System.out.println("return val:"+ls);

            //测试账户信息查询
//            Tuple2<BigInteger, BigInteger> val=  contract.getAccountInfo().send();
//            System.out.println("return val:"+val);

//            Function function = new Function("getContracts", Arrays.asList(),Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray7<Uint256>>() {}));
//            String encodedFunction = FunctionEncoder.encode(function);
//            org.web3j.protocol.core.methods.response.EthCall response = web3j.ethCall(Transaction.createEthCallTransaction("0x3b44fc7839a68a7773bd3dfbe9c71f3b06517318", "0x3f077f9c7f5748faf3471808537588588f5166ec", encodedFunction), DefaultBlockParameterName.EARLIEST).sendAsync().get();
//            List<Type> someTypes = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testCall() throws ExecutionException, InterruptedException {

    }
}