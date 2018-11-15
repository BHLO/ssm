package cn.itcast.controller;

import cn.itcast.domain.SysLog;
import cn.itcast.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogController {
    //引入日志的业务类
    @Autowired
    private LogService logService;
    private SysLog log;
    @Autowired
    private HttpServletRequest request;


    //定义切面类的切入点 指定拦截的方法规则
    @Pointcut("execution( * cn.itcast.controller.*.*(..))")
    public void pt() {
    }

    //前置通知
    @Before("pt()")
    public void excuteBefore(JoinPoint jp) {
        log = new SysLog();

        //通过request存取ip
        log.setIp(request.getRemoteAddr());

        //存取时间
        log.setVisitTime(new Date());

        /*获取当前登录的用户名
        后台获取权限框架存储的用户对象*/
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        //存取用户名
        log.setUsername(user.getUsername());

        /*获取正在访问的类和方法*/
        //目标类
        Class targetClass = jp.getTarget().getClass();
        //获取类名
        String className = targetClass.getSimpleName();
        //获取方法名
        String methodName = jp.getSignature().getName();
        //存取Method
        log.setMethod(className + "==" + methodName);

    }

    //后置通知
    @AfterReturning("pt()")
    public void executeAfter(){
        log.setExecuteTime(new Date().getTime()-log.getVisitTime().getTime());
        log.setExecuteResult("success");
        log.setExecuteMsg("执行成功");
        logService.insertLog(log);
    }

    @AfterThrowing(pointcut = "pt()",throwing = "e")
    public void executeException(Exception e){
        log.setExecuteTime(new Date().getTime()-log.getVisitTime().getTime());
        log.setExecuteResult("exception");
        log.setExecuteMsg(e.getMessage());
        logService.insertLog(log);
    }

}
