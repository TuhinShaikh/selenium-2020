package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class DriverSettings {
    public WebDriver driver;


    @BeforeClass
    public void setupWebDriver() {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.google.com/");

    }

    @BeforeMethod
    public void settingNewTest() {
        driver.manage().deleteAllCookies();
        driver.manage().window().fullscreen();
    }

    @AfterMethod
    public void closingTest() {
        driver.close();
    }


    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
