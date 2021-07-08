package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CurrentBoardPageHelper extends PageBase{
    String boardTitle;

    public CurrentBoardPageHelper(WebDriver driver, String boardName){
        this.driver = driver;
        this.boardTitle = boardName;
    }


//===============================================CREATE NEW BOARD=======================================================
    public void createNewBoard() {
        WebElement createNewBoard = driver.findElement(By.cssSelector("div.mod-add"));
        createNewBoard.click();
        waitUntilElementIsClickable(By.xpath("//input[@data-test-id='create-board-title-input']"), 10);
        WebElement newBoardTitle = driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']"));
        clickAndFielding(newBoardTitle, boardTitle);
    }

    public void backgroundImage() {
        WebElement backgroundImage = driver.findElement(By.xpath("(//ul/li/button)[2]"));
        backgroundImage.click();
    }

    public void accessDropDownMenu() {
        WebElement accessDropDownMenu = driver.findElement(By.xpath("//span[@aria-label='DownIcon']"));
        accessDropDownMenu.click();
    }

    public void typeOfAccess() {
        waitUntilElementIsClickable(By.xpath("(//nav/ul/li)[3]"), 10);
        WebElement typeOfAccess = driver.findElement(By.xpath("(//nav/ul/li)[3]"));
        typeOfAccess.click();
    }

    public void approveButton() {
        waitUntilElementIsClickable(By.xpath("//button[@type='button'][contains(text(),'Yes, make board public')]"), 10);
        WebElement approveButton = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Yes, make board public')]"));
        approveButton.click();
    }

    public void createButton() {
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='create-board-submit-button']"), 10);
        WebElement createButton = driver.findElement(By.xpath("//button[@data-test-id='create-board-submit-button']"));
        createButton.click();
    }

    public void cancelingNewList() {
        waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"), 10);
        WebElement cancelingNewList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelingNewList.click();

    }

    public void backToTheBoardsPage() {
        WebElement homeIcon = driver.findElement(By.xpath("//span[@aria-label='HouseIcon']"));
        homeIcon.click();
    }

    public void receivingConfirmForBoard() {
        waitUntilElementIsClickable(By.xpath("//div[@class='board-tile-details-name']"), 10);
        String actualBoardTitle = driver.findElement(By.xpath("//div[@class='board-tile-details-name']")).getText();
        Assert.assertEquals(boardTitle,actualBoardTitle,"Ne igraysya s kostilyami");
    }

//=============================================ADD NEW LIST==========================================================================

    public void chooseBoard() {
        waitUntilAllElementsArePresent(By.cssSelector(".all-boards"), 10);
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        chooseBoard.click();
        waitUntilElementIsVisible(By.cssSelector("#board"), 10);
    }

    public int listSizeBefore() {
        List<WebElement> listSizeBefore = driver.findElements(By.cssSelector(".js-list-content"));
        return listSizeBefore.size();
    }

    public void addNewList() {
        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
        waitUntilElementIsClickable(By.cssSelector("div.js-add-list"), 10);
        WebElement addAnotherList = driver.findElement(By.cssSelector("div.js-add-list"));
        addAnotherList.click();
    }

    public void listTitleField() {
        waitUntilElementIsClickable(By.cssSelector("input.list-name-input"), 10);
        WebElement listTitleField = driver.findElement(By.cssSelector("input.list-name-input"));
        listTitleField.sendKeys(listTitle);
    }

    public void addListButton() {
        waitUntilElementIsClickable(By.cssSelector("input.js-save-edit"), 10);
        WebElement addListButton = driver.findElement(By.cssSelector("input.js-save-edit"));
        addListButton.click();
        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
    }

    public int listSizeAfter() {
        List<WebElement> listSizeAfter = driver.findElements(By.cssSelector(".js-list-content"));
        return listSizeAfter.size();
    }
//    public void receivingConfirmForList() {
//        int beforeSize = listSizeBefore();
//        int afterSize = listSizeAfter();
//      Assert.assertTrue(afterSize>beforeSize);
//    }


