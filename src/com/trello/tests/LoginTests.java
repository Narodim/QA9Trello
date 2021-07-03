package com.trello.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;




public class LoginTests extends TestBase {

    @Test
    public void negativeLogin(){
        waitUntilElementIsClickable(By.id("user"), 10);
        WebElement emailField = driver.findElement(By.id("user"));
        clickAndFielding(emailField,loginNegative);
        waitUntilElementIsClickable(By.id("password"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        clickAndFielding(passwordField,passwordNegative);
        driver.findElement(By.id("login")).click();

        waitUntilElementIsVisible(By.cssSelector("p.error-message"), 10);
        Assert.assertEquals(driver.findElements(By.cssSelector("p.error-message")).get(0).getText(),
                "There isn't an account for this email", "The error message is not correct");
    }

    @Test
    public void positiveLogin() {
        waitUntilElementIsClickable(By.id("user"), 10);
        WebElement emailField = driver.findElement(By.id("user"));
        clickAndFielding(emailField,LOGINPOSITIVE);
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("password"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        clickAndFielding(passwordField,PASSWORDPOSITIVE);
        waitUntilElementIsClickable(By.id("login-submit"), 10);
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();

        waitUntilElementIsClickable(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"), 10);
        WebElement boardsList = driver.findElement(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]"));//without localization
        boardsList.click();
        String boardButtonActualText = boardsList.getText();
        Assert.assertEquals(boardButtonActualText, "Boards", "Troubles with assert");

        WebElement openMemberMenu = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
        openMemberMenu.click();
        waitUntilElementIsClickable(By.xpath("//nav/ul/li[8]"), 10);
        WebElement logOut = driver.findElement(By.xpath("//nav/ul/li[8]"));
        logOut.click();

        waitUntilElementIsClickable(By.id("logout-submit"), 10);
        WebElement logoutSubmit = driver.findElement(By.id("logout-submit"));
        logoutSubmit.click();

        waitUntilElementIsVisible(By.xpath("//div[@class='layout-centered-content']"), 10);
        WebElement exitConfirmed = driver.findElement(By.xpath("//div[@class='layout-centered-content']"));
        String confirmedExitText = exitConfirmed.getText();
        Assert.assertEquals(confirmedExitText, "Thanks for using Trello." +
                        "\nYou’re all logged out. So now what?","Troubles with assert");
    }
}

