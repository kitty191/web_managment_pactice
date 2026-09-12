package org.example.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    private final OperateLogMapper operateLogMapper;
    private final HttpServletRequest httpServletRequest;

    public OperationLogAspect(OperateLogMapper operateLogMapper, HttpServletRequest httpServletRequest) {
        this.operateLogMapper = operateLogMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Around("@annotation(org.example.anno.Log)")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable {
        //计算方法耗时
        long startTime = System.currentTimeMillis();

        //执行方法
        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        //构建日志实体
        OperateLog log = new OperateLog();

        log.setOperateEmpId((Integer) httpServletRequest.getAttribute("operateEmpId"));
        log.setOperateTime(LocalDateTime.now());
        log.setCostTime(costTime);
        log.setClassName(pjp.getTarget().getClass().getName());
        log.setMethodName(pjp.getSignature().getName());
        log.setMethodParams(Arrays.toString(pjp.getArgs()));
        log.setReturnValue(result != null ? result.toString() : "void");

        operateLogMapper.insert(log);

        return result;
    }
}
