package com.trello.tests;

import org.openqa.selenium.support.PageFactory;
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
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .fillInEmailFieldPos(LOGINPOSITIVE)
                .logInWithAttl()
                .fillInPasswordFieldPos(PASSWORDPOSITIVE)
                .clickSubmitButton();
        memberMenu
                .openMenuMember()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void profileAndVisibilityTabExisting(){
        Assert.assertEquals(memberMenu.profileAndVisibilityTab(),"Profile and visibility");
    }



}
