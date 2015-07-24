package com.serli.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(linkText = "Add")
    WebElement addLink;
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public AddUserPage gotoAddUserPage() {
        addLink.click();
        return PageFactory.initElements(driver, AddUserPage.class);
    }
}
