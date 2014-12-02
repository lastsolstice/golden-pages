package com.gp.calendar;

import java.sql.Timestamp;

public class CalendarDTO {

  
  public static enum Type{
    B2C, C2B;
  }
  
  public static enum Status{
    PENDING,ACCEPTED,DENIED;
  }
  
  
  private String id;
  private Timestamp date;
  private String inviteeId;
  private String inviterId;
  private Status status;
  private Type type;
  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Timestamp getDate() {
    return date;
  }
  public void setDate(Timestamp date) {
    this.date = date;
  }
  public String getInviteeId() {
    return inviteeId;
  }
  public void setInviteeId(String inviteeId) {
    this.inviteeId = inviteeId;
  }
  public String getInviterId() {
    return inviterId;
  }
  public void setInviterId(String inviterId) {
    this.inviterId = inviterId;
  }
  public Status getStatus() {
    return status;
  }
  public void setStatus(Status status) {
    this.status = status;
  }
  public Type getType() {
    return type;
  }
  public void setType(Type type) {
    this.type = type;
  }
  
  
  
  
}
