package selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public abstract class BasePage {

    /*
    * BasePage.java holds utility/common methods shared across page objects
    * */

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    //here we have some methods that clean up common uses of dynamic waits
    public WebElement waitForElementBy(By locator) {
        return(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForNestedElement(WebElement root, By locator) {
        return(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, locator));
    }

    public void waitForTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(title));
    }

    //these are methods that execute javascript to force fields into view because selenium/angular will sometimes allow other elements to overlap
    public void bringElementIntoView(WebElement element) {
        try {
            sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait shortWait = new WebDriverWait(driver, 3);
        shortWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void bringElementToCenterOfView(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    //these are methods that are either not safe for all browsers, or are otherwise bad practice. They're sometimes unavoidable.
    public void badWait(int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void badClick(WebElement element) {
        Actions badClick = new Actions(driver);
        badClick.moveToElement(element).click().build().perform();
    }

}
