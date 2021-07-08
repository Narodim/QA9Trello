package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPageHelper extends PageBase{
    
    public LoginPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public  void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.id("login"),10);
    }

    public void openPage() {
        waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        driver.findElement(By.cssSelector(".text-primary")).click();
    }

    public void fillInEmailField(String value) {

        WebElement emailField = driver.findElement(By.id("user"));
        clickAndFielding(emailField,loginNegative);
    }

    public void fillInPasswordField(String value) {
        waitUntilElementIsClickable(By.id("password"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        clickAndFielding(passwordField,passwordNegative);
    }


    public void submitLoginNotAttl() {
        driver.findElement(By.id("login")).click();
    }

    public void invalidMessageReceiving() {
        waitUntilElementIsVisible(By.cssSelector("p.error-message"), 10);
        Assert.assertEquals(driver.findElements(By.cssSelector("p.error-message")).get(0).getText(),
                "There isn't an account for this email", "The error message is not correct");
    }

    public void logInWithAttl() {
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.id("login")).click();
    }

    public void fillInEmailFieldPos(String value) {
        WebElement emailField = driver.findElement(By.id("user"));
        clickAndFielding(emailField,LOGINPOSITIVE);
    }

    public void fillInPasswordFieldPos(String value) {
        waitUntilElementIsClickable(By.id("password"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        clickAndFielding(passwordField,PASSWORDPOSITIVE);
    }

    public void clickSubmitButton() {
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        waitUntilElementIsClickable(By.xpath("//ul[@class='boards-page-board-section-list']"), 10);
    }

    public void receivingStringFromBoardsPage() {
        waitUntilElementIsClickable(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"), 10);
        WebElement boardsList = driver.findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"));//without localization
        boardsList.click();
        String boardButtonActualText = boardsList.getText();
        Assert.assertEquals(boardButtonActualText, "Boards", "Troubles with assert");
    }

    public void logoutFromAccount() {
        WebElement openMemberMenu = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
        openMemberMenu.click();
        waitUntilElementIsClickable(By.xpath("//nav/ul/li[8]"), 10);
        WebElement logOut = driver.findElement(By.xpath("//nav/ul/li[8]"));
        logOut.click();
    }

    public void logoutSubmit() {
        waitUntilElementIsClickable(By.id("logout-submit"), 10);
        WebElement logoutSubmit = driver.findElement(By.id("logout-submit"));
        logoutSubmit.click();
    }

    public void receivingConfirm() {
        waitUntilElementIsVisible(By.xpath("//div[@class='layout-centered-content']"), 10);
        WebElement exitConfirmed = driver.findElement(By.xpath("//div[@class='layout-centered-content']"));
        String confirmedExitText = exitConfirmed.getText();
        Assert.assertEquals(confirmedExitText, "Thanks for using Trello." +
                "\nYou’re all logged out. So now what?","Troubles with assert");
    }
}
