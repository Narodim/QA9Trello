package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CurrentBoardTests extends TestBase {
    @BeforeTest
    public void initTest() throws InterruptedException {
        WebElement emailField = driver.findElement(By.id("user"));
        clickAndFielding(emailField, loginPositive);
        Thread.sleep(3000);
        driver.findElement(By.id("login")).click();
        WebElement passwordField = driver.findElement(By.id("password"));
        clickAndFielding(passwordField, passwordPositive);
        Thread.sleep(3000);
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        Thread.sleep(8000);
        WebElement chooseBoard = driver.findElement(By.xpath("(//div[@title='" + boardTitle + "'])"));
        chooseBoard.click();
        Thread.sleep(5000);
    }

    @Test()
    public void newBoardAdd() throws InterruptedException {
        WebElement createNewBoard = driver.findElement(By.cssSelector("div.mod-add"));
        createNewBoard.click();
        Thread.sleep(3000);
        WebElement newBoardTitle = driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']"));
        clickAndFielding(newBoardTitle, boardTitle);
        Thread.sleep(3000);
        WebElement backgroundImage = driver.findElement(By.xpath("(//ul/li/button)[2]"));
        backgroundImage.click();
        Thread.sleep(3000);
        WebElement accessDropDownMenu = driver.findElement(By.xpath("//span[@aria-label='DownIcon']"));
        accessDropDownMenu.click();
        Thread.sleep(5000);
        WebElement typeOfAccess = driver.findElement(By.xpath("(//nav/ul/li)[3]"));
        typeOfAccess.click();
        Thread.sleep(5000);
        WebElement approveButton = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Yes, make board public')]"));
        approveButton.click();
        Thread.sleep(5000);
        WebElement createButton = driver.findElement(By.xpath("//button[@data-test-id='create-board-submit-button']"));
        createButton.click();
        Thread.sleep(10000);

    }

    @Test
    public void newListAdd() throws InterruptedException {
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
    }

    @Test
    public void newCardAdd() throws InterruptedException {
        WebElement addNewCard = driver.findElement(By.cssSelector(".js-card-composer-container"));
        addNewCard.click();
        Thread.sleep(3000);
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        clickAndFielding(cardTitleField, cardTitle);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".js-add-card")).click();
        Thread.sleep(3000);


    }

    @Test
    public void listDeleting() throws InterruptedException {
        deletingListAfterCheck();
    }

    @Test
    public void listCopy()throws InterruptedException {
        copyListAfterCheck();
    }



    @Test
    public void boardDeleting() throws InterruptedException {
        WebElement showSidebar = driver.findElement(By.cssSelector("a.js-show-sidebar"));
        showSidebar.click();
        Thread.sleep(3000);
        WebElement goBackButton = driver.findElement(By.cssSelector("a.js-pop-widget-view"));
        goBackButton.click();
        Thread.sleep(3000);
        WebElement selectMoreButtonFromMenu = driver.findElement(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"));
        selectMoreButtonFromMenu.click();
        Thread.sleep(5000);
        WebElement closeBoardButton = driver.findElement(By.cssSelector("a.js-close-board"));
        closeBoardButton.click();
        Thread.sleep(5000);
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Close']"));
        submitButton.click();
        Thread.sleep(5000);

    }

}


