package com.shijt.OAuth2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

public class ControllerResult implements Serializable {

    private Object result;
    private int errorCode;
    private Object errorMsg;
    private int totalPage;
    private int totalCount;
    private Object addition;

    public ControllerResult(){}

    public ControllerResult(Object result) {
        this.result = result;
    }

    public ControllerResult(Object result, int errorCode) {
        this.result = result;
        this.errorCode = errorCode;
    }

    public ControllerResult(Object result, int errorCode, Object errorMsg) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ControllerResult(Object result,int errorCode,Object errorMsg,int totalPage,int totalCount){
        this.result=result;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
        this.totalPage=totalPage;
        this.totalCount=totalCount;
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

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @JsonDeserialize
    public Object getAddition() {
        return addition;
    }

    public void setAddition(Object addition) {
        this.addition = addition;
    }
}
