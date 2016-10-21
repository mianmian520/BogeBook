package com.boge.bogebook.util;

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

}
