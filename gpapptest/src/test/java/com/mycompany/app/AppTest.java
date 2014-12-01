package com.mycompany.app;

import javax.swing.text.html.CSS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * 
     */
    public void ConsumerSignUp()
    {
    	WebDriver driver = new HtmlUnitDriver();
    	driver.get("http://localhost:8080/gpapp");
    	System.out.println("Page title " + driver.getTitle());
    	WebElement element = driver.findElement(By.id("full-name"));
    	element.sendKeys("rihihana");
    	
    	element = driver.findElement(By.id("username"));
    	element.sendKeys("rihihana");

    	element = driver.findElement(By.id("email"));
    	element.sendKeys("rihihana@rihihana.com");

    	element = driver.findElement(By.id("password"));
    	element.sendKeys("123");

    	element = driver.findElement(By.id("password-confirm"));
    	element.sendKeys("123");
    	
    	element.submit();
    	System.out.println("Page title " + driver.getTitle());
        //assertTrue( true );
    }
    
    /**
     * 
     */
    public void ConsumerSignUpError()
    {
    	WebDriver driver = new HtmlUnitDriver();
    	driver.get("http://localhost:8080/gpapp");
    	WebElement element = driver.findElement(By.id("full-name"));
    	element.sendKeys("rihihana");
    	
    	element = driver.findElement(By.id("username"));
    	element.sendKeys("rihihana");

    	element = driver.findElement(By.id("email"));
    	element.sendKeys("rihihana@rihihana.com");

    	element = driver.findElement(By.id("password"));
    	element.sendKeys("123");

    	element = driver.findElement(By.id("password-confirm"));
    	element.sendKeys("123");
    	
    	element.submit();
    	
    	//element = driver.findElement(By.tagName("message_box"));
    	System.out.println("Page Title " + driver.getTitle()); //element.getText());
        //assertTrue( true );
    }
}
