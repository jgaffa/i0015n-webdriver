import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class WebDriverTest {
    /*
        Testing if title and reindeer image present on https://klockren.nu
        Testing through Chrome or Edge web browser, drivers are in /drivers directory
        Parameters are set in webdriver.xml file
        To change executing application, change "browser" parameter
        Execute test from webdriver.xml file
    }*/
    WebDriver driver;
    String elementXPath = "//*[@id=\"klockren\"]";
    String pageTitle = "Klockren";

    @BeforeClass
    @Parameters({"browser", "url"})
    void setUp(String browser, String url)  {
        System.out.println("\tSetting up test...");

        if(browser.equalsIgnoreCase("chrome"))  {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("edge"))    {
            System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.get(url);
    }
    @Test(priority = 1)
    void logoTest() {
        System.out.println("\tTesting logo...");

        WebElement logoElement = driver.findElement(new By.ByXPath(elementXPath));

        Assert.assertTrue(logoElement.isDisplayed(), "Logo not displayed on web page...");
    }
    @Test(priority = 2)
    void homePageTitle()    {
        System.out.println("\tTesting title...");

        Assert.assertEquals(driver.getTitle(), pageTitle, "Title does not match...");
    }
    @AfterClass
    void tearDown() {
        System.out.println("\tTest finished...");

        driver.quit();
    }
}
