package hello.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

    // (return type) (class path).(method name)(params)
    // (*) (hello.core.service.). (*)(..)
    @Around("execution(* hello.core.service..*(..))")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start_time = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end_time = System.currentTimeMillis();
        System.out.println("Method took : " + (end_time - start_time) + "milli secs");
        System.out.println("execute : " + joinPoint.getThis().toString().split("@")[0] + " -> " + joinPoint.getSignature().getName());
        return result;
    }
}
