package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CurrentBoardTests extends TestBase {
    @BeforeMethod
    public void initTest() {
        waitUntilElementIsClickable(By.id("user"), 10);
        WebElement emailField = driver.findElement(By.id("user"));
        clickAndFielding(emailField,LOGINPOSITIVE);
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("password"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        clickAndFielding(passwordField,PASSWORDPOSITIVE);
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        waitUntilElementIsClickable(By.xpath("//ul[@class='boards-page-board-section-list']"), 10);
    }

    @Test()
    public void newBoardAdd() {

        WebElement createNewBoard = driver.findElement(By.cssSelector("div.mod-add"));
        createNewBoard.click();

        waitUntilElementIsClickable(By.xpath("//input[@data-test-id='create-board-title-input']"), 10);
        WebElement newBoardTitle = driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']"));
        clickAndFielding(newBoardTitle, boardTitle);


        WebElement backgroundImage = driver.findElement(By.xpath("(//ul/li/button)[2]"));
        backgroundImage.click();

        WebElement accessDropDownMenu = driver.findElement(By.xpath("//span[@aria-label='DownIcon']"));
        accessDropDownMenu.click();

        waitUntilElementIsClickable(By.xpath("(//nav/ul/li)[3]"), 10);
        WebElement typeOfAccess = driver.findElement(By.xpath("(//nav/ul/li)[3]"));
        typeOfAccess.click();

        waitUntilElementIsClickable(By.xpath("//button[@type='button'][contains(text(),'Yes, make board public')]"), 10);
        WebElement approveButton = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Yes, make board public')]"));
        approveButton.click();

        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='create-board-submit-button']"), 10);
        WebElement createButton = driver.findElement(By.xpath("//button[@data-test-id='create-board-submit-button']"));
        createButton.click();



        waitUntilElementIsClickable(By.xpath("//a[@aria-label='Cancel list editing']"), 10);
        WebElement cancelingNewList = driver.findElement(By.xpath("//a[@aria-label='Cancel list editing']"));
        cancelingNewList.click();

        WebElement homeIcon = driver.findElement(By.xpath("//span[@aria-label='HouseIcon']"));
        homeIcon.click();

        waitUntilElementIsClickable(By.xpath("//div[@class='board-tile-details-name']"), 10);
        String actualBoardTitle = driver.findElement(By.xpath("//div[@class='board-tile-details-name']")).getText();
        Assert.assertEquals(boardTitle,actualBoardTitle,"Ne igraysya s kostilyami");

    }

    @Test
    public void newListAdd() {

        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        chooseBoard.click();

        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
        waitUntilElementIsClickable(By.cssSelector("div.js-add-list"), 10);
        List<WebElement> listSizeBefore = driver.findElements(By.cssSelector(".js-list"));
        WebElement addAnotherList = driver.findElement(By.cssSelector("div.js-add-list"));
        addAnotherList.click();

        waitUntilElementIsClickable(By.cssSelector("input.list-name-input"), 10);
        WebElement listTitleField = driver.findElement(By.cssSelector("input.list-name-input"));
        listTitleField.sendKeys(listTitle);

        waitUntilElementIsClickable(By.cssSelector("input.js-save-edit"), 10);
        WebElement addListButton = driver.findElement(By.cssSelector("input.js-save-edit"));
        addListButton.click();
        waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);
        List<WebElement> listSizeAfter = driver.findElements(By.cssSelector(".js-list"));

        waitUntilElementIsClickable(By.xpath("//a[@aria-label='Cancel list editing']"), 10);
        WebElement cancelingNewList = driver.findElement(By.xpath("//a[@aria-label='Cancel list editing']"));
        cancelingNewList.click();

        Assert.assertEquals(listSizeAfter.size(), listSizeBefore.size()+1, "You can make this easier");

    }

    @Test
    public void newCardAdd() {
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='" + boardTitle + "']"));
        chooseBoard.click();

        waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);

        List<WebElement> nameOfList = driver.findElements(By.cssSelector(".js-list"));
        List<String> nameOfListArr = new ArrayList<>();
        nameOfList.stream().map(WebElement::getText).forEach(nameOfListArr::add);
        for (String name : nameOfListArr) {
            if (name.contains(listTitle)) {
                waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);
                List<WebElement> listAction = driver.findElements(By.xpath("//a[@aria-label='List actions']"));
                int indexNum = nameOfListArr.indexOf(name);
                listAction.get(indexNum).click();

                List<WebElement> numCardsBefore = driver.findElements(By.cssSelector(".ui-droppable"));
                waitUntilElementIsClickable(By.cssSelector(".js-add-card"), 10);
                WebElement addCard = driver.findElement(By.cssSelector(".js-add-card"));
                addCard.click();
                waitUntilElementIsClickable(By.cssSelector(".js-card-title"), 10);
                WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
                clickAndFielding(cardTitleField, cardTitle);

                waitUntilElementIsClickable(By.cssSelector(".js-add-card"), 10);
                driver.findElement(By.cssSelector(".js-add-card")).click();

                waitUntilElementIsClickable(By.cssSelector(".js-cancel"), 10);
                WebElement cancelOperation = driver.findElement(By.cssSelector(".js-cancel"));
                cancelOperation.click();

                waitUntilElementIsClickable(By.cssSelector(".placeholder"), 10);
                List<WebElement> numCardsAfter = driver.findElements(By.cssSelector(".ui-droppable"));

                Assert.assertNotEquals(numCardsBefore.size(), numCardsAfter.size(), "Something wrong with add card");
            }
        }
    }




    @Test
    public void listDeleting() {
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        chooseBoard.click();
        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);

        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list"));
        if(listSize.size()==0){
            waitUntilElementIsClickable(By.cssSelector("div.js-add-list"), 5);
            WebElement addAnotherList = driver.findElement(By.cssSelector("div.js-add-list"));
            addAnotherList.click();

            waitUntilElementIsClickable(By.cssSelector("input.list-name-input"), 10);
            WebElement listTitleField = driver.findElement(By.cssSelector("input.list-name-input"));
            listTitleField.sendKeys(listTitle);

            waitUntilElementIsClickable(By.cssSelector("input.js-save-edit"), 10);
            WebElement addListButton = driver.findElement(By.cssSelector("input.js-save-edit"));
            addListButton.click();

            waitUntilElementIsClickable(By.xpath("//a[@aria-label='Cancel list editing']"), 10);
            WebElement cancelingNewList = driver.findElement(By.xpath("//a[@aria-label='Cancel list editing']"));
            cancelingNewList.click();

            waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
            List<WebElement> listAfter= driver.findElements(By.cssSelector(".js-list"));

            Assert.assertNotEquals(listSize.size(),listAfter.size(), "v dushe ne ebu");
        }

        waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);
        List<WebElement> listBefore= driver.findElements(By.cssSelector(".js-list"));

        waitUntilElementIsClickable(By.xpath("//a[@aria-label='List actions']"), 10);
        WebElement listAction = driver.findElement(By.xpath("//a[@aria-label='List actions']"));
        listAction.click();

        waitUntilElementIsClickable(By.cssSelector(".js-close-list"), 10);
        WebElement closeList = driver.findElement(By.cssSelector(".js-close-list"));
        closeList.click();

        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
        List<WebElement> listAfter = driver.findElements(By.cssSelector(".js-list"));

        Assert.assertNotEquals(listBefore.size(), listAfter.size(), "Check it");
    }

    @Test
    public void listCopy() {
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        chooseBoard.click();
        waitUntilElementIsClickable(By.cssSelector(".ui-sortable"), 10);

        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
        List<WebElement> listSize= driver.findElements(By.cssSelector(".js-list"));
        if(listSize.size()==0){
            waitUntilElementIsClickable(By.cssSelector("div.js-add-list"),10);
            WebElement addAnotherList = driver.findElement(By.cssSelector("div.js-add-list"));
            addAnotherList.click();

            waitUntilElementIsClickable(By.cssSelector("input.list-name-input"), 10);
            WebElement listTitleField = driver.findElement(By.cssSelector("input.list-name-input"));
            listTitleField.sendKeys(listTitle);

            waitUntilElementIsClickable(By.cssSelector("input.js-save-edit"), 10);
            WebElement addListButton = driver.findElement(By.cssSelector("input.js-save-edit"));
            addListButton.click();

            waitUntilElementIsClickable(By.xpath("//a[@aria-label='Cancel list editing']"), 10);
            WebElement cancelingNewList = driver.findElement(By.xpath("//a[@aria-label='Cancel list editing']"));
            cancelingNewList.click();
        }

        waitUntilElementIsClickable(By.xpath("//a[@aria-label='List actions']"), 10);
        WebElement listAction = driver.findElement(By.xpath("//a[@aria-label='List actions']"));
        listAction.click();

        waitUntilElementIsClickable(By.cssSelector(".js-copy-list"), 10);
        WebElement copyList = driver.findElement(By.cssSelector(".js-copy-list"));
        copyList.click();

        waitUntilElementIsClickable(By.xpath("//textarea[@class='js-autofocus']"), 10);
        WebElement nameListAfterCopy = driver.findElement(By.xpath("//textarea[@class='js-autofocus']"));
        nameListAfterCopy.click();
        nameListAfterCopy.clear();
        nameListAfterCopy.sendKeys("Copy of : "+listTitle);

        waitUntilElementIsClickable(By.xpath("//input[@value='Create list']"), 10);
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Create list']"));
        submitButton.click();

        Assert.assertEquals("Copy of", "Copy of", "List wasn't copy");
    }


    @Test
    public void boardDeleting() {
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        chooseBoard.click();

        waitUntilElementIsClickable(By.cssSelector("a.js-show-sidebar"), 10);
        WebElement showSidebar = driver.findElement(By.cssSelector("a.js-show-sidebar"));
        showSidebar.click();

        waitUntilElementIsVisible(By.cssSelector(".js-pop-widget-view"), 10);
        WebElement goBackButton = driver.findElement(By.cssSelector("a.js-pop-widget-view"));
        goBackButton.click();

        waitUntilElementIsClickable(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"), 10);
        WebElement selectMoreButtonFromMenu = driver.findElement(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"));
        selectMoreButtonFromMenu.click();

        waitUntilElementIsClickable(By.cssSelector("a.js-close-board"), 10);
        WebElement closeBoardButton = driver.findElement(By.cssSelector("a.js-close-board"));
        closeBoardButton.click();

        waitUntilElementIsClickable(By.xpath("//input[@value='Close']"), 10);
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Close']"));
        submitButton.click();
    }
}


