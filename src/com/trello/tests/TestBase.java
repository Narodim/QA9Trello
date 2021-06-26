package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        Select languageDropdownMenu = new Select(driver.findElement(By.id("language-picker")));
        languageDropdownMenu.selectByValue("en");
        Thread.sleep(3000);
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

    public void clickAndFielding(WebElement field, String value) {
        field.click();
        field.sendKeys(value);
    }

    public void deletingListAfterCheck() throws InterruptedException {
        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list"));

        if(listSize.size()>0){
            WebElement listAction = driver.findElement(By.xpath("//a[@aria-label='List actions']"));
            listAction.click();
            Thread.sleep(3000);
            WebElement closeList = driver.findElement(By.cssSelector(".js-close-list"));
            closeList.click();
            Thread.sleep(3000);


        } else{
            WebElement addAnotherList = driver.findElement(By.cssSelector("div.js-add-list"));
            addAnotherList.click();
            Thread.sleep(3000);
            WebElement listTitleField = driver.findElement(By.cssSelector("input.list-name-input"));
            listTitleField.sendKeys(listTitle);
            Thread.sleep(3000);
            WebElement addListButton = driver.findElement(By.cssSelector("input.js-save-edit"));
            addListButton.click();
            Thread.sleep(3000);
            WebElement cancelingNewList = driver.findElement(By.xpath("//a[@aria-label='Cancel list editing']"));
            cancelingNewList.click();
            Thread.sleep(3000);
            WebElement listAction = driver.findElement(By.xpath("//a[@aria-label='List actions']"));
            listAction.click();
            Thread.sleep(3000);
            WebElement closeList = driver.findElement(By.cssSelector(".js-close-list"));
            closeList.click();
            Thread.sleep(3000);
        }
    }

    public void copyListAfterCheck() throws InterruptedException {
        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list"));

        if(listSize.size()>0){
            WebElement listAction = driver.findElement(By.xpath("//a[@aria-label='List actions']"));
            listAction.click();
            Thread.sleep(3000);
            WebElement closeList = driver.findElement(By.cssSelector(".js-copy-list"));
            closeList.click();
            Thread.sleep(3000);
            WebElement nameListAfterCopy = driver.findElement(By.xpath("//textarea[@class='js-autofocus']"));
            nameListAfterCopy.click();
            nameListAfterCopy.clear();
            nameListAfterCopy.sendKeys("Copy of : "+listTitle);
            Thread.sleep(3000);


        } else{
            WebElement addAnotherList = driver.findElement(By.cssSelector("div.js-add-list"));
            addAnotherList.click();
            Thread.sleep(3000);
            WebElement listTitleField = driver.findElement(By.cssSelector("input.list-name-input"));
            listTitleField.sendKeys(listTitle);
            Thread.sleep(3000);
            WebElement addListButton = driver.findElement(By.cssSelector("input.js-save-edit"));
            addListButton.click();
            Thread.sleep(3000);
            WebElement cancelingNewList = driver.findElement(By.xpath("//a[@aria-label='Cancel list editing']"));
            cancelingNewList.click();
            Thread.sleep(3000);
            WebElement listAction = driver.findElement(By.xpath("//a[@aria-label='List actions']"));
            listAction.click();
            Thread.sleep(3000);
            WebElement closeList = driver.findElement(By.cssSelector(".js-copy-list"));
            closeList.click();
            Thread.sleep(3000);
            WebElement nameListAfterCopy = driver.findElement(By.xpath("//textarea[@class='js-autofocus']"));
            nameListAfterCopy.click();
            nameListAfterCopy.clear();
            nameListAfterCopy.sendKeys("Copy of : "+listTitle);
            Thread.sleep(3000);
            WebElement submitButton = driver.findElement(By.xpath("//input[@value='Create list']"));
            submitButton.click();
            Thread.sleep(3000);
        }
    }
}
