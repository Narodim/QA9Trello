package com.trello.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageHelper;

public class HomePageTest extends TestBase{
        HomePageHelper homePage;

        @BeforeMethod
    public void initTest(){
            homePage = new HomePageHelper(driver);
            homePage.waitUntilPageIsLoaded();
        }

        @Test
    public void verifyAppTest(){
            Assert.assertTrue(homePage.isCorrectPage());
        }
}
