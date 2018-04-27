package com.mycompany.tqshomework;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UntitledTestCase {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joaos\\Desktop\\chromedriver.exe");

        driver = new ChromeDriver();

        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("http://localhost:8080/TQSHomework/");
        driver.findElement(By.name("j_idt6:j_idt8")).click();
        driver.findElement(By.name("j_idt6:j_idt8")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | name=j_idt6:j_idt8 | ]]
        driver.findElement(By.name("j_idt6:j_idt8")).clear();
        driver.findElement(By.name("j_idt6:j_idt8")).sendKeys("5.99");
        driver.findElement(By.name("j_idt6:j_idt10")).click();
        new Select(driver.findElement(By.name("j_idt6:j_idt10"))).selectByVisibleText("AMD");
        driver.findElement(By.name("j_idt6:j_idt10")).click();
        driver.findElement(By.name("j_idt6:j_idt13")).click();
        new Select(driver.findElement(By.name("j_idt6:j_idt13"))).selectByVisibleText("BIF");
        driver.findElement(By.name("j_idt6:j_idt13")).click();
        driver.findElement(By.name("j_idt6:j_idt16")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
