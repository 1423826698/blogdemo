package com.kyx.util;

public class StringAndArray {
    /**
     * 字符串转换成字符串数组
     * @param str
     * @return
     */
    public static String[] stringToArray(String str){
        String[] array =str.split(",");
        for (int i=0; i<array.length;i++){
            array[i] =array[i].trim();
        }
        return array;
    }

    /**
     * 字符串数组转换为字符串
     * @param tags
     * @return
     */
    public  static String arrayToString(String[] tags){
        StringBuilder sb =new StringBuilder();
        for (String s :tags){
            if (sb.length() ==0){
                sb.append(s.trim());
            }else {
                sb.append(",").append(s.trim());
            }
        }
        return sb.toString();
    }
}
