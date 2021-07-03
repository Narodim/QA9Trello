package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.util.List;

public class TestBase {
    WebDriver driver;
    public static String LOGINPOSITIVE = "qa.haifa.9@gmail.com";
    public static String PASSWORDPOSITIVE = "MonitorSobaka_19";
    public static String loginNegative = "Neg" + System.currentTimeMillis() + "@gmail.com";
    public static String passwordNegative = "Frgbh9980";
    public static String boardTitle = "Trello_test";
    public static String listTitle = "Tipa krutoe nazvanie";
    public static String cardTitle = "text for a card field";


    @BeforeMethod
    public void StartUp()  {
//     ----to change language for the browser
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("lang=" + "rus");
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        driver.manage().window().maximize();
        Select languageDropdownMenu = new Select(driver.findElement(By.id("language-picker")));
        languageDropdownMenu.selectByValue("en");
        waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        driver.findElement(By.cssSelector(".text-primary")).click();
//        System.out.println("Log in button name: "+driver.findElement(By.cssSelector(".text-primary")).getText());



    }
    @AfterMethod
    public void tearDown() {
//        WebElement openMemberMenu = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
//        openMemberMenu.click();
//        Thread.sleep(3000);
//        WebElement logOut = driver.findElement(By.xpath("//nav/ul/li[8]"));
//        logOut.click();
//        Thread.sleep(3000);
//        WebElement logoutSubmit = driver.findElement(By.id("logout-submit"));
//        logoutSubmit.click();
//        Thread.sleep(3000);
//        System.out.println("Exit confirmed: "+ driver.findElement(By.xpath("//div[@class='layout-centered-content']")).getText());
        driver.quit();
    }

    public void clickAndFielding(WebElement field, String value) {
        field.click();
        field.sendKeys(value);
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsInvisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsArePresent(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
