package com.shijt.auth2.vo;

import com.shijt.auth2.commons.GlobalConsts;

import javax.persistence.*;

@Entity
@Table(name=GlobalConsts.tb_resource,schema = GlobalConsts.db_schema)
public class Resource {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
