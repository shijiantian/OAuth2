package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import com.shijt.OAuth2.services.ExpenseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Api(value="水电开销控制器")
@RestController
@RequestMapping(value = GlobalConsts.login_need)
public class ExpenseController {
    @Autowired
    private ExpenseHistoryService expenseHistoryService;
    @Value("${excel.files.addr}")
    private String excelFilesAddr;

    @ApiOperation(value = "水电消费历史",notes = "获取历史水电消费记录")
    @RequestMapping(value = "getHistoryExpense/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Object getHistoryExpense(@PathVariable("pageNo")int pageNo,@PathVariable("pageSize")int pageSize){
        return expenseHistoryService.getExpenseHistory(pageNo,pageSize);
    }

    @RequestMapping(value = "setHistoryExpense",method = RequestMethod.POST)
    public Object setHistoryExpense(@RequestBody @Validated ExpenseHistoryDto expenseHistoryDto){
        expenseHistoryService.validate(expenseHistoryDto);
        expenseHistoryService.setExpenseHistory(expenseHistoryDto);
        return new ControllerResult(200);
    }

    @RequestMapping(value = "getExcel",method = RequestMethod.GET)
    public Object getExcel(){
        Workbook wb=expenseHistoryService.getExcelWorkbook();
        File outputDir=new File("tempFiles");
        if(!outputDir.exists()){
            outputDir.mkdirs();
        }

        try {
            FileOutputStream localStream=new FileOutputStream("tempFiles/expense.xls");
            wb.write(localStream);
            localStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ControllerResult(excelFilesAddr+"expense.xls");

    }

}
