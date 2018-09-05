package com.shijt.auth2.vo;

import com.shijt.auth2.commons.GlobalConsts;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name=GlobalConsts.tb_permission,schema = GlobalConsts.db_schema)
public class Permission {

    private int type;
    private String url;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
