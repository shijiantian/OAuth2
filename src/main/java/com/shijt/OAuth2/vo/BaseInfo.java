package com.shijt.OAuth2.vo;

import javax.persistence.*;
import java.util.Date;

public class BaseInfo {


    private Date createTime;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @PrePersist
    protected void onCreated(){
        this.updateTime=this.createTime=new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateTime=new Date();
    }
}
