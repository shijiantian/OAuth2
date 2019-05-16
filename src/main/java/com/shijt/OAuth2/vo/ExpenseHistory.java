package com.shijt.OAuth2.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shijt.OAuth2.utils.DateFormatUtil;
import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name=GlobalConsts.tb_expense_history,schema= GlobalConsts.db_schema)
public class ExpenseHistory extends BaseInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer waterCount;
    private Float waterPrice;
    private Integer elecCount;
    private Float elecPrice;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date expenseDate;

    public ExpenseHistory(){}

    public ExpenseHistory(ExpenseHistoryDto expenseHistoryDto){
        this.waterCount=expenseHistoryDto.getWaterCount();
        this.waterPrice=expenseHistoryDto.getWaterPrice();
        this.elecCount=expenseHistoryDto.getElecCount();
        this.elecPrice=expenseHistoryDto.getElecPrice();
        this.expenseDate=DateFormatUtil.str2DayDate(expenseHistoryDto.getExpenseDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(Integer waterCount) {
        this.waterCount = waterCount;
    }

    public Float getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(Float waterPrice) {
        this.waterPrice = waterPrice;
    }

    public Integer getElecCount() {
        return elecCount;
    }

    public void setElecCount(Integer elecCount) {
        this.elecCount = elecCount;
    }

    public Float getElecPrice() {
        return elecPrice;
    }

    public void setElecPrice(Float elecPrice) {
        this.elecPrice = elecPrice;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }
}
