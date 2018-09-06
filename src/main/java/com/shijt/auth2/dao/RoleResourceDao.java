package com.shijt.auth2.dao;

import com.shijt.auth2.commons.GlobalConsts;
import com.shijt.auth2.vo.RoleResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleResourceDao extends CrudRepository<RoleResource,Long> {

    @Query(value = "select * from "+GlobalConsts.db_schema+"."+GlobalConsts.tb_role_resource+" where role_id=:roleId",nativeQuery = true)
    public List<RoleResource> findByRoleId(@Param("roleId") Long roleId);
}
