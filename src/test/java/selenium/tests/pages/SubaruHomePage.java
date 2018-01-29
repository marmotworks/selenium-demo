package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubaruHomePage extends SubaruBasePage{

    String title = "Subaru Cars, Sedans, SUVs | Subaru of America";

    public SubaruHomePage(WebDriver driver){
        super(driver);
        waitForTitle(title);
    }

    public SubaruModelSelectionPage clickBuildAndPrice(){
        driver.findElement(By.linkText("Build & Price")).click();
        return new SubaruModelSelectionPage(driver);
    }
}
