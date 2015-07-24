package com.serli.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.serli.selenium.pages.AddUserPage;
import com.serli.selenium.pages.EditUserPage;
import com.serli.selenium.pages.HomePage;
import com.serli.selenium.pages.LoginPage;
import com.serli.selenium.pages.ManageUserPage;

public class Ex10PageObject {
    WebDriver driver;
    String baseUrl = "http://localhost:9000/";

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @Test
    public void testLoginPage() {
        driver.get(baseUrl);
        LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
        HomePage homepage = loginpage.login("admin", "admin");
        Assert.assertEquals("Choose the object to edit", driver.findElement(By.tagName("h2")).getText());
        AddUserPage addUserPage = homepage.gotoAddUserPage(); 
        Assert.assertEquals("Add User", driver.findElement(By.id("crudBlankTitle")).getText());
        // We go a bit further
        ManageUserPage manageUserPage = addUserPage.createUser("Doe", "John", "password", "john@doe.com", "123 sturt street");
        Assert.assertTrue(manageUserPage.isUserEmailDisplayed("john@doe.com"));
        EditUserPage editPage = manageUserPage.editUser("john@doe.com");
        editPage.deleteUser();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
