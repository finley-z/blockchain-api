package com.tt.api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tt.api.entity.ResultVo;
import com.tt.contract.TTVIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;



//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Controller
@RequestMapping(value = "/transaction")
public class TransactionController   extends BaseController{
    @ResponseBody
    @RequestMapping(value = "transform")
    public ResultVo transform(@RequestBody String json) {
        ResultVo resultVo=new ResultVo(true);
        try {
            if(json==null||json.length()<1){
                return null;
            }
            JSONObject param= JSON.parseObject(json);
            String to=param.getString("to");
            String amount=param.getString("amount");
            String remark=param.getString("remark");
            String address=param.getString("address");
            String password=param.getString("password");
            TTVIP contract = loadContract(address, password);
//            String txHash=redisUtil.incr("contract_id",1)+"";
            TransactionReceipt receipt = contract.transform(to, new BigInteger(amount),remark).send();
            if(receipt==null){
                resultVo.setStatus(false);
            }
            resultVo.setData(receipt);
        } catch (Exception e) {
            resultVo.setStatus(false);
            e.printStackTrace();
        }
        return resultVo;
    }

    @ResponseBody
    @RequestMapping(value = "add_fund")
    public ResultVo add_fund(@RequestBody String json) {
        ResultVo resultVo=new ResultVo(true);
        try {
            if(json==null||json.length()<1){
                return null;
            }
            JSONObject param= JSON.parseObject(json);
            String to=param.getString("to");
            String amount=param.getString("amount");
            String remark=param.getString("remark");
            String address=param.getString("address");
            String password=param.getString("password");
            TTVIP contract = loadContract(address, password);
//            String txHash=redisUtil.incr("contract_id",1)+"";
            TransactionReceipt receipt = contract.addFund(to, new BigInteger(amount),remark).send();
            if(receipt==null){
                resultVo.setStatus(false);
            }
            resultVo.setData(receipt);
        } catch (Exception e) {
            resultVo.setStatus(false);
            e.printStackTrace();
        }
        return resultVo;
    }


}
