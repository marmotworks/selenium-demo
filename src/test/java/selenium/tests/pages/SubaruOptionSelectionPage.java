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
        WebElement transmissionChooser = waitForElementBy(By.id("transmission-chooser"));
        List<WebElement> transmissionOptions = transmissionChooser.findElements(By.className("ng-scope"));
        badWait(1000);
        for (WebElement transmissionOption : transmissionOptions) {
            WebElement accessoryName = waitForNestedElement(transmissionOption, By.className("accessory-name"));

            if (accessoryName.getText().equals(transmissionName)) {

                WebElement transmissionButton = transmissionOption.findElement(By.tagName("button"));
                bringElementToCenterOfView(transmissionButton);
                badWait(1000);
                transmissionButton.click();
                waitForElementBy(By.id("transmission-chooser"));
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

    /*
    * Adds an option from the All category if it's not already selected.
    * */
    public void selectOptionByName(String optionName) {
        //wait for and locate the second of accordions that contain options
        WebElement optionChooser = waitForElementBy(By.id("accessory-chooser"));

        //get a list of accordion sections
        List<WebElement> optionCategories = optionChooser.findElements(By.tagName("accessory-accordion-bar"));

        //for each of these categories, check their name
        for (WebElement optionCategory : optionCategories) {
            String optionCategoryName = optionCategory.findElement(By.cssSelector("span[class='acc-category ng-binding']")).getText();
            String expansionIcon = optionCategory.findElement(By.tagName("i")).getAttribute("class");

            //if the name of the category is All and the accordion is not expanded, expand it
            if (optionCategoryName.equalsIgnoreCase("All") && expansionIcon.equalsIgnoreCase("icon-plus")) {
                bringElementToCenterOfView(optionCategory);
                optionCategory.click();
                waitForNestedElement(optionCategory, By.cssSelector("div[class='accordion-content row']"));

                //get a list of the options in the All category and find the option we're looking for
                List<WebElement> allOptions = optionCategory.findElements(By.tagName("accessory-item-display"));
                for (WebElement option : allOptions) {
                    String optionLabel = option.findElement(By.className("accessory-name")).getText();
                    String itemStatus = option.findElement(By.cssSelector("span[class='item-status ng-binding']")).getText();
                    //if the option is found and it hasn't already been added, add it
                    if (optionLabel.equalsIgnoreCase(optionName) && itemStatus.equalsIgnoreCase("add")) {
                        bringElementToCenterOfView(option);
                        option.findElement(By.tagName("button")).click();
                        badWait(1000);
                    }
                }
            }
        }
    }

    public String getPrice(){
        WebElement totalSection = driver.findElement(By.cssSelector("table[class='summary-section summary-total ng-scope']"));
        String total = totalSection.findElement(By.cssSelector("td[class='ng-binding']")).getText();
        return total;
    }
}
