package com.boge.bogebook.util;

import java.text.DecimalFormat;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class Tools {

    /**
     * 数量大于10000时转换
     * @param n
     * @return
     */
    public static String intToStr(int n){
        if(n>10000){
            return n/10000+"."+n%10000/1000+"万";
        }else{
            return String.valueOf(n);
        }
    }

    public static String longToDateString(long l) {
        long l1 = 1000 * 60 * 60 * 24;
        long l2 = 30;
        long l3 = 12;

        if(l < l1){
            return (l / (1000 * 60 * 60)) + "小时前";
        }else if(l >= l1 && l/l1 < l2){
            if(l / l1 == 1){
                return "昨天";
            }else{
                return (l / l1)+"天前";
            }
        } else if(l/l1 >= l2 && l/l1/l2 < l3){
            return (l/l1 / l2)+"月前";
        } else {
            return (l/l1/l2 / l3)+"年前";
        }
    }


    public static String longToSize(long l){
        DecimalFormat df = new DecimalFormat("#.##");
        if(l / 1024 > 0 && l /(1024*1024) <= 0){
            return df.format(l/1024.0)+"KB";
        } else if(l / 1024 == 0){
            return l+"B";
        } else {
            return df.format(l/(1024*1024.0))+"MB";
        }
    }
}
