package com.fhzc.app.system;

import com.fhzc.app.dao.mybatis.util.EncryptUtils;

/**
 * Created by lihongde on 2016/8/18 19:03
 */
public class TestEncrypt {
    public static void main(String[] args) throws Exception {
       String info =  EncryptUtils.encryptToDES("09285618", "18910645495");
        System.out.println(info);

        String d = EncryptUtils.decryptByDES("09285618", "B87F4E069B2B897F5DEFDD6B54D43F07");
        System.out.println(d);
    }
}
