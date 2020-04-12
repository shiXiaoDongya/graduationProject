package com.luoxd.graduation_project.utils;

import com.sun.applet2.AppletParameters;

import java.util.HashMap;
import java.util.Map;

public class Condition2StrUtils {
    private static final Map<Integer,String> expCondition =  new HashMap<Integer,String>();
    private static final Map<Integer,String> eduCondition =  new HashMap<Integer,String>();
    private static final Map<Integer,String> finanCondition =  new HashMap<Integer,String>();
    private static final Map<Integer,String> sizeCondition =  new HashMap<Integer,String>();

    static {
        expCondition.put(1,"在校生");
        expCondition.put(2,"应届生");
        expCondition.put(3,"1年以内");
        expCondition.put(4,"1-3年");
        expCondition.put(5,"3-5年");
        expCondition.put(6,"5-10年");
        expCondition.put(7,"10年以上");

        eduCondition.put(1,"初中及以下");
        eduCondition.put(2,"中专/中技");
        eduCondition.put(3,"高中");
        eduCondition.put(4,"大专");
        eduCondition.put(5,"本科");
        eduCondition.put(6,"硕士");
        eduCondition.put(7,"博士");

        finanCondition.put(1,"未融资");
        finanCondition.put(2,"天使轮");
        finanCondition.put(3,"A轮");
        finanCondition.put(4,"B轮");
        finanCondition.put(5,"C轮");
        finanCondition.put(6,"D轮及以上");
        finanCondition.put(7,"已上市");
        finanCondition.put(8,"不需要融资");

        sizeCondition.put(1,"0-20人");
        sizeCondition.put(2,"20-99人");
        sizeCondition.put(3,"100-499人");
        sizeCondition.put(4,"500-999人");
        sizeCondition.put(5,"1000-9999人");
        sizeCondition.put(6,"10000人以上");

    }

    public static String getExpStr(Integer tempExpCondition) {
        return expCondition.get(tempExpCondition);
    }
    public static String getEduStr(Integer tempEduCondition) {
        return eduCondition.get(tempEduCondition);
    }
    public static String getFinanStr(Integer tempFinanCondition) {
        return finanCondition.get(tempFinanCondition);
    }
    public static String getSizeStr(Integer tempSizeCondition) {
        return sizeCondition.get(tempSizeCondition);
    }
}
