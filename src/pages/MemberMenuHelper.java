package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberMenuHelper extends PageBase {
    public MemberMenuHelper(WebDriver driver){
        this.driver = driver;
    }


    public void openMenuMember() {
        WebElement openMemberMenu = driver.findElement(By.xpath("//button[@aria-label='Open member menu']"));
        openMemberMenu.click();
       }


    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//nav/ul/li[2]"), 10);
    }

    public String profileAndVisibilityMenuName() {
        return driver.findElement(By.xpath("//nav/ul/li[2]")).getText();
    }
}
