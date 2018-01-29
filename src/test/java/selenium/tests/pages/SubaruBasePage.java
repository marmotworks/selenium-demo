package selenium.tests.pages;

import org.openqa.selenium.WebDriver;

public abstract class SubaruBasePage extends BasePage{

    public SubaruBasePage(WebDriver driver) {
        super(driver);
    }

    public static SubaruHomePage open(WebDriver driver) {
        driver.get("https://www.subaru.com");
        return new SubaruHomePage(driver);
    }
}
