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
	
}
