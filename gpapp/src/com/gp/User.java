package com.gp;

public class User {

	public static enum Type{
		BIZ_USER, CONS_USER;
	}
	
	private String uid;
	private String username;
	private Type type;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
	
	//private String password;
	
	
	
	
}
