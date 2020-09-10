package com.kyx.util;

import java.util.Random;

public class CommonUtils {


    /**
     * 返回区间的随机值
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumber(int min,int max){
        Random random =new Random();
        int res = random.nextInt(max);
        while (res<min){
            res =res +random.nextInt(max-min);
        }
        return res;
    }

    /**
     * 生成4位验证码
     * @return
     */
    public String verificationCode(){
        Random random =new Random();
        int  str =random.nextInt(10000) ;
        while (str<1000){
            str =random.nextInt(10000);
        }
        return "验证码为:"+str+"";
    }

    public static String stringToUnicode(String str){
        char[] utfBytes = str.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }
    public static String UnicodeToString(String str){
        int start = 0;
        int end = 0;
        StringBuffer stringBuffer =new StringBuffer();

        while (start>-1){
            String charStr="";
            end =str.indexOf("\\u",start+2);
            if (str.indexOf("\\u",start+2)!=-1){
                charStr +=str.substring(start+2,end);
            }else {
                charStr =str.substring(start+2,str.length());
            }
            stringBuffer.append((char)Integer.parseInt(charStr,16));
            start =end;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(UnicodeToString("\\u8bf7\\u8f93\\u5165\\u6807\\u7b7e"));
        String str ="\\u4f30\\u4f31";
        String a =str;
        System.out.println(a);
//        System.out.println("\u4f82\u4f65".length());
//        System.out.println("\u4f82".substring(2,"\u4f82".length()));
    }
}
