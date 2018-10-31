package com.shijt.oauth2.dao;

import com.shijt.oauth2.commons.GlobalConsts;
import com.shijt.oauth2.vo.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleDao extends CrudRepository<UserRole,Long> {

    @Query(value = "select * from "+GlobalConsts.db_schema+"."+GlobalConsts.tb_user_role+" where user_id=:userId",nativeQuery = true)
    public List<UserRole> findByUserID(@Param("userId") Long userId);
}
