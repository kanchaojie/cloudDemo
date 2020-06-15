package com.kcj.utils;

import java.util.LinkedList;
import java.util.List;

public class ListUtil {

    /**
     * 判断集合非空
     * @param source
     * @return
     */
    public static <T> boolean isEmpy(List<T> source){
        return null == source || source.isEmpty();
    }

    /**
     * 切片
     * @param source
     * @param n
     * @return
     */
    public static <T> List<List<T>> split(List<T> source,int n){
        List<List<T>> result = new LinkedList<List<T>>();
        //(先计算出余数)
        int remaider=source.size()%n;
        //然后是商
        int number=source.size()/n;
        //偏移量
        int offset=0;
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }
}

