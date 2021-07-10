package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class MemberMenuHelper extends PageBase {
    public String accName;

    public MemberMenuHelper(WebDriver driver){
        this.driver = driver;
    }


    public void openMenuMember() {
        waitUntilElementIsClickable(By.xpath("//button[@aria-label='Open member menu']"), 10);
        WebElement openMemberMenu = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
        openMemberMenu.click();
       }


    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//*[@data-test-id='header-member-menu-popover']"), 10);
    }

    public String profileAndVisibilityTab() {
        return driver.findElement(By.xpath("//nav/ul/li[2]")).getText();
    }


    public String activityTab() {
        accName = driver.findElement(By.xpath("//*[@class='_3_9rx_DdTAFtig']")).getText();
        driver.findElement(By.xpath("//nav/ul/li[3]")).click();
        waitUntilElementIsVisible(By.cssSelector(".list-actions"), 10);
        return driver.findElement(By.xpath("//*[@class='phenom-desc'][contains(.,'Testovskiy')][contains(.,'added list')]")).getText();
    }
}
