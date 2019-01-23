package com.shijt.OAuth2.Utils;

import org.springframework.util.StringUtils;

public class DataConversionUtil {

    public static int str2Int(String value,int defaultValue){
        if(StringUtils.isEmpty(value)){
            return defaultValue;
        }else{
            try {
                return Integer.valueOf(value);
            }catch (NumberFormatException e){
                e.printStackTrace();
                return defaultValue;
            }
        }
    }

    public static float str2Float(String value,float defaultValue){
        if(StringUtils.isEmpty(value)){
            return defaultValue;
        }else{
            try {
                return Float.valueOf(value);
            }catch (NumberFormatException e){
                e.printStackTrace();
                return defaultValue;
            }
        }
    }

    public static long str2Long(String value,long defaultValue){
        if(StringUtils.isEmpty(value)){
            return defaultValue;
        }else{
            try {
                return Long.valueOf(value);
            }catch (NumberFormatException e){
                e.printStackTrace();
                return defaultValue;
            }
        }
    }
}
