package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.ErrorMsgDto;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import com.shijt.OAuth2.services.ExpenseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Api(value="水电开销控制器")
@RestController
@RequestMapping(value = GlobalConsts.login_need)
public class ExpenseController {
    @Autowired
    private ExpenseHistoryService expenseHistoryService;
//    @Value("${excel.files.addr}")
//    private String excelFilesAddr;
    @Value("${excel.files.dirName}")
    private String dirName;

    @ApiOperation(value = "水电消费历史",notes = "获取历史水电消费记录")
    @RequestMapping(value = "getHistoryExpense/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Object getHistoryExpense(@PathVariable("pageNo")int pageNo,@PathVariable("pageSize")int pageSize){
        return expenseHistoryService.getExpenseHistory(pageNo,pageSize);
    }

    @ApiOperation(value = "水表电表数据",notes = "获取水表电表数据")
    @RequestMapping(value = "getMeterHistory/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Object getMeterHistory(@PathVariable("pageNo")int pageNo,@PathVariable("pageSize")int pageSize){
        return expenseHistoryService.getMeterHistory(pageNo,pageSize);
    }

    @RequestMapping(value = "setHistoryExpense",method = RequestMethod.POST)
    public Object setHistoryExpense(@RequestBody @Valid ExpenseHistoryDto expenseHistoryDto){
        List<ErrorMsgDto> errors=expenseHistoryService.existsByMonth(expenseHistoryDto.getExpenseDate());
        if(errors!=null){
            return new ControllerResult("参数校验错误",1001,errors);
        }else{
            expenseHistoryService.setExpenseHistory(expenseHistoryDto);
            return new ControllerResult(200);
        }
    }

    @RequestMapping(value = "getExcel/{type}",method = RequestMethod.GET)
    public Object getExcel(@PathVariable("type") int type) throws IOException{
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

        FileOutputStream localStream=new FileOutputStream(dirName+"/"+fileName);
        wb.write(localStream);
        localStream.close();

        return downloadFile(dirName+"/"+fileName,HttpServletResponse.SC_OK);
//        return new ControllerResult(excelFilesAddr+fileName);

    }

    public ResponseEntity<InputStreamResource> downloadFile(String filePath,Integer httpStatus) throws IOException {
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }


    @RequestMapping(value = "importMeterData",method = RequestMethod.POST)
    public Object importMeterData(@RequestParam("importFile") MultipartFile importFile) throws IOException{
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
//            String dirName="tempFiles";
            String fileName="importErrors.xls";
            FileOutputStream localStream=new FileOutputStream(dirName+"/"+fileName);
            errorWb.write(localStream);
            localStream.close();
            return downloadFile(dirName+"/"+fileName,HttpServletResponse.SC_CONFLICT);
//            return new ControllerResult(excelFilesAddr+fileName,1001);
        }
    }

    @ResponseBody
    @RequestMapping(value = "deleteExpenseHistory/{id}",method = RequestMethod.DELETE)
    public Object deleteExpenseHistory(@PathVariable("id") Long id){
        return expenseHistoryService.deleteById(id);
    }

}
