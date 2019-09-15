package com.example.aopdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class CustomAspect {
//    @Around("@annotation(CustomLogAnotation)")
    @Around("@annotation(com.example.aopdemo.CustomLogAnotation)")
    public Object basicLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        https://stackoverflow.com/questions/11881148/log-method-entries-with-spring-aop
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        Object retVal = null;
        try {
            StringBuffer startMessageStringBuffer = new StringBuffer();

            startMessageStringBuffer.append("Start method ");
            startMessageStringBuffer.append(joinPoint.getSignature().getName());
            startMessageStringBuffer.append("(");

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                startMessageStringBuffer.append(args[i]).append(",");
            }
            if (args.length > 0) {
                startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
            }

            startMessageStringBuffer.append(")");

            logger.info(startMessageStringBuffer.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            retVal = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessageStringBuffer = new StringBuffer();
            endMessageStringBuffer.append("Finish method ");
            endMessageStringBuffer.append(joinPoint.getSignature().getName());
            endMessageStringBuffer.append("(..); execution time: ");
            endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
            endMessageStringBuffer.append(" ms;");

            logger.info(endMessageStringBuffer.toString());
            System.out.println("CUSTOMLOG --> " + joinPoint.getSignature() + " <--");
            System.out.println("CUSTOMLOG start message buffer --> " + startMessageStringBuffer.toString() + " <--");
        } catch (Throwable ex) {
            StringBuffer errorMessageStringBuffer = new StringBuffer();

            // Create error message
            logger.info(errorMessageStringBuffer.toString(), ex);

            throw ex;
        }

        return retVal;
//        final Object proceed = joinPoint.proceed();
//        System.out.println("CUSTOMLOG --> " + joinPoint.getSignature() + " <--");
//        return proceed;
    }
}
