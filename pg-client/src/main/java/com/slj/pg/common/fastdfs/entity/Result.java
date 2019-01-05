package com.slj.pg.common.fastdfs.entity;

/**
 * Created by pengzhang7@iflytek.com on 17/4/5.
 */
public class Result {
    private String fileId;

    private boolean isSuccess;

    private int errorNo;

    public Result(){}

    public Result(boolean isSuccess,int errorNo){
        this.isSuccess = isSuccess;
        this.errorNo = errorNo;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }
}
