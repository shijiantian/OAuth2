package com.shijt.oauth2.vo;

import com.shijt.oauth2.commons.GlobalConsts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role",schema = GlobalConsts.db_schema)
public class UserRole {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
