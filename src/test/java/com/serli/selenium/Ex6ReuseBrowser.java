package com.serli.selenium;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ex6ReuseBrowser {

    private static WebDriver driver;
    private static String baseUrl = "http://localhost:9000";
    private static String browser = "firefox";

    @BeforeClass
    public static void setUp() throws Exception {
        if (System.getProperty("selenium.browser") != null)
            browser = System.getProperty("selenium.browser");

        if (System.getProperty("selenium.baseurl") != null)
            baseUrl = System.getProperty("selenium.baseurl");

        switch (browser ) {
            default:
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "C:\\formations\\selenium\\Selenium 2\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            case "htmlunit":
                driver = new HtmlUnitDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\formations\\selenium\\Selenium 2\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateUserFail() throws Exception {
        login("admin", "admin");
        createUser("to", "titi", "tatatoto", "tutu", "ddsdscd");
        
        if (driver instanceof TakesScreenshot){
            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File fTo = new File("target/testUserFail.png");
            FileUtils.copyFile(f, fTo);
        }
    }
    
    
    @Test
    public void testCreateUser() throws Exception {
        login("admin", "admin");
        createUser("toto", "titi", "tatatoto", "tutu@titi.com", "ddsdscd");
        deleteUser("tutu@titi.com");
        logout();
    }

    @Test
    public void testCreateUser2() throws Exception {
        login("admin", "admin");
        createUser("bla", "bulle", "tatatoto", "baba@pilouf.com", "ddsdscd");
        deleteUser("baba@pilouf.com");
        logout();
    }

    @Test
    public void testCreateUser3() throws Exception {
        login("admin", "admin");
        createUser("pouf", "boule", "tatatoto", "pif@pouf.com", "ddsdscd");
        deleteUser("pif@pouf.com");
        logout();
    }

    private void login(String username, String password) {
        driver.get(baseUrl + "/login");
        findAndWait(By.id("username"), 10).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("signin")).click();
        findAndWait(By.linkText("Users"), 10).click();
        assertTrue(isElementPresent(By.xpath("//p[@class='crudCount']"), 10));
    }

    private void createUser(String lastname, String firstname, String password, String email, String address) {
        driver.findElement(By.linkText("Add User")).click();
        findAndWait(By.id("object_lastname"), 10).sendKeys(lastname);
        driver.findElement(By.id("object_firstname")).sendKeys(firstname);
        driver.findElement(By.id("object_password")).sendKeys(password);
        driver.findElement(By.id("object_email")).sendKeys(email);
        driver.findElement(By.id("object_address")).sendKeys(address);
        driver.findElement(By.name("_save")).click();
    }

    private void deleteUser(String email) {
        driver.findElement(By.linkText(email)).click();
        findAndWait(By.cssSelector("p.crudDelete > input[type=\"submit\"]"), 10).click();
        assertTrue(isElementPresent(By.xpath("//p[@class='crudCount']"), 10));
    }

    private void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * Finding element with explicit or implicit wait.
     */
    private WebElement findAndWait(final By by, int timeout) {
        // Explicit wait if you want greater timeout than implicit
        WebElement myDynamicElement = (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
        return myDynamicElement;
    }

    private boolean isElementPresent(By by, int timeout) {
        try {
            findAndWait(by, timeout);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}