package com.shijt.OAuth2.dto;

import com.shijt.OAuth2.commons.GlobalConsts;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ExpenseHistoryDto implements Serializable {

    private static final long serialVersionUID = -998443527353584057L;
    private Long id;

    @NotNull(message = "非空整数")
    @NumberFormat(pattern=GlobalConsts.int_regexp)
    private Integer waterCount;

    @NotNull(message = "非空数字")
    @NumberFormat(pattern = GlobalConsts.float_regexp)
    private Float waterPrice;


    @NotNull(message = "非空整数")
    @NumberFormat(pattern = GlobalConsts.int_regexp)
    private Integer elecCount;

    @NumberFormat(pattern = GlobalConsts.float_regexp)
    @NotNull(message = "非空数字")
    private Float elecPrice;

    private String expenseDate;

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

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
