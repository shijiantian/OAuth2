package com.shijt.OAuth2.dto;

import java.io.Serializable;

public class ErrorMsgDto implements Serializable {
    private String field;
    private String defaultMessage;

    public ErrorMsgDto(){}

    public ErrorMsgDto(String field, String defaultMessage) {
        this.field = field;
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
