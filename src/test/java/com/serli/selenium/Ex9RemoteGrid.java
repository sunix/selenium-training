package com.serli.selenium;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;

@RunWith(ConcurrentTestRunner.class)
public class Ex9RemoteGrid {

    private final static Logger LOG = LoggerFactory.getLogger(Ex9RemoteGrid.class);

    private WebDriver driver;
    private String baseUrl = "http://localhost:9000";
    private String browser = "chrome";
    private String remote = "true";

    @Before
    public void setUp() throws Exception {

        if (System.getProperty("selenium.browser") != null)
            browser = System.getProperty("selenium.browser");

        if (System.getProperty("selenium.baseurl") != null)
            baseUrl = System.getProperty("selenium.baseurl");

        if (System.getProperty("selenium.remote") != null)
            remote = System.getProperty("selenium.remote");

        if (!Boolean.parseBoolean(remote)) {
            switch (browser) {
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
        } else {
            DesiredCapabilities capabilities;
            if (browser != null) {
                switch (browser) {
                    case "firefox":
                        capabilities = DesiredCapabilities.firefox();
                        break;
                    case "ie":
                        capabilities = DesiredCapabilities.internetExplorer();
                        break;
                    case "chrome":
                        capabilities = DesiredCapabilities.chrome();
                        break;
                    case "htmlunit":
                        capabilities = DesiredCapabilities.htmlUnit();
                        break;
                    default: {
                        LOG.warn("Driver unknown {}. Let's use HtmlUnit.", browser);
                        capabilities = DesiredCapabilities.htmlUnit();
                    }
                }
            } else {
                LOG.warn("Driver not specified. Let's use Firefox.", browser);
                capabilities = DesiredCapabilities.firefox();
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
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
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("signin")).click();
        driver.findElement(By.linkText("Users")).click();
    }

    private void createUser(String lastname, String firstname, String password, String email, String address) {
        driver.findElement(By.linkText("Add User")).click();
        driver.findElement(By.id("object_lastname")).sendKeys(lastname);
        driver.findElement(By.id("object_firstname")).sendKeys(firstname);
        driver.findElement(By.id("object_password")).sendKeys(password);
        driver.findElement(By.id("object_email")).sendKeys(email);
        driver.findElement(By.id("object_address")).sendKeys(address);
        driver.findElement(By.name("_save")).click();
    }

    private void deleteUser(String email) {
        driver.findElement(By.linkText(email)).click();
        driver.findElement(By.cssSelector("p.crudDelete > input[type=\"submit\"]")).click();
    }

    private void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}