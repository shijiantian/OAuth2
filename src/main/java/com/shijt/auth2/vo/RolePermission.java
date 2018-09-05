package com.shijt.auth2.vo;

import com.shijt.auth2.commons.GlobalConsts;

import javax.persistence.*;

@Entity
@Table(name =GlobalConsts.tb_role_permission,schema = GlobalConsts.db_schema)
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long roleId;
    private int permissionId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
