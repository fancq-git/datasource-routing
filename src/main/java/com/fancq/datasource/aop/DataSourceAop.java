package com.fancq.datasource.aop;

import com.fancq.datasource.bean.DBContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    /**
     * 读切点
     */
    @Pointcut("!@annotation(com.fancq.datasource.annotation.Master) " +
            "&& (execution(* com.fancq.datasource..*.select*(..)) " +
            "|| execution(* com.fancq.datasource.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写切点
     */
    @Pointcut("@annotation(com.fancq.datasource.annotation.Master) " +
            "|| execution(* com.fancq.datasource.service..*.insert*(..)) " +
            "|| execution(* com.fancq.datasource.service..*.add*(..)) " +
            "|| execution(* com.fancq.datasource.service..*.update*(..)) " +
            "|| execution(* com.fancq.datasource.service..*.edit*(..)) " +
            "|| execution(* com.fancq.datasource.service..*.delete*(..)) " +
            "|| execution(* com.fancq.datasource.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }

    @Before("execution(* com.fancq.datasource.service.impl.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if (StringUtils.startsWithAny(methodName, "get", "select", "find"))
            DBContextHolder.slave();
        else
            DBContextHolder.master();
    }
}
