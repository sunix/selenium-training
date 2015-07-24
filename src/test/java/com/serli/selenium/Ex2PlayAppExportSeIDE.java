package com.serli.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Ex2PlayAppExportSeIDE {

    private WebDriver driver;
    private String baseUrl = "http://localhost:9000";

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateUser() throws Exception {
        driver.get(baseUrl + "/login");
        assertTrue(isElementPresent(By.id("username")));
        try {
            driver.findElement(By.id("username")).clear();
        } catch (NoSuchElementException e) {
            
        }
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("remember")).click();
        driver.findElement(By.id("remember")).click();
        driver.findElement(By.id("signin")).click();
        driver.findElement(By.linkText("Add")).click();
        driver.findElement(By.id("object_lastname")).clear();
        driver.findElement(By.id("object_lastname")).sendKeys("toto");
        driver.findElement(By.id("object_firstname")).clear();
        driver.findElement(By.id("object_firstname")).sendKeys("titi");
        driver.findElement(By.id("object_password")).clear();
        driver.findElement(By.id("object_password")).sendKeys("tata");
        driver.findElement(By.id("object_email")).clear();
        driver.findElement(By.id("object_email")).sendKeys("tutu");
        driver.findElement(By.id("object_address")).clear();
        driver.findElement(By.id("object_address")).sendKeys("ddsdscd");
        driver.findElement(By.name("_save")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent]]
        // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent]]
        driver.findElement(By.id("object_password")).clear();
        driver.findElement(By.id("object_password")).sendKeys("tatatoto");
        driver.findElement(By.id("object_email")).clear();
        driver.findElement(By.id("object_email")).sendKeys("tutu@titi.com");
        driver.findElement(By.name("_save")).click();
        assertEquals("", driver.getTitle());
        // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent]]
        driver.findElement(By.linkText("tutu@titi.com")).click();
        driver.findElement(
                By.cssSelector("p.crudDelete > input[type=\"submit\"]"))
                .click();
        // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent]]
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void testEditUser() throws Exception {
        driver.get(baseUrl + "/login");
        assertTrue(isElementPresent(By.id("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("remember")).click();
        driver.findElement(By.id("remember")).click();
        driver.findElement(By.id("signin")).click();
        driver.findElement(By.linkText("Add")).click();
        driver.findElement(By.id("object_lastname")).clear();
        driver.findElement(By.id("object_lastname")).sendKeys("toto2");
        driver.findElement(By.id("object_firstname")).clear();
        driver.findElement(By.id("object_firstname")).sendKeys("titi2");
        driver.findElement(By.id("object_password")).clear();
        driver.findElement(By.id("object_password")).sendKeys("tatatoto");
        driver.findElement(By.id("object_email")).clear();
        driver.findElement(By.id("object_email")).sendKeys("tutu2@titi2.com");
        driver.findElement(By.id("object_address")).clear();
        driver.findElement(By.id("object_address")).sendKeys("ddsdscd");
        driver.findElement(By.name("_save")).click();
        driver.findElement(By.linkText("tutu2@titi2.com")).click();
        driver.findElement(By.id("object_email")).clear();
        driver.findElement(By.id("object_email")).sendKeys("tutu3@titi3.com");
        driver.findElement(By.name("_save")).click();
        driver.findElement(By.linkText("tutu3@titi3.com")).click();
        driver.findElement(
                By.cssSelector("p.crudDelete > input[type=\"submit\"]"))
                .click();
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}