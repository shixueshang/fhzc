package com.fhzc.app.system.exception;

import com.fhzc.app.system.controller.AjaxJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lihongde on 2016/8/18 19:32
 */
@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AjaxJson handleInvalidRequestError(RuntimeException ex) {
        logger.error("handleInvalidRequestError RuntimeException :" + ex.getMessage(), ex);
        return new AjaxJson(false, "server error");
    }


}
