package com.slj.pg.excpetion;

import com.slj.pg.http.CustomStatus;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
public class CustomException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final Object content;

    public CustomException(Exception e, CustomStatus customStatus) {
        super(e);
        this.errorCode = customStatus.getCode();
        this.errorMessage = customStatus.getMessage();
        this.content = null;
    }

    public CustomException(CustomStatus customStatus) {
        this.errorCode = customStatus.getCode();
        this.errorMessage = customStatus.getMessage();
        this.content = null;
    }

    public CustomException(CustomStatus customStatus, Object content) {
        this.errorCode = customStatus.getCode();
        this.errorMessage = customStatus.getMessage();
        this.content = content;
    }

    public String getErrorCode() {
        return errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getContent() {
        return content;
    }
}
