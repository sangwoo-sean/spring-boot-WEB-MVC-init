package hello.core.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public class Aop {

    private final RequestLogger requestLogger;
    private final HttpServletRequest request;

    // Execution Expression Samples
    // (return type) (class path).(method name)(params)
    // (*) (hello.core.service.). (*)(..)
    // @annotation(org.springframework.web.bind.annotation.GetMapping) // 해당 어노테이션이 달린 메소드들 매핑

    @Around("execution(* hello.core.controller..*(..))")
    public Object AroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        requestLogger.setRequestURL(request.getRequestURI());
        requestLogger.log(" " + joinPoint.getThis().toString().split("@")[0] + " -> " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        return result;
    }

    @Around("execution(* hello.core.service..*(..))")
    public Object AroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        long start_time = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end_time = System.currentTimeMillis();
        requestLogger.log("[" + (end_time - start_time) + "ms] " + joinPoint.getThis().toString().split("@")[0] + " -> " + joinPoint.getSignature().getName());
        return result;
    }
}
