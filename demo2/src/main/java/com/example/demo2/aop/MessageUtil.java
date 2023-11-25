package com.example.demo2.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageUtil {
    @Before("execution(* com.example.demo2.service.*.*(..))")
    public void beforeMessage() {
        System.out.println("----- Serviceクラス createUser メソッド開始 -----");
    }
}
