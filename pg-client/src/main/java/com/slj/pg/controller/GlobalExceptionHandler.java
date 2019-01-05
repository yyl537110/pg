package com.slj.pg.controller;

import com.alibaba.fastjson.JSON;
import com.slj.pg.excpetion.CustomException;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {NullPointerException.class, MissingServletRequestParameterException.class})
    public HttpMessage<Boolean> nullPointerExceptionHandle(Exception e) {
        LOG.error(e.getMessage(), e);
        return new HttpMessage<>(false, CustomStatus.PARAM_MISS);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    public HttpMessage<Boolean> illegalArgumentExceptionHandle(Exception e) {
        LOG.error(e.getMessage(), e);
        return new HttpMessage<>(false, CustomStatus.PARAM_ERROR);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public HttpMessage<Boolean> httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException e) {
        LOG.error(e.getMessage(), e);
        return new HttpMessage<>(false, CustomStatus.REQUEST_METHOD_ERROR);
    }

    @ExceptionHandler(value = CustomException.class)
    public HttpMessage<Object> customExceptionHandle(CustomException e) {
        LOG.error(e.getMessage(), e);
        return new HttpMessage<>(e.getContent(), e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public HttpMessage<Boolean> illegalStateExceptionHandle(Exception exception) {
        try {
            return new HttpMessage<>(false, JSON.parseObject(exception.getMessage(), CustomStatus.class));
        } catch (Exception e) {
            LOG.error("----统一异常处理失败", e);
        }
        return new HttpMessage<>(false, CustomStatus.INNER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public HttpMessage<Boolean> exceptionHandle(Exception e) {
        LOG.error(e.getMessage(), e);
        return new HttpMessage<>(false, CustomStatus.INNER_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public HttpMessage<Boolean> httpMessageNotReadableExceptionHandle(Exception e) {
        LOG.error(e.getMessage(), e);
        return new HttpMessage<>(false, CustomStatus.PARAM_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HttpMessage<Boolean> methodArgumentNotValidExceptionHandle(Exception e) {
        String message = e.getMessage();
        LOG.error(message, e);
        if (message.contains("NotNull") || message.contains("NotBlank")) {
            return new HttpMessage<>(false, CustomStatus.PARAM_MISS);
        }
        return new HttpMessage<>(false, CustomStatus.PARAM_ERROR);
    }

}

