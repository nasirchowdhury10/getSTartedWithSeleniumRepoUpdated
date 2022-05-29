package com.example.demo.aop;



import com.example.demo.advice.GlobalExceptionHandlerAdvice;
import com.example.demo.exception.InvalidArgmentException;
import com.example.demo.exception.RecordNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;


@Aspect
@Component
public class ControllerAop {

    private final GlobalExceptionHandlerAdvice handlerAdvice;

    public ControllerAop(GlobalExceptionHandlerAdvice handlerAdvice){
        this.handlerAdvice = handlerAdvice;

    }


    private final Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    @Pointcut("execution(public org.springframework.http.ResponseEntity com.example.demo.controller..*(..))")
    public void endpointHandlerPointcut() {
        //its is  responisve pointcut.....

    }

    @Before("endpointHandlerPointcut()")
    public void before(JoinPoint joinPoint) {
        logger.info("Before Exception : {} ", joinPoint.getSignature().getName());

    }

    @After("endpointHandlerPointcut()")
    public void after(JoinPoint joinPoint) {
        logger.info("After Exception : {} ", joinPoint.getSignature().getName());

    }

    @Around("endpointHandlerPointcut()")
    public Object aroundExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Instant start = Instant.now();
        logger.info("{} Staring time  : {} ", methodName, LocalDateTime.now());


        Object result = null;
        try {
            result = joinPoint.proceed();
            logger.info("GET result From : {}", methodName);

        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
           result =  this.handlerException(e);
        } finally {
            logger.info("{} Exiting  time  : {} ", methodName, LocalDateTime.now());

            Duration executionTime = Duration.between(start,Instant.now());
            logger.info(" {} method Exception Time : {} ", methodName,executionTime,toString());

        }
        return result;

    }


    private Object handlerException(Throwable e){
        if(e instanceof RecordNotFoundException){

            return handlerAdvice.recordNotFoundHandler((RecordNotFoundException) e);

//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());

        }else if (e instanceof InvalidArgmentException){

            return handlerAdvice.badRequestHandler((InvalidArgmentException) e);

//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(e.getMessage());


        } else {

            return handlerAdvice.commonFoundHandler((Exception) e);
//            return ResponseEntity
//                    .internalServerError()
//                    .body(e.getMessage());
        }
    }

}


