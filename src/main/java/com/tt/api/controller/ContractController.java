package com.tt.api.controller;

import com.tt.api.entity.ContractContent;
import com.tt.contract.TTVIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.parity.Parity;

import java.io.File;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/contract")
public class ContractController {
    @Autowired
    private Web3j web3j;

    @Autowired
    private Parity parity;


    @Value("${app.contractAddress}")
    private String contractAddress;

    @Value("${app.keystore.dir}")
    private String keystoreDir;

    @ResponseBody
    @RequestMapping(value = "sign_contract")
    public String sign_contract(ContractContent con, @RequestParam(required = true) String address, @RequestParam(required = true) String password) {
        try {
            TTVIP contract = loadContract(address, password);
            TransactionReceipt receipt = contract.signContract(con.getSecondParty(), new BigInteger(con.getShareProfit().toString()), new BigInteger(con.getExpireYear().toString()), con.getRemark()).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "get_contract")
    public String get_contract(String address, String password, String secondParty) {
        try {
            TTVIP contract = loadContract(address, password);
            contract.getContracts().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TTVIP loadContract(String address, String password) throws Exception {
        File file = new File(keystoreDir);
        String[] keyNames = file.list();
        address = address.replace("0x", "");
        String keyFile = null;
        for (int i = 0; keyNames != null && i < keyNames.length; i++) {
            if (keyNames[i].indexOf(address) != -1) {
                System.out.println(keyNames[i]);
                keyFile = keyNames[i];
                break;
            }
        }

        if (keyFile != null) {
            Credentials credentials = WalletUtils.loadCredentials(password, keystoreDir + keyFile);
            return TTVIP.load(contractAddress, web3j, credentials, null, null);
        }
        return null;
    }
}
