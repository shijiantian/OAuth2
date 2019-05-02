package com.shijt.OAuth2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ExpenseHistoryDto implements Serializable {
    @NotNull
    private Integer waterCount;
    @NotNull
    private Float waterPrice;
    @NotNull
    private Integer elecCount;
    @NotNull
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
}
