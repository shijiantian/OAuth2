package com.shijt.auth2.dao;

import com.shijt.auth2.vo.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionDao extends CrudRepository<Permission,Long> {

}
