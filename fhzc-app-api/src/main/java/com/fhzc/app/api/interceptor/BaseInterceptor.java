package com.fhzc.app.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihongde on 2016/7/28 12:08
 */
public class BaseInterceptor  implements HandlerInterceptor {

    protected Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    private static final String AUTH_URL = "/api/auth/";

    /**
     *  拦截所有请求
     *  1、如果已经登陆则返回true
     *  2、如果为登陆相关请求则返回true
     *  3、如果是其他请求且未登陆返回false
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isAuthenticated = SecurityUtils.getSubject().isAuthenticated();

        if(isAuthenticated) {
            return true; //已经登录过
        }

        String uri =  WebUtils.getRequestUri(request);

        if(uri.contains(AUTH_URL)){
            return true;
        }
        return false;
    }

    /**
     * 该方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
     * 作。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }

}