package com.fhzc.app.dao.mybatis.util;

/**
 * Created by lihongde on 2016/7/29 14:44
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具类
 */
public class EncryptUtils {

    private static final String MD5 = "MD5";
    private static final String SHA = "SHA-1";
    private static final String DES = "DES";
    private static final String AES = "AES";

    /**
     * MD5加密
     *
     * @param info 要加密的信息
     * @return 加密后的字符串
     */
    public static String encryptToMD5(String info) {
        byte[] digesta = null;
        try {
            // 得到一个md5的消息摘要
            MessageDigest alga = MessageDigest.getInstance(MD5);
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        return byte2hex(digesta);
    }

    /**
     * 进行SHA加密
     *
     * @return String 加密后的字符串
     */
    public static String encryptToSHA() {
        byte[] digesta = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance(SHA);
            // 添加要进行计算摘要的信息
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        return byte2hex(digesta);
    }


    /**
     * 根据一定的算法得到相应的key
     *
     * @param src
     * @return
     */
    private static String getKey(String algorithm, String src) {
        if (algorithm.equals(AES)) {
            return src.substring(0, 16);
        } else if (algorithm.equals(DES)) {
            return src.substring(0, 8);
        } else {
            return null;
        }
    }

    /**
     * 得到AES加密的key
     * @param src
     * @return
     */
    public static String getAESKey(String src){
        return getKey(AES, src);
    }

    /**
     * 得到DES加密的key
     *
     * @param src
     * @return
     */
    public static String getDESKey(String src) {
        return getKey(DES, src);
    }

    /**
     * 创建密匙
     *
     * @param algorithm 加密算法,可用 AES,DES,DESede,Blowfish
     * @return SecretKey 秘密（对称）密钥
     */
    private static SecretKey createSecretKey(String algorithm) {
        // 声明KeyGenerator对象
        KeyGenerator keygen;
        // 声明 密钥对象
        SecretKey deskey = null;
        try {
            // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
            keygen = KeyGenerator.getInstance(algorithm);
            // 生成一个密钥
            deskey = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 返回密匙
        return deskey;
    }

    /**
     * 创建一个AES的密钥
     * @return
     */
    private static SecretKey createSecretAESKey() {
        return createSecretKey(AES);
    }

    /**
     * 创建一个DES的密钥
     * @return
     */
    private static SecretKey createSecretDESKey() {
        return createSecretKey(DES);
    }

    /**
     * 根据相应的加密算法、密钥、源文件进行加密，返回加密后的文件
     *
     * @param Algorithm 加密算法:DES,AES
     * @param key
     * @param info
     * @return
     */
    private static String encrypt(String Algorithm, SecretKey key, String info) {
        // 定义要生成的密文
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
            c1.init(Cipher.ENCRYPT_MODE, key);
            // 对要加密的内容进行编码处理,
            cipherByte = c1.doFinal(info.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回密文的十六进制形式
        return byte2hex(cipherByte);
    }

    /**
     * 根据相应的解密算法、密钥和需要解密的文本进行解密，返回解密后的文本内容
     *
     * @param Algorithm
     * @param key
     * @param sInfo
     * @return
     */
    private static String decrypt(String Algorithm, SecretKey key, String sInfo) {
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            c1.init(Cipher.DECRYPT_MODE, key);
            // 对要解密的内容进行编码处理
            cipherByte = c1.doFinal(hex2byte(sInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(cipherByte);
    }

    /**
     * 根据相应的解密算法、指定的密钥和需要解密的文本进行解密，返回解密后的文本内容
     *
     * @param Algorithm 加密算法:DES,AES
     * @param sKey      这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @param sSrc
     * @return
     */
    private static String decrypt(String Algorithm, String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                throw new Exception("Key为空null");
            }
            // 判断采用AES加解密方式的Key是否为16位
            if (Algorithm.equals(AES) && sKey.length() != 16) {
                throw new Exception("Key长度不是16位");
            }
            // 判断采用DES加解密方式的Key是否为8位
            if (Algorithm.equals(DES) && sKey.length() != 8) {
                throw new Exception("Key长度不是8位");
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, Algorithm);
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hex2byte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 根据相应的加密算法、指定的密钥、源文件进行加密，返回加密后的文件
     *
     * @param Algorithm 加密算法:DES,AES
     * @param sSrc      这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @param sKey
     * @return
     */
    private static String encrypt(String Algorithm, String sSrc, String sKey) throws Exception {
        // 判断Key是否正确
        if (sKey == null) {
            throw new Exception("Key为空null");
        }
        // 判断采用AES加解密方式的Key是否为16位
        if (Algorithm.equals(AES) && sKey.length() != 16) {
            throw new Exception("Key长度不是16位");
        }
        // 判断采用DES加解密方式的Key是否为8位
        if (Algorithm.equals(DES) && sKey.length() != 8) {
            throw new Exception("Key长度不是8位");
        }
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, Algorithm);
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return byte2hex(encrypted);
    }

    /**
     * 采用DES随机生成的密钥进行加密
     *
     * @param key
     * @param info
     * @return
     */
    public static String encrypt(SecretKey key, String info) {
        return encrypt(DES, key, info);
    }

    /**
     * 采用DES指定密钥的方式进行加密
     *
     * @param key
     * @param info
     * @return
     * @throws Exception
     */
    public static String encryptToDES(String key, String info) throws Exception {
        return encrypt(DES, info, key);
    }

    /**
     * 采用DES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
     *
     * @param key
     * @param sInfo
     * @return
     */
    public static String decryptByDES(SecretKey key, String sInfo) {
        return decrypt(DES, key, sInfo);
    }

    /**
     * 采用DES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     *
     * @param key
     * @param sInfo
     * @return
     */
    public static String decryptByDES(String key, String sInfo) throws Exception {
        return decrypt(DES, sInfo, key);
    }

    /**
     * 采用AES随机生成的密钥进行加密
     * @param key
     * @param info
     * @return
     */
    public static String encryptToAES(SecretKey key, String info) {
        return encrypt(AES, key, info);
    }
    /**
     * 采用AES指定密钥的方式进行加密
     * @param key
     * @param info
     * @return
     * @throws Exception
     */
    public static String encryptToAES(String key, String info) throws Exception {
        return encrypt(AES, info, key);
    }

    /**
     * 采用AES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
     * @param key
     * @param sInfo
     * @return
     */
    private static String decryptByAES(SecretKey key, String sInfo) {
        return decrypt(AES, key, sInfo);
    }
    /**
     * 采用AES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     * @param key
     * @param sInfo
     * @return
     */
    private String decryptByAES(String key, String sInfo) throws Exception {
        return decrypt(AES, sInfo, key);
    }

    /**
     * 十六进制字符串转化为2进制
     *
     * @param strhex
     * @return
     */
    private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    /**
     * 将二进制转化为16进制字符串
     *
     * @param b 二进制字节数组
     * @return String
     */
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String source = "18559041317";
        String key = EncryptUtils.getDESKey(EncryptUtils.encryptToSHA());
        System.out.println(key);
        System.out.println("DES加密后为:" + EncryptUtils.encryptToDES(key, source));
        String str12 = EncryptUtils.decryptByDES(key, EncryptUtils.encryptToDES(key, source));
        System.out.println("DES解密后为：" + str12);

    }


}