package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubaruTrimSelectionPage extends SubaruBasePage{

    String title = "Build & Price Subaru | Subaru of America";

    public SubaruTrimSelectionPage(WebDriver driver) {
        super(driver);
        waitForTitle(title);
    }

    /*
    * For Model Year 2018, Forester trim levels are as follows:
    * 2.5i Base
    * 2.5i Premium
    * 2.5i Limited
    * 2.5i Touring
    * 2.0XT Premium
    * 2.0XT Touring
    * */
    //TODO handle the pagination to select 2.0XT Models
    public SubaruOptionSelectionPage chooseTrimLevel(String trimLevel){

        WebElement trimPanel = waitForElementBy(By.id("fourth_animated_content"));
        List<WebElement> trimList = trimPanel.findElements(By.className("trim-title"));
        for (WebElement trimLabel : trimList) {
            if (trimLabel.getText().replace("\n", " ").equals(trimLevel)) {
                badWait(1000);
                bringElementToCenterOfView(trimLabel);
                trimLabel.click();
                return new SubaruOptionSelectionPage(driver);
            }
        }
        return null;
    }
}
