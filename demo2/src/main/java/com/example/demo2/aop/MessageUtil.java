package com.example.demo2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * メッセージ出力クラス.<br>
 * 今回はログ出力と見立ててprintlnメソッドを使用します.
 */
@Aspect
@Component
public class MessageUtil {
    /**
     * com.example.demo2パッケージ内のメソッドを開始する際にメッセージを出力します.
     *
     * @param joinPoint
     */
    @Before("execution(* com.example.demo2.*.*.*(..))")
    public void beforeMessage(JoinPoint joinPoint) {
        System.out.println("----- " + joinPoint.getSignature() + "の処理を開始します. −−−−−");
    }

    /**
     * com.example.demo2パッケージ内のメソッドを終了する際にメッセージを出力します.
     *
     * @param joinPoint
     */
    @After("execution(* com.example.demo2.*.*.*(..))")
    public void afterMessage(JoinPoint joinPoint) {
        System.out.println("-----" + joinPoint.getSignature() + "の処理を終了します. -----");
    }

    /**
     * com.example.demo2.serviceパッケージ内のメソッドがreturnをする際にメッセージを出力します.
     *
     * @param joinPoint
     */
    @AfterReturning("execution(* com.example.demo2.service.*.*(..))")
    public void afterMessageReturn() {
        System.out.println("----- ユーザ情報の処理を終了します. -----");
    }
}
