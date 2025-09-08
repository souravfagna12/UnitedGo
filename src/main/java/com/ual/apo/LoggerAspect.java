package com.ual.apo;

import java.text.DateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
public class LoggerAspect {
	@AfterThrowing(pointcut="excecution(* com.ual.*.*.*(..)",throwing="exception")
	public void logAfterThrowingDetails(JoinPoint joinPoint,Exception e) {
		log.info("In AfterReturning Advice with return value, Joinpoint signature :{}", joinPoint.getSignature());
		long time = System.currentTimeMillis();
		String date = DateFormat.getDateTimeInstance().format(time);
		log.info("Report generated at time:{}", date);
		log.error("error:"+e.getMessage());
	}
}
