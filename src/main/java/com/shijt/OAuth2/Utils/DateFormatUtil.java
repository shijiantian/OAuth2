package com.shijt.OAuth2.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static Date str2Date(String dateStr){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate=null;
        try{
            parseDate= dateFormat.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return parseDate;
    }
}
