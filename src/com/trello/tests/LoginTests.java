package com.trello.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageHelper;
import pages.LoginPageHelper;

public class LoginTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;

    @BeforeMethod
    public void initTests(){
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void negativeLogin() {

        loginPage.fillInEmailField(loginNegative);
        loginPage.fillInPasswordField(passwordNegative);
        loginPage.submitLoginNotAttl();
        loginPage.invalidMessageReceiving();
    }

    @Test
    public void positiveLogin() {

        loginPage.fillInEmailFieldPos(LOGINPOSITIVE);
        loginPage.logInWithAttl();
        loginPage.fillInPasswordFieldPos(PASSWORDPOSITIVE);
        loginPage.clickSubmitButton();
        loginPage.receivingStringFromBoardsPage();
        loginPage.logoutFromAccount();
        loginPage.logoutSubmit();
        loginPage.receivingConfirm();
    }
}

