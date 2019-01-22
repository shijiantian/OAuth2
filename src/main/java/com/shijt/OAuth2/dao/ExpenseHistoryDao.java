package com.shijt.OAuth2.dao;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.vo.ExpenseHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseHistoryDao extends CrudRepository<ExpenseHistory,Long> {

    @Query(value = "select count(id) from "+ GlobalConsts.db_schema+"."+GlobalConsts.tb_expense_history,nativeQuery = true)
    Integer getTotalCount();

    @Query(value = "select * from "+GlobalConsts.db_schema+"."+GlobalConsts.tb_expense_history+" order by expense_date desc limit :offset,:pageSize",nativeQuery = true)
    List<ExpenseHistory> findByPage(@Param("offset")int offset,@Param("pageSize") int pageSize);

    @Query(value ="select * from "+GlobalConsts.db_schema+"."+GlobalConsts.tb_expense_history+" order by expense_date desc",nativeQuery = true)
    List<ExpenseHistory> findAllByDesc();
}
