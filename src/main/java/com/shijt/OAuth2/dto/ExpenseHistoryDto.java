package com.shijt.OAuth2.dto;

public class ExpenseHistoryDto {
    private Integer waterCount;
    private Float waterPrice;
    private Integer elecCount;
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
