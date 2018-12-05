package com.shijt.OAuth2.vo;

import com.shijt.OAuth2.commons.GlobalConsts;

import javax.persistence.*;

@Entity
@Table(name = GlobalConsts.tb_user,schema = GlobalConsts.db_schema)
public class User extends BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
