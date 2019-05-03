package com.shijt.OAuth2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExpenseHistoryAspect {

    //将ExpenseHistoryServiceImpl类中的所有方法定义为切点
    @Pointcut("execution(* com.shijt.OAuth2.services.impl.ExpenseHistoryServiceImpl.*(..))")
    public void expenseHistory(){}

    //切点执行之前切入
    @Before(value = "expenseHistory()")
    public void doBefore(){
        System.out.println("-----start to query ExpenseHistory table !-------");
    }

    //@Around封装了切点的处理过程，可编写贯穿函数前后的逻辑,例如计算函数执行时间
    //本函数需要有返回值，否则切点的返回结果会为空
    @Around(value = "expenseHistory()")
    public Object doAround(ProceedingJoinPoint joinpoint) throws Throwable{
        long startTime=System.currentTimeMillis();
        Object result=joinpoint.proceed();
        long endTime=System.currentTimeMillis();
        System.out.println("-------执行耗时:"+(endTime-startTime));
        return result;
    }

    //切点成功返回之后切入（抛出异常不会切入）
    //returning注入切点返回值
    @AfterReturning(pointcut = "expenseHistory()",returning = "resultObject")
    public void doAfterReturning(Object resultObject){
        System.out.println("----function exec succeed------"+resultObject+"-----");
    }

    //切点之后执行(无论是否抛出异常)
    @After(value = "expenseHistory()")
    public void doAfter(){
        System.out.println("----function exec end-------");
    }

    @AfterThrowing(pointcut = "expenseHistory()",throwing = "exceptionObject")
    public void doAfterThrowing(Exception exceptionObject){
        System.out.println("----get exception----"+exceptionObject.getMessage());
    }

}
