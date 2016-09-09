package com.fhzc.app.api;

import com.fhzc.app.api.tools.TextUtils;

/**
 * Created by lihongde on 2016/9/8 19:37
 */
public class Test {

    public  static void main(String[] args){
        String ss = "两市共成交2600多亿。\r\n\r\n昨天发改委再次发声";
        ss = TextUtils.delHTMLTag(ss);
        System.out.println(ss);
    }
}
