package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SubaruOptionSelectionPage extends SubaruBasePage {

    String title = "Build & Price Subaru | Subaru of America";

    public SubaruOptionSelectionPage(WebDriver driver) {
        super(driver);
        waitForTitle(title);
    }

    public void selectTransmissionByName(String transmissionName) {
        WebElement transmissionChooser = waitForElementById("transmission-chooser");
        List<WebElement> transmissionOptions = transmissionChooser.findElements(By.className("ng-scope"));
        for (WebElement transmissionOption : transmissionOptions) {
            badWait(1000);
            WebElement accessoryName = transmissionOption.findElement(By.className("accessory-name"));
            System.out.println(accessoryName.getText());
            if (accessoryName.getText().equals(transmissionName)) {
                WebElement transmissionButton = transmissionOption.findElement(By.tagName("button"));
                bringElementToCenterOfView(transmissionButton);
                transmissionButton.click();

            }
        }
    }
}
