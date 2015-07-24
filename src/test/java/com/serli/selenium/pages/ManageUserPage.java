package com.serli.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ManageUserPage {

    protected WebDriver driver;

    public ManageUserPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isUserEmailDisplayed(String email) {
        try {
            driver.findElement(By.linkText(email));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public EditUserPage editUser(String email) {
        driver.findElement(By.linkText(email)).click();
        return PageFactory.initElements(driver, EditUserPage.class);
    }

}
