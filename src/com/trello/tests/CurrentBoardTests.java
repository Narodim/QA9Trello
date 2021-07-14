package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentBoardPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;

import java.util.List;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    CurrentBoardPageHelper currentBoardPage;


    @BeforeMethod
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .fillInEmailFieldPos(LOGINPOSITIVE)
                .logInWithAttl()
                .fillInPasswordFieldPos(PASSWORDPOSITIVE)
                .clickSubmitButton();
    }

    @Test()
    public void newBoardAdd() {
        currentBoardPage
                        .createNewBoard()
                        .backToTheBoardsPage();
        Assert.assertEquals(
                boardTitle,currentBoardPage.receivingConfirmFromBoardsPage(),
                "Ne igraisya s kostilyami");

    }

    @Test
    public void newListAdd(){
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        currentBoardPage.addNewList();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeAfter, sizeBefore+1,
                "Something wrong with adding new list");
    }

    @Test
    public void newCardAdd(){
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                            .addNewList();
        }
        int cardsBefore = currentBoardPage.numCardsBeforeLst();
        currentBoardPage.addCardByListName();
        int cardsAfter = currentBoardPage.numCardsBeforeLst();
        Assert.assertEquals(cardsAfter, cardsBefore+1,
                "Something wrong with adding new card");
    }

    @Test
    public void listArchive(){
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                            .addNewList();
            sizeBefore++;
        }
        currentBoardPage.listArchiveByName();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeBefore-1, sizeAfter,
                "na etom nashi polnomochiya fsyo");
    }

    @Test
    public void listCopy() {
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                    .addNewList();
            sizeBefore++;
        }
        currentBoardPage.copyListByName();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeAfter, sizeBefore+1,
                "List wasn't copy");
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


