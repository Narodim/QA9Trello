package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.List;

public class TestBase {
    WebDriver driver;
    String loginPositive = "qa.haifa.9@gmail.com";
    String passwordPositive = "MonitorSobaka_19";
    String loginNegative = "Neg" + System.currentTimeMillis() + "@gmail.com";
    String passwordNegative = "Frgbh9980";
    String boardTitle = "QA_Haifa_9_Trello";
    String listTitle = "name for new list";
    String cardTitle = "text for card field";

    @BeforeTest
    public void StartUp() throws InterruptedException {
//     ----to change language for the browser
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("lang=" + "rus");
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        Select languageDropdownMenu = new Select(driver.findElement(By.id("language-picker")));
        languageDropdownMenu.selectByValue("en");
        Thread.sleep(3000);
//        System.out.println("Log in button name: "+driver.findElement(By.cssSelector(".text-primary")).getText());
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);


    }
    @AfterTest
    public void tearDown() throws InterruptedException {
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
