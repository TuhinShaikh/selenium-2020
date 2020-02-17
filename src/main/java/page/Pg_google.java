package page;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Pg_google extends BasePage {

    public Pg_google(WebDriver driver) {
        super(driver);
    }


    By searchBox = By.name("q");


    public void searchText(String textToSearch) {
        sendText(searchBox, textToSearch);
    }

    public void submitSearch() {
        clickOn(searchBox);

    }


}
