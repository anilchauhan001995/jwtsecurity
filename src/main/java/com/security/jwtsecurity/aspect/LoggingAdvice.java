package com.security.jwtsecurity.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {
	
	Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut(value="execution(* com.security.jwtsecurity.controller.*.*(..))")
	public void myPointCut() {	
	}
	
	@Around("myPointCut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getName();
		Object[] inputs = pjp.getArgs();
		logger.info("Method Invoked : "+methodName +" class : "+ className + " Input Arguments : " + mapper.writeValueAsString(inputs));
		Object object = pjp.proceed();
		logger.info("Method Response : "+methodName +" class : "+ className + " Input Arguments : " + mapper.writeValueAsString(object));
		return object;
	}
}
