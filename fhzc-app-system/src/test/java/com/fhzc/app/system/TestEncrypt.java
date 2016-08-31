package com.fhzc.app.system;

import com.fhzc.app.dao.mybatis.util.EncryptUtils;

/**
 * Created by lihongde on 2016/8/18 19:03
 */
public class TestEncrypt {
    public static void main(String[] args) throws Exception {
       String info =  EncryptUtils.encryptToDES("0127001X", "15100233758");
        System.out.println(info);

        //String d = EncryptUtils.decryptByDES("09285618", "");
    }
}
