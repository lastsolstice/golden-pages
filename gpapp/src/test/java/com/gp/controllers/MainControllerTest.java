package com.gp.controllers;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.*;


public class MainControllerTest {

	@Test
	public void login(){
		
		String expected = "HelloWorld";
		String actual = "HelloWorld";
		
		Assert.assertEquals(expected, actual);
		
	}
	
//	@Test
//	public void mainpg(){
//
//		WebDriver driver = new HtmlUnitDriver();
//		
//		driver.get("http://localhost:8080/gpapp/");				//webpage
//		
//		assertTrue( driver.getTitle().startsWith("Golden Pages"));
//		//driver. findElement("bname");
//	}
	
	
}
