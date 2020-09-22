package com.shijt.OAuth2.dao;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.vo.UserBaseInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserBaseInfoDao extends CrudRepository<UserBaseInfo,Long> {
}
