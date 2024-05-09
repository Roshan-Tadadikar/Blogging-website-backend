package com.example.demo.Aspects;

import com.example.demo.Models.LoggerModel;
import com.example.demo.repositories.LoggerRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Aspects {

    @Autowired
    LoggerRepository loggerRepository;

    @AfterThrowing(value = "execution(* com.example.demo..*.*(..))", throwing = "ex")
    public void  logExeptions(JoinPoint joinPoint, Exception ex){
        log.error(joinPoint.getSignature().getName()+" An exception occured "+ex.getMessage());
        ex.printStackTrace();
        
    }

//    @Before(value = "execution(* com.example.demo.Controllers.*(..))")
//    public void logControllerLogs(ProceedingJoinPoint joinPoint){
//        log.info("Inside logControlelrLogs");
//        LoggerModel model = new LoggerModel();
//
//    }
}
