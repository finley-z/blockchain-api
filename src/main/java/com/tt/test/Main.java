package com.tt.test;

import com.tt.ethservice.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AccountService accountService = (AccountService) context.getBean("accountService");

//        String password = accountService.createNewAccount("finley1", "123456");
//        System.out.println(password);

        List<String> accounts = accountService.getAccountList();
        System.out.println(Arrays.toString(accounts.toArray()));

    }
}