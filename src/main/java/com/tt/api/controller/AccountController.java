package com.tt.api.controller;


import com.tt.api.entity.ResultVo;
import com.tt.chainservice.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

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
}
