package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentBoardPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    CurrentBoardPageHelper currentBoardPage;

    @BeforeMethod
    public void initTest() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);


        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.fillInEmailFieldPos(LOGINPOSITIVE);
        loginPage.logInWithAttl();
        loginPage.fillInPasswordFieldPos(PASSWORDPOSITIVE);
        loginPage.clickSubmitButton();
    }

    @Test()
    public void newBoardAdd() {

        currentBoardPage.createNewBoard();
        currentBoardPage.backgroundImage();
        currentBoardPage.accessDropDownMenu();
        currentBoardPage.typeOfAccess();
        currentBoardPage.approveButton();
        currentBoardPage.createButton();
        currentBoardPage.cancelingNewList();
        currentBoardPage.backToTheBoardsPage();
        currentBoardPage.receivingConfirmForBoard();
    }

    @Test
    public void newListAdd(){

        currentBoardPage.chooseBoard();
        int beforeSize = currentBoardPage.listSizeBefore();
        currentBoardPage.addNewList();
        currentBoardPage.listTitleField();
        currentBoardPage.addListButton();
        currentBoardPage.cancelingNewList();
        int afterSize = currentBoardPage.listSizeAfter();
        Assert.assertEquals(afterSize, beforeSize+1);


    }

    @Test
    public void newCardAdd() throws InterruptedException {
        currentBoardPage.chooseBoard();
        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list-content"));
//        int sizeOfList = listSize.size();
        if(listSize.size()==0){
            currentBoardPage.addNewList();
            currentBoardPage.listTitleField();
            currentBoardPage.addListButton();
//            waitUntilElementBecame(By.cssSelector(".js-list-content"), sizeOfList+1,10);
            Thread.sleep(3000);
            currentBoardPage.cancelingNewList();
        }
        currentBoardPage.addCardByListName();
    }

    @Test
    public void listArchive() throws InterruptedException {
        currentBoardPage.chooseBoard();
        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list"));
        if(listSize.size()==0){
            currentBoardPage.addNewList();
            currentBoardPage.listTitleField();
            currentBoardPage.addListButton();
            Thread.sleep(3000);
            currentBoardPage.cancelingNewList();
        }
        currentBoardPage.listArchiveByName();
    }

    @Test
    public void listCopy() throws InterruptedException {
        currentBoardPage.chooseBoard();
        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list-content"));
        if(listSize.size()==0){
            currentBoardPage.addNewList();
            currentBoardPage.listTitleField();
            currentBoardPage.addListButton();
            Thread.sleep(3000);
            currentBoardPage.cancelingNewList();
        }
        currentBoardPage.copyListByName();

    }


//    @Test
//    public void boardDeleting() {
//        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
//        chooseBoard.click();
//
//        waitUntilElementIsClickable(By.cssSelector("a.js-show-sidebar"), 10);
//        WebElement showSidebar = driver.findElement(By.cssSelector("a.js-show-sidebar"));
//        showSidebar.click();
//
//        waitUntilElementIsVisible(By.cssSelector(".js-pop-widget-view"), 10);
//        WebElement goBackButton = driver.findElement(By.cssSelector("a.js-pop-widget-view"));
//        goBackButton.click();
//
//        waitUntilElementIsClickable(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"), 10);
//        WebElement selectMoreButtonFromMenu = driver.findElement(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"));
//        selectMoreButtonFromMenu.click();
//
//        waitUntilElementIsClickable(By.cssSelector("a.js-close-board"), 10);
//        WebElement closeBoardButton = driver.findElement(By.cssSelector("a.js-close-board"));
//        closeBoardButton.click();
//
//        waitUntilElementIsClickable(By.xpath("//input[@value='Close']"), 10);
//        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Close']"));
//        submitButton.click();
//    }
}


