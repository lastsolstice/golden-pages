package com.gp.business;

public class BusinessDTO {

	public static enum ColumnName{
		BUSINESSNAME;
	}
	
	
	private String bid;
	private String description;
	private String businessName;
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	
	
}
