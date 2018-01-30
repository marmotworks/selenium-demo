package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubaruModelSelectionPage extends SubaruBasePage{

    String title = "Build & Price Subaru | Subaru of America";
    String vehiclePanelClassName = "vehicle-card-item-wrap";
    String buildButtonClassName = "icon-build";

    public SubaruModelSelectionPage(WebDriver driver){
        super(driver);
        waitForTitle(title);
    }

    //Gets a list of vehicle names, finds the one that matches the model desired, and clicks the associated button.
    public SubaruTrimSelectionPage clickModelBuildButton(String model){
        List<WebElement> modelPanels = driver.findElements(By.className(vehiclePanelClassName));
        for (WebElement element : modelPanels) {
            if (element.getAttribute("ng-init").contains(model)) {
                //if we don't bring the element into view, the bottom decorator bar obscures it.
                bringElementIntoView(element);
                element.findElement(By.className(buildButtonClassName)).click();
                return new SubaruTrimSelectionPage(driver);
            }
        }
        return null;
    }

}
