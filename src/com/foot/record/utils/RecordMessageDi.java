package com.foot.record.utils;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;
public class RecordMessageDi {
	public static String MD5Encoder(String inputValue){
		try{
			MessageDigest md5String = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(md5String.digest(inputValue.getBytes("UTF-8")));
		}catch(Exception ex){
			ex.printStackTrace();
			return inputValue;
		}
	}
	
	public static void main(String[] args) {
		String str="516213";
		System.out.println(MD5Encoder(str));
	}

}
