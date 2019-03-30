package com.hirim.sulgijang.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;


public class CryptUtils {
    private static  String key = "aklsdjalksdjlksajdklasdjklasd";

    private static SecretKeySpec key() {
        byte[] keyBytes = new byte[16];
        byte[] b = new byte[0];
        try {
            b = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int len = b.length;
        if(len > keyBytes.length){
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String encrypt(String str) {
        String iv = key.substring(0, 16);
        SecretKeySpec keySpec = key();
        Cipher c = null;
        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
            byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
            String enStr = new String(Base64.encodeBase64(encrypted));
            return enStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String decrypt(String str) {
        String iv = key.substring(0, 16);
        SecretKeySpec keySpec = key();

        Cipher c = null;
        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            return new String(c.doFinal(byteStr), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
