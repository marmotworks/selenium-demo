package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        badWait(1000);
        for (WebElement transmissionOption : transmissionOptions) {
            WebElement accessoryName = waitForNestedElement(transmissionOption, By.className("accessory-name"));

            if (accessoryName.getText().equals(transmissionName)) {

                WebElement transmissionButton = transmissionOption.findElement(By.tagName("button"));
                bringElementToCenterOfView(transmissionButton);
                transmissionButton.click();
                waitForElementById("transmission-chooser");
                break;
            }
        }
    }

    public void selectExteriorColorByPaintCode(String paintCode) {
        WebElement colorChooser = waitForElementBy(By.cssSelector("div[color-title=\"'Exterior'\"]"));
        List<WebElement> colorOptions = colorChooser.findElements(By.cssSelector("div[ng-repeat='color in colors']"));
        for (WebElement colorOption : colorOptions) {
            if (colorOption.getAttribute("data-color").equalsIgnoreCase(paintCode)) {
                bringElementToCenterOfView(colorOption);
                colorOption.click();
                break;
            }
        }
    }

    public void selectOptionByName(String optionName) {
        WebElement optionChooser = waitForElementById("accessory-chooser");
        List<WebElement> optionCategories = optionChooser.findElements(By.tagName("accessory-accordion-bar"));
        for (WebElement optionCategory : optionCategories) {

            String optionCategoryName = optionCategory.findElement(By.cssSelector("span[class='acc-category ng-binding']")).getText();
            String expansionIcon = optionCategory.findElement(By.tagName("i")).getAttribute("class");
            if (optionCategoryName.equalsIgnoreCase("All") && expansionIcon.equalsIgnoreCase("icon-minus")) {
                optionCategory.click();
                waitForNestedElement(optionCategory, By.cssSelector("accordion-content row"));
            }
            //break out here?
            List<WebElement> allOptions = optionCategory.findElements(By.tagName("accessory-item-display"));
            for (WebElement option : allOptions) {
                String optionLabel = option.findElement(By.className("accessory-name")).getText();
                String itemStatus = option.findElement(By.cssSelector("span[class='item-status ng-binding']")).getText();
                if (optionLabel.equalsIgnoreCase(optionName) && itemStatus.equalsIgnoreCase("add")) {
                    bringElementToCenterOfView(option);
                    option.findElement(By.tagName("button")).click();
                    WebDriverWait driverWait = new WebDriverWait(driver, 10);
                    //driverWait.until(ExpectedConditions.textToBe(By.cssSelector("span[class='item-status ng-binding']"), "added"));
                    driverWait.until(ExpectedConditions.textToBePresentInElement(option.findElement(By.cssSelector("span[class='item-status ng-binding']")), "added"));
                }
            }

        }
    }

    public void getPrice(){

    }
}
