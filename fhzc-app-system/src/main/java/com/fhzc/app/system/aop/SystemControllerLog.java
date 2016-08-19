package com.fhzc.app.system.aop;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller
 * Created by lihongde on 2016/8/19 18:13
 */


@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {

    String description()  default "";
}
