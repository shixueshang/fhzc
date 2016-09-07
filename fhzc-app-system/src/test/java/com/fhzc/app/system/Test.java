package com.fhzc.app.system;


import org.apache.commons.codec.digest.DigestUtils;

/**
* Created by lihongde on 2016/7/7 11:40
*/
public class Test {
    public static void main(String[] args){
        System.out.println(DigestUtils.md5Hex("111111"));
    }
}
