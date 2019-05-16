package com.shijt.OAuth2.services;

import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.ErrorMsgDto;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import com.shijt.OAuth2.vo.ExpenseHistory;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface ExpenseHistoryService {

    ControllerResult getExpenseHistory(int pageNo,int pageSize);

    void setExpenseHistory(ExpenseHistoryDto expenseHistoryDto);

    List<ExpenseHistory> getPageSelectResult(int pageNo, int pageSize,int overflow);

    Object getEchartsOption(List<ExpenseHistoryDto> data);

    List<ExpenseHistory> findByParams(ExpenseHistoryDto expenseHistoryDto);

    List<ErrorMsgDto> existsByMonth(String expenseDateStr);

    Workbook getExcelWorkbook(int type);

    Workbook importMeterData(Workbook wb);
}
