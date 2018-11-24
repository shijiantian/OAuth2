package com.shijt.OAuth2.dto;

public class ControllerResult {

    private Object result;
    private Object errorCode;
    private Object errorMsg;

    public ControllerResult(){}

    public ControllerResult(Object result) {
        this.result = result;
    }

    public ControllerResult(Object result, Object errorCode) {
        this.result = result;
        this.errorCode = errorCode;
    }

    public ControllerResult(Object result, Object errorCode, Object errorMsg) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Object getResult() {
        return result;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }
}
