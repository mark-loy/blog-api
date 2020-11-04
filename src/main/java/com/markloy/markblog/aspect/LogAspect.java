package com.markloy.markblog.aspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 切入点
     * 切入点表达式: 拦截controller下的所有方法
     */
    @Pointcut("execution(* com.markloy.markblog.controller.*.*(..))")
    public void log() {}

    /**
     * 前置通知：在执行目标方法之前执行
     * @param joinPoint 连接点对象
     */
    @Before("log()")
    public void before(JoinPoint joinPoint) {
        // 得到ServletRequestAttributes
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 通过ServletRequestAttributes拿到HttpServletRequest
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取访问的url
        String url = request.getRequestURL().toString();
        // 获取访问的IP
        String ip = request.getRemoteAddr();
        // 获取调用的类、方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取调用时间
        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
        String time = dateFormat.format(date);
        // 构造
        RequestLog requestLog = new RequestLog(time, url, ip, classMethod, args);
        log.info("request: {}", requestLog);
    }

    /**
     * 后置通知：目标方法正确执行之后执行
     * @param result
     */
    @AfterReturning(pointcut = "log()", returning = "result")
    public void afterReturn(Object result) {
        log.info("result: {}", result);
    }

    /**
     * 后置通知：在执行目标方法之后执行
     */
    @After("log()")
    public void after() {}

    /**
     * 内部类
     *    定义需要展示的日志字段
     */
    private class RequestLog {
        /**
         * 请求时间
         */
        private String time;
        /**
         * 请求url
         */
        private String url;
        /**
         * 访问IP
         */
        private String ip;
        /**
         * 调用的类及方法
         */
        private String classMethod;
        /**
         * 参数
         */
        private Object[] args;

        public RequestLog(String time, String url, String ip, String classMethod, Object[] args) {
            this.time = time;
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "time='" + time + '\'' +
                    ", url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
