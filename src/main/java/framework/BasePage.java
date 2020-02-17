package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;

    }


    public WebElement webAction(By locator) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(30, SECONDS)
//                .pollingEvery(5, SECONDS)
//                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return element;
    }

    public void clickOn(By locator) {
        try {
            webAction(locator).click();
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
    }

    public void sendText(By locator, String text) {
        try {
            webAction(locator).sendKeys(text);
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }
    }


    public String getTextFromElement(By locator) {
        String text = null;
        try {
            text = webAction(locator).getText();
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not found with this locator: " + locator.toString());
            e.printStackTrace();
        }

        return text;
    }

    public void selectAutoComplete(By autoCompleteTextField, String partialText, By suggestedList, String valueToBeSelected) {
        webAction(autoCompleteTextField).sendKeys(partialText);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Fluent wait is not implemented here
        List<WebElement> list = driver.findElements(suggestedList);
        for (WebElement ele : list) {
            if (ele.getText().contains(valueToBeSelected)) {
                ele.click();
                break;
            }
        }
    }

    public void selectCurrentDateFromCalender(String datePattern, By calendarField, By listOfDays) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        Date date = new Date();
        String todayDate = sdf.format(date);

        webAction(calendarField).click();
        //Fluent wait is not implemented here
        List<WebElement> days = driver.findElements(listOfDays);

        for (WebElement day : days) {
            String expectedDay = day.getText();
            if (expectedDay.equals(todayDate)) {
                day.click();
                break;
            }
        }
    }

    public void selectFromDropDown(By dropdownField, String visibleText) {
        // Select value from drop
        Select dropdown = new Select(webAction(dropdownField));
        // Select element by visible text
        dropdown.selectByVisibleText(visibleText);
    }

    public void selectFromDropDown(By dropdownField, int index) {
        // Select value from drop
        Select dropdown = new Select(webAction(dropdownField));
        // Select element by index
        dropdown.selectByIndex(index);
    }

    public void mouseOver(By hoverOverElement) {
        WebElement element = webAction(hoverOverElement);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    // Switches to window based of index
    public void switchToWidnow(int index) {

        ArrayList<String> listOfWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(listOfWindows.get(index));
    }

    // Closes current window and switches back to root window
    public void switchToRootWindowAndCloseCurrentBrowser() {
        List<String> listOfWindows = new ArrayList<String>(driver.getWindowHandles());

        for (int i = 1; i < listOfWindows.size(); i++) {
            driver.switchTo().window(listOfWindows.get(i));
            driver.close();
        }
        driver.switchTo().window(listOfWindows.get(0));
    }

}
