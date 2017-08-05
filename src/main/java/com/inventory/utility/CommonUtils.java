package com.inventory.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {
	
	private static final String MD5_SALT = "1@3$IBigDO!@#$%";
	
	public static String getMD5(String data) throws NoSuchAlgorithmException
    { 
		data = MD5_SALT + data;
		
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
           }
        
        return sb.toString();
    }
}
