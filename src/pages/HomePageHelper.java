package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePageHelper extends PageBase {

    public HomePageHelper(WebDriver driver){
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.cssSelector(".text-primary"), 40);
    }

    public boolean isCorrectPage(){
        return driver.findElement(By.cssSelector(".text-primary")).getText().equals("Log in");
    }

}
