package com.trello.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentBoardPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;
import pages.MemberMenuHelper;

public class MemberMenuTest extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    CurrentBoardPageHelper currentBoardPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void initTest() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);
        memberMenu = new MemberMenuHelper(driver);

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.fillInEmailFieldPos(LOGINPOSITIVE);
        loginPage.logInWithAttl();
        loginPage.fillInPasswordFieldPos(PASSWORDPOSITIVE);
        loginPage.clickSubmitButton();
        memberMenu.openMenuMember();
        memberMenu.waitUntilPageIsLoaded();
    }

    @Test
    public void profileAndVisibilityMenuExisting(){
        Assert.assertEquals(memberMenu.profileAndVisibilityMenuName(),"Profile and visibility");
    }


}
