package com.jf.mydemo.mytest.DecodeTest;

import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-03 17:37
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class myDes {
    public static byte[] des(int mode, byte[] data, byte[] keyData) {
        byte[] ret = null;
        //加密的内容存在并且密钥存在且长度为8个字节
        if (data != null
                && data.length > 0
                && keyData != null
                && keyData.length == 8) {
            try {
                Cipher cipher = Cipher.getInstance("DES");
                DESKeySpec keySpec = new DESKeySpec(keyData);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey key = keyFactory.generateSecret(keySpec);
                cipher.init(mode, key);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    //DES 加密
    public static byte[] desEncrypt(byte[] data, byte[] keyData) {
        return des(Cipher.ENCRYPT_MODE, data, keyData);
    }

    //DES 解密
    public static byte[] desDecrypt(byte[] data, byte[] keyData) {
        return des(Cipher.DECRYPT_MODE, data, keyData);
    }

    @Test
    public void decodeTest(){

    }

}

