package com.shijt.auth2.dao;

import com.shijt.auth2.vo.RolePermission;
import org.springframework.data.repository.CrudRepository;

public interface RolePermissionDao extends CrudRepository<RolePermission,Long> {
}
