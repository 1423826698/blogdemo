package com.kyx.util;

public class BulidArticleUtil {

    public String bulidArticle(String htmlArticleComment){
        String str =htmlArticleComment.trim();
        String regex = "\\s+";

        String articleTable =str.replace(regex,"");

        int indexBegin =articleTable.indexOf("<");
        int indexEnd =articleTable.indexOf(">");
        String myArticle="";
        String fragment ="";
        while (indexBegin!=-1){
            fragment=articleTable.substring(0,indexBegin);
            if (fragment.length()>194){
                fragment =fragment.substring(0,194);
                myArticle +=fragment;
            }else {
                myArticle += fragment;
            }

            articleTable=articleTable.substring(indexEnd+1);
            indexBegin=articleTable.indexOf("<");
            indexEnd=articleTable.indexOf(">");




        }
            if(myArticle.length()>194){
                myArticle =myArticle.substring(0,194);
            }
            return myArticle;


    }
}
