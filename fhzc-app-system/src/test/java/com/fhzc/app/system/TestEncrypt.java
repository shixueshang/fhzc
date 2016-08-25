package com.fhzc.app.system;

import com.fhzc.app.dao.mybatis.util.EncryptUtils;

/**
 * Created by lihongde on 2016/8/18 19:03
 */
public class TestEncrypt {
    public static void main(String[] args) throws Exception {
       String info =  EncryptUtils.encryptToDES("07168414", "13810465136");
        System.out.println(info);
    }
}
