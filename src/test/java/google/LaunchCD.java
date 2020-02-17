package google;

import framework.DriverSettings;
import org.testng.annotations.Test;
import page.Pg_google;

public class LaunchCD extends DriverSettings {


    @Test
    public void cd() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
//
//        WebDriver driver = new ChromeDriver();
////        driver.get("http://www.google.com/");
//        Thread.sleep(5000);  // Let the user actually see something!
//        WebElement searchBox = driver.findElement(By.name("q"));
//        searchBox.sendKeys("ChromeDriver");
//        searchBox.submit();
//        Thread.sleep(5000);  // Let the user actually see something!
//       // driver.quit();


        Pg_google pg = new Pg_google(driver);

        pg.searchText("WebDriver");
        Thread.sleep(5000);
        pg.submitSearch();
        Thread.sleep(5000);

    }
}
