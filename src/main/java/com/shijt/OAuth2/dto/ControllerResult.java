package com.shijt.OAuth2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

public class ControllerResult implements Serializable {

    private Object result;
    private int errorCode;
    private Object errors;
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

    public ControllerResult(Object result, int errorCode, Object errors) {
        this.result = result;
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ControllerResult(Object result,int errorCode,Object errors,int totalPage,int totalCount){
        this.result=result;
        this.errorCode=errorCode;
        this.errors=errors;
        this.totalPage=totalPage;
        this.totalCount=totalCount;
    }

    public Object getResult() {
        return result;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public Object getErrors() {
        return errors;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
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
