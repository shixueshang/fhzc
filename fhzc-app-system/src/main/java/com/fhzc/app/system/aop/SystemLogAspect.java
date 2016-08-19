package com.fhzc.app.system.aop;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.model.SystemLog;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.SystemLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 切点类
 * Created by lihongde on 2016/8/19 18:15
 */

@Aspect
@Component
public class SystemLogAspect extends BaseController{

    @Resource
    private SystemLogService systemLogService;

    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    /**
     * Service层切点
     */
    @Pointcut("@annotation(com.fhzc.app.system.aop.SystemServiceLog)")
    public void serviceAspect() {
    }

    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.fhzc.app.system.aop.SystemControllerLog)")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Admin user  = super.getCurrentUser();
        String ip = request.getRemoteAddr();
        try {
            SystemLog log = new SystemLog();
            log.setDescription(getControllerMethodDescription(joinPoint));
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setType(Const.LOG_TYPE.CONTROLLER);
            log.setLevel(Const.LOG_LEVEL.NORMAL);
            log.setRequestIp(ip);
            log.setExceptionCode(null);
            log.setExceptionDetail(null);
            log.setParams(null);
            log.setUserId(user.getId());
            log.setCreateTime(new Date());
            systemLogService.add(log);
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Admin user = super.getCurrentUser();
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSON.toJSON(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            SystemLog log = new SystemLog();
            log.setDescription(getServiceMthodDescription(joinPoint));
            log.setExceptionCode(e.getClass().getName());
            log.setType(Const.LOG_TYPE.SERVICE);
            log.setLevel(Const.LOG_LEVEL.ERROR);
            log.setExceptionDetail(e.getMessage());
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.setParams(params);
            log.setUserId(user.getId());
            log.setCreateTime(new Date());
            log.setRequestIp(ip);
            //保存数据库
            systemLogService.add(log);
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog. class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog. class).description();
                    break;
                }
            }
        }
        return description;
    }

}
