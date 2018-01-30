package selenium.tests;

import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@ExtendWith(SeleniumExtension.class)
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void init(WebDriver driver){
        this.driver = driver;
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
