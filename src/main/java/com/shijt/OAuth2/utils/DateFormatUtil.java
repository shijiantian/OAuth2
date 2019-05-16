package com.shijt.OAuth2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
    public static Date str2DayDate(String dateStr){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate=null;
        try{
            parseDate= dateFormat.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return parseDate;
    }

    public static String date2DayStr(Date date){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return  dateFormat.format(date);
    }

    public static String date2MonthStr(Date date){
        DateFormat dateFormat=new SimpleDateFormat("yyy-MM");
        return dateFormat.format(date);

    }

    public static Date getPreviousMonth(Date date,int preCount){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,preCount);
        return calendar.getTime();
    }
}
