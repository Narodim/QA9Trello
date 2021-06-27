package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;


public class LoginTests extends TestBase {

    @Test
    public void negativeLogin() throws InterruptedException {
        //fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys(loginNegative);
        //fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys(passwordNegative);
        Thread.sleep(3000);
        //click on login button
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
        //output error-message
        System.out.println("Error-message: "+driver
                .findElements(By.cssSelector("p.error-message")).get(0).getText());

    }
    @Test
    public void positiveLogin() throws InterruptedException {
        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys(loginPositive);
        Thread.sleep(3000);
        driver.findElement(By.id("login")).click();
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys(passwordPositive);
        Thread.sleep(3000);
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        Thread.sleep(8000);
        WebElement boardsList = driver.findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"));//without localization
        boardsList.click();
        String boardButtonActualText = boardsList.getText();
        Assert.assertEquals(boardButtonActualText, "Boards", "Something going wrong");
        Thread.sleep(3000);
        System.out.println("Header of the Tab: "+ driver.findElement(By.xpath("//button[@aria-label='Open boards menu']/span[contains(text(),'Boards')]")).getText());
        WebElement openMemberMenu = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
        openMemberMenu.click();
        Thread.sleep(3000);
        WebElement logOut = driver.findElement(By.xpath("//nav/ul/li[8]"));
        logOut.click();
        Thread.sleep(3000);
        WebElement logoutSubmit = driver.findElement(By.id("logout-submit"));
        logoutSubmit.click();
        Thread.sleep(3000);
        System.out.println("Exit confirmed: "+ driver.findElement(By.xpath("//div[@class='layout-centered-content']")).getText());

    }

}

