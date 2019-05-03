package com.shijt.OAuth2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExpenseHistoryAspect {

    //将ExpenseHistoryServiceImpl类中的所有方法定义为切点
    @Pointcut("execution(* com.shijt.OAuth2.services.impl.ExpenseHistoryServiceImpl.*(..))")
    public void expenseHistory(){}

    //切点执行之前切入
    @Before(value = "expenseHistory()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("-----start to query ExpenseHistory table !-------");
    }

    //切点返回之后切入（抛出异常不会切入,@After注解才会在抛出异常之后依旧切入）
    @AfterReturning(pointcut = "expenseHistory()",returning = "resultObject")
    public void doAfterReturning(Object resultObject){
        System.out.println("----end the Query------"+resultObject+"-----");
    }

}
