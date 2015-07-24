package com.serli.selenium;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Ex7TakesScreenshot {
	private WebDriver driver;
	private String baseUrl = "http://www.google.fr";
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testGoogle2() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("gbqfq")).clear();
		driver.findElement(By.id("gbqfq")).sendKeys("selenium 2");
		driver.findElement(By.id("gbqfb")).click();
		assertEquals("Selenium - Web Browser Automation", driver.findElement(By.linkText("Selenium - Web Browser Automation")).getText());
		driver.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		
		if (driver instanceof TakesScreenshot){
    		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    		File fTo = new File("target/test.png");
    		FileUtils.copyFile(f, fTo);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}