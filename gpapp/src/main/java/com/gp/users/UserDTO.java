package com.gp.users;

import java.util.List;

import com.gp.calendar.CalendarDTO;

public class UserDTO {

	public static enum Type{
		BIZ_USER, CONS_USER;
	}
	
	public static enum ColumnName{
		EMAIL, USERNAME, PASSWORD
	}
	
	private String uid;
	private String username;
	private String fullName;
	private String email;
	private String password;
	private Type type;
	
	//private List<CalendarDTO> appts;
	
	
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
	
	//private String password;
	
	
	
	
}
