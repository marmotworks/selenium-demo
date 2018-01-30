package selenium.tests;

import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@ExtendWith(SeleniumExtension.class)
public abstract class BaseTest {

    /*
    * BaseTest.java handles common activities for before and after selenium test execution.
    * Because we're using the selenium-jupiter extension, this is sparse.
    * */

    protected WebDriver driver;

    //creates the browser session & driver
    @BeforeEach
    public void init(WebDriver driver){
        this.driver = driver;
    }

    //destroys driver in case graceful destruction fails to take place
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
