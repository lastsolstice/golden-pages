package com.gp.util;

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
	
	
	
}
