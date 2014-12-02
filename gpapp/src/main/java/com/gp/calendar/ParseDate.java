/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.gp.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author andymartinez
 */
public class ParseDate {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Date date = parsingDate("12/09/2017", "09:05PM");
    if (date == null) {
      System.out.println("Wrong input");
    } else
      System.out.println(date.toString());
  }
  
 
  

  public static Date parsingDate(String d, String h) {

    String[] datesplited = d.split("/");

    if (datesplited.length != 3 || datesplited[0].length() != 2 || datesplited[1].length() != 2
        || datesplited[2].length() != 4)// validation
    {
      return null;
    }


    Integer month = Integer.parseInt(datesplited[0]);
    Integer day = Integer.parseInt(datesplited[1]);
    Integer year = Integer.parseInt(datesplited[2]);

    String[] hoursplited = h.split(":");
    if (hoursplited.length != 2 || hoursplited[0].length() != 2 || hoursplited[1].length() != 4)// validation
    {
      return null;
    }

    Integer hour = Integer.parseInt(hoursplited[0]);
    Integer add = 0;
    Integer minute;

    if (hoursplited[1].contains("A")) {
      minute = Integer.parseInt(hoursplited[1].substring(0, hoursplited[1].indexOf("A")));
    } else {
      minute = Integer.parseInt(hoursplited[1].substring(0, hoursplited[1].indexOf("P")));
      hour = hour + 12;
    }
    
    Date r = parsingDateHelper(year, month, day, hour, minute);// cal.getTime();
    System.out.println("Date " + r);
    return r;

  }

  private static Date parsingDateHelper(Integer year, Integer month, Integer day, Integer hour,
      Integer minute) {
    if (month > 12 || month < 1 || day < 1 || year < 0)// not sure about year
    {
      return null;
    }

    if (minute > 59 || minute < 0 || hour < 0 || hour > 24) {
      return null;
    }

    Calendar cal = Calendar.getInstance();

    if (month == 1)
      cal.set(year, Calendar.JANUARY, day, hour, minute, 0);
    else if (month == 2)
      cal.set(year, Calendar.FEBRUARY, day, hour, minute, 0);
    else if (month == 3)
      cal.set(year, Calendar.MARCH, day, hour, minute, 0);
    else if (month == 4)
      cal.set(year, Calendar.APRIL, day, hour, minute, 0);
    else if (month == 5)
      cal.set(year, Calendar.MAY, day, hour, minute, 0);
    else if (month == 6)
      cal.set(year, Calendar.JUNE, day, hour, minute, 0);
    else if (month == 7)
      cal.set(year, Calendar.JULY, day, hour, minute, 0);
    else if (month == 8)
      cal.set(year, Calendar.AUGUST, day, hour, minute, 0);
    else if (month == 9)
      cal.set(year, Calendar.SEPTEMBER, day, hour, minute, 0);
    else if (month == 10)
      cal.set(year, Calendar.OCTOBER, day, hour, minute, 0);
    else if (month == 11)
      cal.set(year, Calendar.NOVEMBER, day, hour, minute, 0);
    else if (month == 12)
      cal.set(year, Calendar.DECEMBER, day, hour, minute, 0);

    Date date = new Date();

    if (date.after(cal.getTime())) {
      return null;
    } else
      return cal.getTime();
  }
}
