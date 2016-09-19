package com.weixin.core.util;
import java.security.MessageDigest;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

 /** 
  11.  * 基础加密组件 
  12.  *  
  13.  * @author 梁栋 
  14.  * @version 1.0 
  15.  * @since 1.0 
  16.  */  
 public abstract class Coder {  
public static final String KEY_SHA = "SHA";  
public static final String KEY_MD5 = "MD5";  

/** 
  22.      * MAC算法可选以下多种算法 
  23.      *  
  24.      * <pre> 
  25.      * HmacMD5  
  26.      * HmacSHA1  
  27.      * HmacSHA256  
  28.      * HmacSHA384  
  29.      * HmacSHA512 
  30.      * </pre> 
  31.      */  
 public static final String KEY_MAC = "HmacMD5";  
/** 
  35.      * BASE64解密 
  36.      *  
  37.      * @param key 
  38.      * @return 
  39.      * @throws Exception 
  40.      */  
     public static byte[] decryptBASE64(String key) throws Exception {  
         return (new BASE64Decoder()).decodeBuffer(key);  
   }  
     /** 
  46.      * BASE64加密 
  47.      *  
  48.      * @param key 
  49.      * @return 
  50.      * @throws Exception 
  51.      */  
    public static String encryptBASE64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }  
   
     /** 
  57.      * MD5加密 
  58.      *  
  59.      * @param data 
  60.      * @return 
  61.      * @throws Exception 
  62.      */  
    public static byte[] encryptMD5(byte[] data) throws Exception {  
  
         MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
         md5.update(data);  
  
        return md5.digest();  
    }  
  
     /** 
  73.      * SHA加密 
  74.      *  
  75.      * @param data 
  76.      * @return 
  77.      * @throws Exception 
  78.      */  
     public static byte[] encryptSHA(byte[] data) throws Exception {  
  
       MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
         sha.update(data);  
   
         return sha.digest();  
   
     }  
   
     /** 
  89.      * 初始化HMAC密钥 
  90.      *  
  91.      * @return 
  92.      * @throws Exception 
  93.      */  
     public static String initMacKey() throws Exception {  
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);  
   
        SecretKey secretKey = keyGenerator.generateKey();  
        return encryptBASE64(secretKey.getEncoded());  
     }  
   
     /** 
 102.      * HMAC加密 
 103.      *  
 104.      * @param data 
 105.      * @param key 
 106.      * @return 
 107.      * @throws Exception 
 108.      */  
     public static byte[] encryptHMAC(byte[] data, String key) throws Exception {  
   
         SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);  
         Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
         mac.init(secretKey);  
   
        return mac.doFinal(data); 
     }
   
}  