package com.shijt.OAuth2.vo;

import com.shijt.OAuth2.commons.GlobalConsts;

import javax.persistence.*;

@Entity
@Table(name =GlobalConsts.tb_role_resource,schema = GlobalConsts.db_schema)
public class RoleResource extends BaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long roleId;
    private int resourceId;

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

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
