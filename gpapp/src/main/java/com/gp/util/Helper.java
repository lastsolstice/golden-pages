package com.gp.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class Helper {

	//return true if there no value null or empty
	public static boolean isValid(String ... values){
		for(String v : values){
			if (v == null || v.isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	public static String randomString(){
		return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
	}
	
	public static String md5(String in){
		return DigestUtils.md5Hex(in);
	}
	
	
}
