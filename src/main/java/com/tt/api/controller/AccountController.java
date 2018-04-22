package com.tt.api.controller;


import com.tt.api.entity.ResultVo;
import com.tt.chainservice.AccountService;
import com.tt.contract.TTVIP;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.parity.Parity;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private Web3j web3j;

    @Autowired
    private Parity parity;


    @Value("${app.contractAddress}")
    private String contractAddress;

    @Value("${app.keystore.dir}")
    private String keystoreDir;

    @ResponseBody
    @RequestMapping(value = "register")
    public ResultVo register(String accountName, String password) {
        ResultVo resultVo=new ResultVo();
        try{
            String address =accountService.createNewAccount(accountName,password,null);
            resultVo.setData(address);
            resultVo.setStatus(!StringUtils.isEmpty(address));
        }catch (Exception e){
            resultVo.setStatus(false);
            resultVo.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultVo;
    }

    @ResponseBody
    @RequestMapping(value = "login")
    public ResultVo login(String address,String password) {
        ResultVo resultVo=new ResultVo();
        try{
            boolean success=accountService.unlockAccount(address,password);
            resultVo.setStatus(success);
        }catch (Exception e){
            resultVo.setStatus(false);
            resultVo.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultVo;
    }

    @ResponseBody
    @RequestMapping(value = "get_accounts")
    public List<String> get_accounts() {
        return accountService.getAccountList();
    }

    @ResponseBody
    @RequestMapping(value = "get_account_info")
    public List<String> get_account_info(String address,String password) {
        TTVIP contract=null;
        try {
            contract = loadContract(address, password);
            contract.getAccountInfo();
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
