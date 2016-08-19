package com.fhzc.app.system.aop;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截service
 * Created by lihongde on 2016/8/19 18:14
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {

    String description()  default "";
}
