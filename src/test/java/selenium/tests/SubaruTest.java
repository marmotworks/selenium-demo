package selenium.tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.TestTemplate;
import selenium.tests.pages.SubaruOptionSelectionPage;
import selenium.tests.pages.SubaruTrimSelectionPage;
import selenium.tests.pages.SubaruModelSelectionPage;
import selenium.tests.pages.SubaruHomePage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests configuring and pricing subaru vehicles.
 *
 * After you run these a few times Subaru's site will begin issuing ACS Customer Satisfaction Survey popups. I haven't handled them here for the sake of brevity.
 *
 * This test uses the Page Object Model to organize methods and contextualize activities in the same way a user would navigate through the site.
 * These page objects could be used to build any subaru model with minimal adjustment/polish, though as written they've been verified to work to build a Forester.
 */

public class SubaruTest extends BaseTest {

    //TestTemplate annotation tells the selenium-jupiter extension to use browsers.json in resources to select the browser used
    @TestTemplate
    public void helloTest() {

        SubaruHomePage.open(driver);

        assertThat(driver.getTitle(), containsString("Subaru Cars, Sedans, SUVs | Subaru of America"));
    }

    //buildAForesterTest configures a forester and asserts that pricing data has remained consistent.
    //This functionality could be pulled out into a unit of work and parameterized if many models and builds needed to be tested.
    @TestTemplate
    public void buildAForesterTest() {

        //Opens the Subaru home page and returns a homePage page object
        SubaruHomePage homePage = SubaruHomePage.open(driver);

        //Clicks the build & price button, and returns the modelSelection page object
        SubaruModelSelectionPage buildAndPricePage = homePage.clickBuildAndPrice();

        //Clicks on the Forester button, returning the page object for trim selection
        SubaruTrimSelectionPage subaruTrimSelectionPage = buildAndPricePage.clickModelBuildButton("Forester");

        //Selects the specified trim level and returns the Options Selection page object
        SubaruOptionSelectionPage subaruOptionSelectionPage = subaruTrimSelectionPage.chooseTrimLevel("2.5i Premium");

        //Selects the transmission type by displayed name, paint color by paint code, and a Trailer Hitch from the options list
        subaruOptionSelectionPage.selectTransmissionByName("Lineartronic CVT");
        subaruOptionSelectionPage.selectExteriorColorByPaintCode("K1X");
        subaruOptionSelectionPage.selectOptionByName("Trailer Hitch");

        //Gets the price after configuration
        String finalTotal = subaruOptionSelectionPage.getPrice();

        //Verifies that the price as configured matches prior assessed price
        assertThat(finalTotal, Matchers.equalToIgnoringCase("$27,723"));
    }

}
