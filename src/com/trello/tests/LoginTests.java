package com.trello.tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageHelper;
import pages.LoginPageHelper;

public class LoginTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;

    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                 .openPage()
                 .waitUntilPageIsLoaded();
    }

    @Test
    public void negativeLogin() {

        loginPage
                .fillInEmailField(loginNegative)
                .fillInPasswordField(passwordNegative)
                .submitLoginNotAttl();
        Assert.assertEquals(loginPage.invalidMessageReceiving(), "There isn't an account for this email");
    }

    @Test
    public void positiveLogin() {

        loginPage
                .fillInEmailFieldPos(LOGINPOSITIVE)
                .logInWithAttl()
                .fillInPasswordFieldPos(PASSWORDPOSITIVE)
                .clickSubmitButton();
        Assert.assertEquals(loginPage.receivingStringFromBoardsPage(), "Boards");
        loginPage
                .logoutFromAccount()
                .logoutSubmit();
        Assert.assertEquals(loginPage.receivingConfirm(), "Thanks for using Trello." +
                "\nYou’re all logged out. So now what?","Troubles with assert");
    }
}

