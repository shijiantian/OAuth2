package com.shijt.auth2.vo;

import com.shijt.auth2.commons.GlobalConsts;

import javax.persistence.Table;

@Table(name = "user_role",schema = GlobalConsts.db_schema)
public class UserRole {

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
