package cn.dcy.jsapi.aspect;

import cn.dcy.jsapi.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Kyle
 * @date 2024/07/12
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(cn.dcy.jsapi.annotation.LogAnnotation)")
    public void logPoint() {

    }

    @Around("logPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        Object proceed = null;
        if (annotation != null) {
            proceed = point.proceed();
            System.out.println("Calling methodName = " + methodName + " res:" + proceed + " annotation:" + annotation.value());
        }

        return proceed;
    }

}