//======================================================ADD NEW CARD BY LIST NAME==================================================
        public void addCardByListName() {
        List<WebElement> numCardsBefore = driver.findElements(By.cssSelector(".ui-droppable"));
        int numberOfCardBeforeAdd = numCardsBefore.size();
        WebElement listActionButton = driver.findElement
                (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        listActionButton.click();
        waitUntilElementIsVisible(By.cssSelector(".no-back"), 10);
        waitUntilElementIsClickable(By.cssSelector(".js-add-card"),10);
        WebElement addCard = driver.findElement(By.xpath("//*[@class='js-add-card']"));
        addCard.click();
        waitUntilElementIsClickable(By.cssSelector(".js-card-title"), 10);
        WebElement textForCardTitle = driver.findElement(By.cssSelector(".js-card-title"));
        textForCardTitle.sendKeys(cardTitle);
        waitUntilElementIsClickable(By.xpath("//*[@value='Add card']"), 10);
        WebElement addCardSubmitButton = driver.findElement(By.xpath("//*[@value='Add card']"));
        addCardSubmitButton.click();
        waitUntilElementBecame(By.cssSelector(".ui-droppable"),numberOfCardBeforeAdd+1, 10 );
        WebElement cancelButton = driver.findElement(By.cssSelector(".js-cancel"));
        cancelButton.click();
        List<WebElement> numCardsAfter = driver.findElements(By.cssSelector(".ui-droppable"));
        waitUntilElementIsInvisible(By.cssSelector(".js-cancel"), 10);
        WebElement homeIcon = driver.findElement(By.xpath("//*[@aria-label='HouseIcon']"));
        homeIcon.click();
        waitUntilAllElementsArePresent(By.cssSelector(".all-boards"), 10);
        Assert.assertEquals(numCardsAfter.size(), numCardsBefore.size()+1, "Something wrong with add card");
    }

    //    public void newCardAddByListName() {
//        List<WebElement> nameOfList = driver.findElements(By.cssSelector(".js-list"));
//        List<String> nameOfListArr = new ArrayList<>();
//        nameOfList.stream().map(WebElement::getText).forEach(nameOfListArr::add);
//        for (String name : nameOfListArr) {
//            if (name.contains(listTitle)) {
//                waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);
//                List<WebElement> listAction = driver.findElements(By.xpath("//a[@aria-label='List actions']"));
//                int indexNum = nameOfListArr.indexOf(name);
//                listAction.get(indexNum).click();
//
//                List<WebElement> numCardsBefore = driver.findElements(By.cssSelector(".ui-droppable"));
//                waitUntilElementIsClickable(By.cssSelector(".js-add-card"), 10);
//                WebElement addCard = driver.findElement(By.cssSelector(".js-add-card"));
//                addCard.click();
//                waitUntilElementIsClickable(By.cssSelector(".js-card-title"), 10);
//                WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
//                clickAndFielding(cardTitleField, cardTitle);
//
//                waitUntilElementIsClickable(By.cssSelector(".js-add-card"), 10);
//                driver.findElement(By.cssSelector(".js-add-card")).click();
//
//                waitUntilElementIsClickable(By.cssSelector(".js-cancel"), 10);
//                WebElement cancelOperation = driver.findElement(By.cssSelector(".js-cancel"));
//                cancelOperation.click();
//
//                waitUntilElementIsClickable(By.cssSelector(".placeholder"), 10);
//                List<WebElement> numCardsAfter = driver.findElements(By.cssSelector(".ui-droppable"));
//
//                Assert.assertEquals(numCardsAfter.size(), numCardsBefore.size()+1, "Something wrong with add card");
//            }
//        }
//    }
//==============================================ARCHIVE LIST BY NAME===============================================================
    public void listArchiveByName() {
        waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);
        List<WebElement> listBefore= driver.findElements(By.cssSelector(".js-list"));
        WebElement listActionButton = driver.findElement
                (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        listActionButton.click();
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"), 10);
        WebElement closeList = driver.findElement(By.cssSelector(".js-close-list"));
        closeList.click();
        waitUntilAllElementsArePresent(By.cssSelector(".placeholder"), 10);
        List<WebElement> listAfter = driver.findElements(By.cssSelector(".js-list"));
        Assert.assertEquals(listBefore.size()-1, listAfter.size() , "Check it");
    }




//=================================================COPY LIST BY NAME===============================================================


    public void copyListByName() {
        List<WebElement> listSizeBefore= driver.findElements(By.cssSelector(".js-list-content"));
        int sizeBefore = listSizeBefore.size();
        waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"), 10);
        WebElement listActionButton = driver.findElement
                (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        listActionButton.click();
        waitUntilElementIsClickable(By.cssSelector(".js-copy-list"), 10);
        WebElement copyList = driver.findElement(By.cssSelector(".js-copy-list"));
        copyList.click();
        waitUntilElementIsClickable(By.cssSelector(".js-autofocus"), 10);
        WebElement nameListAfterCopy = driver.findElement(By.cssSelector(".js-autofocus"));
        nameListAfterCopy.click();
        nameListAfterCopy.clear();
        nameListAfterCopy.sendKeys("Copy of : "+listTitle);
        waitUntilElementIsClickable(By.xpath("//input[@value='Create list']"), 10);
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Create list']"));
        submitButton.click();
        List<WebElement> listSizeAfter= driver.findElements(By.cssSelector(".js-list-content"));
        int sizeAfter = listSizeAfter.size();
//        waitUntilElementBecame
//        (By.xpath("//*[@class='list js-list-content'][contains(.,'Copy of : '"+listTitle+"')]"),sizeAfter+1, 10);
        //*[@class='list js-list-content'][contains(.,'some text')]
        Assert.assertEquals(sizeAfter, sizeBefore+1, "List wasn't copy");
    }







}

