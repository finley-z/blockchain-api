package com.tt.api.controller;

import com.tt.contract.TTVIP;
import com.tt.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.parity.Parity;

import java.io.File;
import java.math.BigInteger;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class BaseController {
    @Autowired
    protected Web3j web3j;

    @Autowired
    protected Parity parity;

    @Autowired
    protected RedisUtil redisUtil;

    @Value("${app.contractAddress}")
    protected String contractAddress;

    @Value("${app.keystore.dir}")
    protected String keystoreDir;

    @Value("${app.main_account}")
    protected String mainAccount;

    @Value("${app.main_account_pwd}")
    protected String accountPwd;

    public TTVIP loadContract(String address, String password) throws Exception {
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
            return TTVIP.load(contractAddress, web3j, credentials, new BigInteger("1"),  new BigInteger("1955909595"));
        }
        return null;
    }
}
