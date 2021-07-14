package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePageHelper;
import pages.LoginPageHelper;


public class TestBase {
    WebDriver driver;
    HomePageHelper homePage;
    public static String LOGINPOSITIVE = "qa.haifa.9@gmail.com";
    public static String PASSWORDPOSITIVE = "MonitorSobaka_19";
    public static String loginNegative = "Neg" + System.currentTimeMillis() + "@gmail.com";
    public static String passwordNegative = "Frgbh9980";
    public static String boardTitle = "Monday";
    public static String listTitle = "some text";
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
//        waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
//        driver.findElement(By.cssSelector(".text-primary")).click();
//        System.out.println("Log in button name: "+driver.findElement(By.cssSelector(".text-primary")).getText());
        homePage = PageFactory.initElements(driver, HomePageHelper .class);
        homePage.waitUntilPageIsLoaded();



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

}
