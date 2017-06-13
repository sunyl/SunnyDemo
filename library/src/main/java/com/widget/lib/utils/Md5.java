package com.widget.lib.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	public static String md5(byte[] data) {
		if(data == null) {
			return "";
		}
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(data);
			byte[] messageDigest = digest.digest();
			return bufferToHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String bufferToHex(byte[] messageDigest) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String h = Integer.toHexString(0xFF & messageDigest[i]);
            while (h.length() < 2) {
                h = "0" + h;
            }
            hexString.append(h);
        }
        return hexString.toString();
	}
	
	public static String getFileMD5String(File file) {
		InputStream fis = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
	        fis = new FileInputStream(file);
	        byte[] buffer =  new byte[1024*10];
	        int numRead =  0;
	        while((numRead = fis.read(buffer)) >  0 ) {
	        	digest.update(buffer, 0 , numRead);  
	        }  
	        return  bufferToHex(digest.digest());  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCloseable(fis);
		}
		return "";
    }  
	
	public static String md5(String text) {
		if(text == null) {
			return "";
		}
		return md5(text.getBytes());
	}

	public static void closeCloseable(Closeable closeable) {
		if(closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}