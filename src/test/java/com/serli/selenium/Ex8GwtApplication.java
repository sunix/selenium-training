package com.serli.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Ex8GwtApplication {
	private WebDriver driver;
	private String baseUrl =  "http://gwt.google.com";
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		//driver = new HtmlUnitDriver(true);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testGwt() throws Exception {
		driver.get(baseUrl + "/samples/Showcase/Showcase.html#!CwDatePicker");
		driver.findElement(By.xpath("//td/div[contains(@class,'datePickerDay') and text()='21']")).click();
		assertEquals("21 "+ driver.findElement(By.xpath("//td[@class='datePickerMonth']")).getText(), driver.findElement(By.xpath("//b[contains(text(),'Calendrier permanent')]/ancestor::tr/following-sibling::tr//div[contains(@class,'gwt-Label')]")).getText());
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
