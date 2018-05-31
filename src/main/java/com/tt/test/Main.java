package com.tt.test;

import com.tt.chainservice.AccountService;
import com.tt.contract.TTVIP;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");

        AccountService accountService = (AccountService) context.getBean("accountService");
        Web3j web3j = (Web3j) context.getBean("web3j");
//        web3j.ethAccounts();

//        accountService.unlockAccount("0xDB9aB489ed6b547121DC179C901D0F83d21e3692","123456");
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials("123456", "D:/tt-02/keystore/UTC--2018-04-12T08-55-03.413097800Z--db9ab489ed6b547121dc179c901d0f83d21e3692");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //加载合约                                        0xb069284221f2423e67938ba1f639bcb7036645b4                                                                                                                94349406
            TTVIP contract = TTVIP.load("0xb069284221f2423e67938ba1f639bcb7036645b4", web3j, credentials, new BigInteger("1"), new BigInteger("90349406"));
        try {

            //测试签署合约 String firstParty, String secondParty, BigInteger userId, BigInteger shareProfit, BigInteger expireYear, String remark
//            contract.signContract("0x3b44fc7839a68a7773bd3dfbe9c71f3b06517318", new BigInteger("12"), new BigInteger("2"), "测试签署").send();
            //测试交易查询
//            List ls2 = contract.getContracts().send();
//            System.out.println(ls2);

            //测试加款 0xDB9aB489ed6b547121DC179C901D0F83d21e3692 0x38f67949eEB18Ed1A5CF01B4E2f0296afD4e956C
//            contract.addFund("0xDB9aB489ed6b547121DC179C901D0F83d21e3692", new BigInteger("22"), "test addfund").send();
//            Tuple2<BigInteger, BigInteger> yy = contract.getAccountInfo().send();

            //测试交易
//            contract.transform("0xf96679550736c9a5c6284709ca166c9497a3cf98",new BigInteger("1900"),"交易测试").send();

            //测试交易查询
//            List ls = contract.getTransactions("0xf96679550736c9a5c6284709ca166c9497a3cf98").send();
//            System.out.println("return val:"+ls);

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