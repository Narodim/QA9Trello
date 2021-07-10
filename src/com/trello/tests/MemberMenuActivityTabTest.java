package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageHelper;
import pages.LoginPageHelper;
import pages.MemberMenuHelper;



public class MemberMenuActivityTabTest extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void InitTest(){
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
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
    public void lastActivityInActivityTab(){
        Assert.assertEquals(memberMenu.activityTab(), memberMenu.accName + " " + "added list" + " " + listTitle + " " + "to" + " " + boardTitle);
    }
}
