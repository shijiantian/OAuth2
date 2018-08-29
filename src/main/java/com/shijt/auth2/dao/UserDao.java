package com.shijt.auth2.dao;

import com.shijt.auth2.commons.GlobalConsts;
import com.shijt.auth2.vo.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User,Long> {

    @Query(value = "select * from "+GlobalConsts.db_schema+"."+GlobalConsts.tb_user +" where name=:name limit 1",nativeQuery = true)
    public User findUserByName(@Param("name") String name);
}
