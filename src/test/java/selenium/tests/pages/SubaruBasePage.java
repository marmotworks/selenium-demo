package selenium.tests.pages;

import org.openqa.selenium.WebDriver;

public abstract class SubaruBasePage extends BasePage{

    /*
    * SubaruBasePage.java contains utility/support methods for use within subaru-specific page objects
    * */

    public SubaruBasePage(WebDriver driver) {
        super(driver);
    }

    //opens the browser and navigates to the Subaru home page
    public static SubaruHomePage open(WebDriver driver) {
        driver.get("https://www.subaru.com");
        return new SubaruHomePage(driver);
    }
}
