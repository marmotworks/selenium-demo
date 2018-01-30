package selenium.tests;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.TestTemplate;
import selenium.tests.pages.SubaruOptionSelectionPage;
import selenium.tests.pages.SubaruTrimSelectionPage;
import selenium.tests.pages.SubaruModelSelectionPage;
import selenium.tests.pages.SubaruHomePage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * We're using this SeleniumExtension from Boni Garcia to trivialize the process of creating the driver and providing some extensibility.
 */

public class DemoTest extends BaseTest {

    @TestTemplate
    public void helloTest() {

        SubaruHomePage.open(driver);

        assertThat(driver.getTitle(), containsString("Subaru Cars, Sedans, SUVs | Subaru of America"));
    }

    @TestTemplate
    public void buildAForesterTest() {

        SubaruHomePage homePage = SubaruHomePage.open(driver);

        SubaruModelSelectionPage buildAndPricePage = homePage.clickBuildAndPrice();

        SubaruTrimSelectionPage subaruTrimSelectionPage = buildAndPricePage.clickModelBuildButton("Forester");

        SubaruOptionSelectionPage subaruOptionSelectionPage = subaruTrimSelectionPage.chooseTrimLevel("2.5i Premium");

        subaruOptionSelectionPage.selectTransmissionByName("Lineartronic CVT");

        subaruOptionSelectionPage.selectExteriorColorByPaintCode("K1X");

        subaruOptionSelectionPage.selectOptionByName("Trailer Hitch");

        String finalTotal = subaruOptionSelectionPage.getPrice();

        assertThat(finalTotal, Matchers.equalToIgnoringCase("$27,723"));
    }

}
