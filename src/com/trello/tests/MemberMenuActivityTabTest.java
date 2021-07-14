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
import pages.MemberMenuHelper;



public class MemberMenuActivityTabTest extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void InitTest(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
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
    public void lastActivityInActivityTab(){
        Assert.assertEquals(memberMenu.activityTab(), memberMenu.accName + " " + "added list" + " " + listTitle + " " + "to" + " " + boardTitle);
    }
}
