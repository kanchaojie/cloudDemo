package com.kcj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static String covertByDate(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        String str=sdf.format(d);
        return str;
    }
}
