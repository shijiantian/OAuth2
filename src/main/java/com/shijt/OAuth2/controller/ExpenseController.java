package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import com.shijt.OAuth2.services.ExpenseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        boolean exist=expenseHistoryService.existsByMonth(expenseHistoryDto.getExpenseDate());
        if(exist){
            return new ControllerResult("当月数据已存在!",1001);
        }else{
            expenseHistoryService.setExpenseHistory(expenseHistoryDto);
            return new ControllerResult(200);
        }
    }

    @RequestMapping(value = "getExcel/{type}",method = RequestMethod.GET)
    public Object getExcel(@PathVariable("type") int type){
        String dirName="tempFiles";
        File outputDir=new File(dirName);
        if(!outputDir.exists()){
            outputDir.mkdirs();
        }
        String fileName=null;
        switch (type){
            case 1 :
                fileName="expense.xls";
                break;
            case 2 :
                fileName="meterData.xls";
                break;
            default:
                System.out.println("无此类型");
                return new ControllerResult("无此类型",1001);
        }
        Workbook wb=expenseHistoryService.getExcelWorkbook(type);
        try {
            FileOutputStream localStream=new FileOutputStream(dirName+"/"+fileName);
            wb.write(localStream);
            localStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ControllerResult(excelFilesAddr+fileName);

    }

    @RequestMapping(value = "importMeterData",method = RequestMethod.POST)
    public Object importMeterData(@RequestParam("importFile") MultipartFile importFile){
        InputStream inputStream=null;
        Workbook wb=null;
        try {
            inputStream=importFile.getInputStream();
            wb=new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook errorWb=expenseHistoryService.importMeterData(wb);
        if(errorWb==null){
            return new ControllerResult("上传成功");
        }else{
            String dirName="tempFiles";
            String fileName="importErrors.xls";
            try {
                FileOutputStream localStream=new FileOutputStream(dirName+"/"+fileName);
                errorWb.write(localStream);
                localStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ControllerResult(excelFilesAddr+fileName,1001);
        }
    }

}
