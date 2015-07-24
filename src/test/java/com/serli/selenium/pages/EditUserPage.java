package com.serli.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditUserPage {

    @FindBy(css = "input[value=\"Delete User\"]")
    WebElement deleteButton;

    public void deleteUser() {
        deleteButton.click();
    }

}
