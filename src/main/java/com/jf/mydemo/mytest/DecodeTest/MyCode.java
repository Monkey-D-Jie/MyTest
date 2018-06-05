package com.jf.mydemo.mytest.DecodeTest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-03 17:20
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class MyCode {

    public static void main(String[] args) {
        //原文：123456   密钥：ABCD1234 得到的密文：tNEKtEL22FM=
        try {
            String str1 = "tNEKtEL22FM=";
            String str2 = "ABCD1234";
            byte[] decryptionbytes = Base64.getDecoder().decode(str1);
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(str2.getBytes());
            // 创建一个密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 开始解密操作
            String result = new String(cipher.doFinal(decryptionbytes), "UTF-8");
            System.out.println("结果:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
