package com.tt.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tt.api.entity.ResultVo;
import com.tt.contract.TTVIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.UUID;


//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Controller
@RequestMapping(value = "/contract")
public class ContractController  extends BaseController{


    @ResponseBody
    @RequestMapping(value = "sign_contract")
    public ResultVo sign_contract(@RequestBody String json) {
        ResultVo resultVo=new ResultVo(true);
        try {
            if(json==null||json.length()<1){
               return null;
            }
            JSONObject param= JSON.parseObject(json);
            String secondParty=param.getString("secondParty");
            String contractType=param.getString("contractType");
            String contractName=param.getString("contractName");
            String contractContent=param.getString("contractContent");
            String remark=param.getString("remark");
            String address=param.getString("address");
            String password=param.getString("password");
//            String txHash=redisUtil.incr("contract_id",1)+"";
            TTVIP contract = loadContract(address, password);
            TransactionReceipt receipt = contract.signContract(secondParty, new BigInteger(contractType),contractName, contractContent,remark).send();
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
    @RequestMapping(value = "confirm_contract")
    public ResultVo confirm_contract(@RequestBody String json) {
        ResultVo resultVo=new ResultVo(true);
        try {
            if(json==null||json.length()<1){
                return null;
            }
            JSONObject param= JSON.parseObject(json);
            String txHash=param.getString("txHash");
            String state=param.getString("state");
            String address=param.getString("address");
            String password=param.getString("password");
            TTVIP contract = loadContract(address, password);
            TransactionReceipt receipt = contract.confirmContract(new BigInteger(txHash), new BigInteger(state)).send();
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
