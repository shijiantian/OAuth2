package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.Utils.DateFormatUtil;
import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dao.ExpenseHistoryDao;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.EchartsOption;
import com.shijt.OAuth2.dto.ExpenseHistoryDto;
import com.shijt.OAuth2.services.ExpenseHistoryService;
import com.shijt.OAuth2.vo.ExpenseHistory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseHistoryServiceImpl implements ExpenseHistoryService {

    @Autowired
    private ExpenseHistoryDao expenseHistoryDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ControllerResult getExpenseHistory(int pageNo,int pageSize) {
        //获取总数
        Integer totalCount=expenseHistoryDao.getTotalCount();
        //计算页面数量
        int totalPageNo= (int)Math.ceil(totalCount.doubleValue()/pageSize);
        List<ExpenseHistory> expenseHistorys=getPageSelectResult(pageNo,pageSize,1);

        Map<String,Integer> waterCountMap= expenseHistorys.stream()
                .collect(Collectors.toMap(vo->DateFormatUtil.date2MonthStr(vo.getExpenseDate()),ExpenseHistory::getWaterCount));

        Map<String,Integer> elecCountMap= expenseHistorys.stream()
                .collect(Collectors.toMap(vo->DateFormatUtil.date2MonthStr(vo.getExpenseDate()),ExpenseHistory::getElecCount));
        List<ExpenseHistoryDto> resultList= new ArrayList<>();
        for(int i=0;i<expenseHistorys.size()-1;i++){
            ExpenseHistory vo=expenseHistorys.get(i);
            Date laseMonth=DateFormatUtil.getPreviousMonth(vo.getExpenseDate(),-1);
            ExpenseHistoryDto dto=new ExpenseHistoryDto();
            dto.setExpenseDate(DateFormatUtil.date2DayStr(vo.getExpenseDate()));
            dto.setElecPrice(vo.getElecPrice());
            dto.setWaterPrice(vo.getWaterPrice());
            String lastMonthStr=DateFormatUtil.date2MonthStr(laseMonth);
            Integer lastElecCount=elecCountMap.get(lastMonthStr);
            Integer lastWaterCount=waterCountMap.get(lastMonthStr);
            dto.setElecCount(vo.getElecCount()-(lastElecCount==null?vo.getElecCount():lastElecCount));
            dto.setWaterCount(vo.getWaterCount()-(lastWaterCount==null?vo.getWaterCount():lastWaterCount));
            resultList.add(dto);
        }
        ControllerResult result=new ControllerResult(resultList,0,null,totalPageNo,totalCount);
        result.setAddition(getEchartsOption(resultList));
        return result;
    }

    @Override
    public void setExpenseHistory(ExpenseHistoryDto expenseHistoryDto) {
        ExpenseHistory expenseHistory=new ExpenseHistory(expenseHistoryDto);
        expenseHistoryDao.save(expenseHistory);
    }

    @Override
    public List<ExpenseHistory> getPageSelectResult(int pageNo, int pageSize,int overflow){
        //计算起始位置
        int startPosition=(pageNo-1)*(pageSize);
        return expenseHistoryDao.findByPage(startPosition,pageSize+overflow);
    }

    @Override
    public Object getEchartsOption(List<ExpenseHistoryDto> data) {
        EchartsOption option =new EchartsOption();
        Map<String,String> title=new HashMap<>();
        title.put("text","往月水电明细");
        option.setTitle(title);

        Map<String,String[]> legend=new HashMap<>();
        String[] legendData=new String[]{"水费","电费","总计"};
        legend.put("data",legendData);
        option.setLegend(legend);

        Map<String,Object>[] series=new HashMap[3];
        Map<String,Object> water=new HashMap<>();
        Map<String,Object> elec=new HashMap<>();
        Map<String,Object> total=new HashMap<>();
        water.put("name","水费");
        water.put("type","bar");
        elec.put("name","电费");
        elec.put("type","bar");
        total.put("name","总计");
        total.put("type","bar");
        Map<String,String[]> xAxis=new HashMap<>();
        String[] xAxisData=new String[data.size()];
        float[] waterSeries=new float[data.size()];
        float[] elecSeries=new float[data.size()];
        float[] totalSeries=new float[data.size()];
        int size=data.size()-1;
        for(int i=0,j=size;i<=size;i++,j--){
            ExpenseHistoryDto vo=data.get(j);
            xAxisData[i]=vo.getExpenseDate();
            waterSeries[i]=vo.getWaterCount()*vo.getWaterPrice();
            elecSeries[i]=vo.getElecCount()*vo.getElecPrice();
            totalSeries[i]=vo.getWaterCount()*vo.getWaterPrice()+vo.getElecCount()*vo.getElecPrice();

        }
        xAxis.put("data",xAxisData);
        water.put("data",waterSeries);
        elec.put("data",elecSeries);
        total.put("data",totalSeries);
        series[0]=water;
        series[1]=elec;
        series[2]=total;
        option.setSeries(series);
        option.setxAxis(xAxis);
        return option;
    }

    /**
     * 校验参数
     * @param expenseHistoryDto
     */
    @Override
    public void validate(ExpenseHistoryDto expenseHistoryDto) {
        boolean exist=this.existsByMonth(expenseHistoryDto.getExpenseDate());
        if(exist){
//            throw new XMLMessageException("当月数据已存在", GlobalConsts.error_code);
        }
    }

    @Override
    public List<ExpenseHistory> findByParams(ExpenseHistoryDto expenseHistoryDto) {
        StringBuilder sql=new StringBuilder("select * ");
        sql.append(" from ").append(GlobalConsts.db_schema).append(".").append(GlobalConsts.tb_expense_history);
        sql.append(" t where 1=1 ");
        if(StringUtils.hasText(expenseHistoryDto.getExpenseDate()))
            sql.append(" and t.expense_date=").append(expenseHistoryDto.getExpenseDate());
        if(expenseHistoryDto.getElecCount()!=null)
            sql.append(" and t.elec_count>=").append(expenseHistoryDto.getElecCount());
        if(expenseHistoryDto.getWaterCount()!=null)
            sql.append(" and t.water_count>=").append(expenseHistoryDto.getWaterCount());
        RowMapper<ExpenseHistory> rowMapper=new BeanPropertyRowMapper<>(ExpenseHistory.class);
        return jdbcTemplate.query(sql.toString(),rowMapper);

    }

    @Override
    public boolean existsByMonth(String expenseDateStr) {
        Date expenseDate=DateFormatUtil.str2DayDate(expenseDateStr);
        Calendar startTime=Calendar.getInstance();
        startTime.setTime(expenseDate);
        Calendar endTime=Calendar.getInstance();
        endTime.setTime(expenseDate);
        startTime.set(Calendar.DAY_OF_MONTH,1);
        endTime.add(Calendar.MONTH,1);
        endTime.set(Calendar.DAY_OF_MONTH,1);
        StringBuilder sql=new StringBuilder("select count(t.id) ");
        sql.append(" from ").append(GlobalConsts.db_schema).append(".").append(GlobalConsts.tb_expense_history);
        sql.append(" t where 1=1");
        sql.append(" and t.expense_date>=").append(DateFormatUtil.date2DayStr(startTime.getTime()));
        sql.append(" and t.expense_date<").append(DateFormatUtil.date2DayStr(endTime.getTime()));
        Integer count=jdbcTemplate.queryForObject(sql.toString(),Integer.class);
        if(count!=null&&count>0){
            return true;
        }else{
             return false;
        }
    }

    @Override
    public Workbook getExcelWorkbook() {
        Workbook resultWb=new HSSFWorkbook();
        Sheet sheet1=resultWb.createSheet("水电明细");
        setTitleRow(resultWb,sheet1);

        List<ExpenseHistory> expenseHistories=expenseHistoryDao.findAllByDesc();
        List<Cell> cellList=new ArrayList<>();
        for(int i=0;i<expenseHistories.size()-1;i++){
            Row row=sheet1.createRow(i+1);
            cellList.clear();
            for(int j=0;j<6;j++){
                cellList.add(row.createCell(j));
            }
            ExpenseHistory vo1=expenseHistories.get(i);
            ExpenseHistory vo2=expenseHistories.get(i+1);
            int waterCount=vo1.getWaterCount()-vo2.getWaterCount();
            int electCount=vo1.getElecCount()-vo2.getElecCount();
            cellList.get(0).setCellValue(DateFormatUtil.date2DayStr(vo1.getExpenseDate()));
            cellList.get(1).setCellValue(waterCount);
            cellList.get(2).setCellValue(vo1.getWaterPrice());
            cellList.get(3).setCellValue(electCount);
            cellList.get(4).setCellValue(vo1.getElecPrice());
            cellList.get(5).setCellValue(waterCount*vo1.getWaterPrice()+electCount*vo1.getElecPrice());
        }
        try {
            resultWb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultWb;
    }

    /**
     * 设置标题行
     * @param resultWb
     * @param sheet1
     */
    private void setTitleRow(Workbook resultWb, Sheet sheet1) {
        Row row0=sheet1.createRow(0);
        List<Cell> cells=new ArrayList<>();
        HSSFCellStyle titleStyle=((HSSFWorkbook) resultWb).createCellStyle();
        for(int i=0;i<6;i++){
            Cell cell=row0.createCell(i);
            cell.setCellStyle(titleStyle);
            cells.add(cell);
            //SetColumnWidth的第二个参数要乘以256.因为这个参数的单位是1/256个字符宽度
            //以下设置宽度为5个字符
            sheet1.setColumnWidth(i,14*256);
        }
        cells.get(0).setCellValue("年月");
        cells.get(1).setCellValue("用水量(吨)");
        cells.get(2).setCellValue("水费(元)");
        cells.get(3).setCellValue("用电量(度)");
        cells.get(4).setCellValue("电费(元)");
        cells.get(5).setCellValue("总计(元)");

        Font font=resultWb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)14);
        font.setColor(IndexedColors.RED.getIndex());
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

    }
}
