package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import com.shijt.OAuth2.services.ExpenseHistoryService;
import com.shijt.OAuth2.dto.ControllerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value="水电开销控制器")
@RestController
@RequestMapping(value = GlobalConsts.login_need)
public class ExpenseController {
    @Autowired
    private ExpenseHistoryService expenseHistoryService;

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

}
