package com.serli.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage {
    @FindBy(id = "object_lastname")
    WebElement lastnameInput;
    @FindBy(id = "object_firstname")
    WebElement firstnameInput;

    @FindBy(id = "object_password")
    WebElement passwordInput;
    @FindBy(id = "object_email")
    WebElement emailInput;
    @FindBy(id = "object_address")
    WebElement addressInput;
    @FindBy(name = "_save")
    WebElement saveButton;
    protected WebDriver driver;

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public ManageUserPage createUser(String lastname, String firstname, String password, String email, String address) {
        lastnameInput.sendKeys(lastname);
        firstnameInput.sendKeys(firstname);
        passwordInput.sendKeys(password);
        emailInput.sendKeys(email);
        addressInput.sendKeys(address);
        saveButton.click();
        return PageFactory.initElements(driver, ManageUserPage.class);
    }

}
