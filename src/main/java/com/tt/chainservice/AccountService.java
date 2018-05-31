package com.tt.chainservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.ParityAllAccountsInfo;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
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
    @Value("${app.contractAddress}")
    protected String contractAddress;

    @Value("${app.keystore.dir}")
    protected String keystoreDir;

    @Value("${app.main_account}")
    protected String mainAccount;

    @Value("${app.main_account_pwd}")
    protected String accountPwd;

    //创建账户
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

    //解锁账户
    public boolean unlockAccount(String address, String password){
        try {
            PersonalUnlockAccount personalUnlockAccount= parity.personalUnlockAccount(address,password).send();
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

    //获取账户列表
    public List<String> getAccountList(){
        try{
            return  parity.personalListAccounts().send().getAccountIds();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取账户详细信息
    public ParityAllAccountsInfo.AccountsInfo getAccountInfo(String address){
        try{
            ParityAllAccountsInfo parityAllAccountsInfo=parity.parityAllAccountsInfo().send();
            return  parityAllAccountsInfo.getAccountsInfo().get(address);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取账户在区块链中的余额
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


    public boolean sendEth(String ownAddress ,String toAddress ){
        try {
            Credentials credentials =loadCredentialsFromFile(ownAddress,accountPwd);
            BigInteger GAS_PRICE = BigInteger.valueOf(1L);
            BigInteger GAS_LIMIT = BigInteger.valueOf(4600000);

            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(ownAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

               //创建交易，这里是转0.5个以太币
               BigInteger value = Convert.toWei("5", Convert.Unit.ETHER).toBigInteger();
               RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, GAS_PRICE, GAS_LIMIT, toAddress, value);

               //签名Transaction，这里要对交易做签名
               byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
               String hexValue = Numeric.toHexString(signedMessage);

               //发送交易
               EthSendTransaction ethSendTransaction =web3j.ethSendRawTransaction(hexValue).sendAsync().get();
               String transactionHash = ethSendTransaction.getTransactionHash();
               if(transactionHash!=null){
                   return true;
               }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Credentials loadCredentialsFromFile(String address, String password) throws Exception {
        File file = new File(keystoreDir);
        String[] keyNames = file.list();
        address = address.replace("0x", "");
        String keyFile = null;
        for (int i = 0; keyNames != null && i < keyNames.length; i++) {
            if (keyNames[i].toLowerCase().indexOf(address.toLowerCase()) != -1) {
                keyFile = keyNames[i];
                break;
            }
        }

        if (keyFile != null) {
            Credentials credentials = WalletUtils.loadCredentials(password, keystoreDir + keyFile);
            return credentials;
        }
        return null;
    }

    public static void main(String []args){
        AccountService service=new AccountService();
        service.sendEth("0xDB9aB489ed6b547121DC179C901D0F83d21e3692","0x6A87772A28Ef6327AE41E9262596E45C631f78eC");
    }
}
