package com.tt.api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tt.api.entity.ResultVo;
import com.tt.chainservice.AccountService;
import com.tt.contract.TTVIP;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Controller
@RequestMapping(value = "/account")
public class AccountController extends  BaseController{
    @Autowired
    private AccountService accountService;


    @ResponseBody
    @RequestMapping(value = "register")
    public ResultVo register(@RequestBody String json) {
        ResultVo resultVo=new ResultVo();
        if(json==null||json.length()<1){
            resultVo.setStatus(false);
            resultVo.setErrorMsg("参数格式不正确");
        }
        JSONObject param= JSON.parseObject(json);

        String address=param.getString("address");
        String name=param.getString("name");

        try{
            TTVIP contract = loadContract(mainAccount, accountPwd);
            byte[] name_bytes=toBytes(name);
            TransactionReceipt receipt = contract.register(address,new BigInteger("0"),name_bytes,new BigInteger("0")).send();
            accountService.sendEth(mainAccount,address);
            resultVo.setData(address);
            resultVo.setStatus(!StringUtils.isEmpty(address));
            resultVo.setData(receipt);
        }catch (Exception e){
            resultVo.setStatus(false);
            resultVo.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultVo;
    }

    @ResponseBody
    @RequestMapping(value = "sendData")
    public ResultVo sendData(@RequestBody String json) {
        ResultVo resultVo=new ResultVo();
        if(json==null||json.length()<1){
            resultVo.setStatus(false);
            resultVo.setErrorMsg("参数格式不正确");
        }
        JSONObject param= JSON.parseObject(json);

        String to=param.getString("to");
        String data=param.getString("data");

        try{
            String receipt=accountService.sendData(mainAccount,to,data);
            resultVo.setData(receipt);
            resultVo.setStatus(!StringUtils.isEmpty(receipt));
            resultVo.setData(receipt);
        }catch (Exception e){
            resultVo.setStatus(false);
            resultVo.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultVo;
    }


    @ResponseBody
    @RequestMapping(value = "login")
    public ResultVo login(@RequestBody String json) {
        ResultVo resultVo=new ResultVo();
        if(json==null||json.length()<1){
            resultVo.setStatus(false);
            resultVo.setErrorMsg("参数格式不正确");
        }
        JSONObject param= JSON.parseObject(json);
        String address=param.getString("address");
        String password=param.getString("password");
        try{
            boolean success=accountService.unlockAccount(address,password);
            resultVo.setStatus(success);
            resultVo.setData(redisUtil.get(address));
        }catch (Exception e){
            resultVo.setStatus(false);
            resultVo.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultVo;
    }

    @ResponseBody
    @RequestMapping(value = "get_accounts")
    public List<HashMap<String,String>> get_accounts() {
         List<HashMap<String,String>> accounts=new ArrayList<HashMap<String,String>>();
         List<String> address=accountService.getAccountList();
         Iterator<String> it=address.iterator();
         while(it.hasNext()){
             String addre=it.next();
             HashMap<String,String> acc=new HashMap<>();
             acc.put("address",addre);
             acc.put("name", (String) redisUtil.get(addre));
             accounts.add(acc);
         }
         return accounts;
    }

    @ResponseBody
    @RequestMapping(value = "get_account_info")
    public List<String> get_account_info(@RequestBody String json) {
        TTVIP contract=null;
        if(json==null||json.length()<1){
            return null;
        }
        JSONObject param= JSON.parseObject(json);
        String address=param.getString("address");
        String password=param.getString("password");
        try {
            contract = loadContract(address, password);
//            contract.getAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] toBytes(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            if (ch > 255)
                str +=  Integer.toHexString(ch);
            else
                str += Integer.toHexString(ch);
        }

        int len=str.length();
        int addZero=64-len;
        for(int i=0;addZero>0&&i<addZero;i++){
            str+="0";
        }
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }
}
