package com.slj.pg.http;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
public class HttpMessage<T> {

    /**
     * properties
     */
    private T content;
    private String code;
    private String message;

    public HttpMessage() {

    }

    public HttpMessage(CustomStatus customStatus) {
        this.code = customStatus.getCode();
        this.message = customStatus.getMessage();
    }

    public HttpMessage(T content, CustomStatus customStatus) {
        this.content = content;
        this.code = customStatus.getCode();
        this.message = customStatus.getMessage();
    }

    public HttpMessage(T content, String code, String message) {
        this.content = content;
        this.code = code;
        this.message = message;
    }
    /**
     * content getter
     *
     * @return content
     */
    public T getContent() {
        return content;
    }

    /**
     * content setter
     *
     * @param content content
     */
    public void setContent(T content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * message getter
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * message setter
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }


}
