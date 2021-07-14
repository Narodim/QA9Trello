package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class LoginPageHelper extends PageBase{

    @FindBy(css = ".text-primary")
    WebElement logInIcon;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "user")
    WebElement loginNeg;
    @FindBy(id="password")
    WebElement passwordNeg;
    @FindBy(css="p.error-message")
    WebElement errorMassage;
    @FindBy(xpath = "//input[@value='Log in with Atlassian']")
    WebElement logInWithAttl;
    @FindBy(id = "user")
    WebElement loginPos;
    @FindBy(id="password")
    WebElement passwordPos;
    @FindBy(id="login-submit")
    WebElement loginSubmit;
    @FindBy(xpath = "//ul[@class='boards-page-board-section-list']")
    WebElement mainBoardsPage;
    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']/span[2]")
    WebElement boardIconOnMainPage;
    @FindBy(xpath = "//button[@aria-label='Open member menu']")
    WebElement memberMenuButton;
    @FindBy(xpath = "//nav/ul/li[8]")
    WebElement logOut;
    @FindBy(id="logout-submit")
    WebElement submitLogout;
    @FindBy(xpath = "//div[@class='layout-centered-content']")
    WebElement logoutMessage;


    public LoginPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public  LoginPageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginButton,10);
        return this;
    }

    public LoginPageHelper openPage() {
        waitUntilElementIsClickable(logInIcon,40);
        logInIcon.click();
        return this;
    }

    public LoginPageHelper fillInEmailField(String value) {

        WebElement emailField = loginNeg;
        clickAndFielding(emailField,loginNegative);
        return this;
    }

    public LoginPageHelper fillInPasswordField(String value) {
        waitUntilElementIsClickable(passwordNeg, 10);
        WebElement passwordField = passwordNeg;
        clickAndFielding(passwordField,passwordNegative);
        return this;
    }


    public LoginPageHelper submitLoginNotAttl() {
        loginButton.click();
        return this;
    }

    public String invalidMessageReceiving() {
        waitUntilElementIsVisible(errorMassage, 10);
        return errorMassage.getText();
    }

    public LoginPageHelper logInWithAttl() {
        waitUntilElementIsClickable(logInWithAttl, 10);
        loginButton.click();
        return this;
    }

    public LoginPageHelper fillInEmailFieldPos(String value) {
        WebElement emailField = loginPos;
        clickAndFielding(emailField,LOGINPOSITIVE);
        return this;
    }

    public LoginPageHelper fillInPasswordFieldPos(String value) {
        waitUntilElementIsClickable(passwordPos, 10);
        WebElement passwordField = passwordPos;
        clickAndFielding(passwordField,PASSWORDPOSITIVE);
        return this;
    }

    public LoginPageHelper clickSubmitButton() {
        waitUntilElementIsClickable(loginSubmit, 10);
        loginSubmit.click();
        waitUntilElementIsClickable(mainBoardsPage, 10);
        return this;
    }

    public String receivingStringFromBoardsPage() {
        waitUntilElementIsClickable(boardIconOnMainPage, 10);
        boardIconOnMainPage.click();
        return boardIconOnMainPage.getText();
    }

    public LoginPageHelper logoutFromAccount() {
        memberMenuButton.click();
        waitUntilElementIsClickable(logOut, 10);
        logOut.click();
        return this;
    }

    public LoginPageHelper logoutSubmit() {
        waitUntilElementIsClickable(submitLogout, 10);
        submitLogout.click();
        return this;
    }

    public String receivingConfirm() {
        waitUntilElementIsVisible(logoutMessage, 10);
        return logoutMessage.getText();
    }
}
