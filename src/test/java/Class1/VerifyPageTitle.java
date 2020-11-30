package Class1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyPageTitle {

    /**
     * 1. Open facebook homepage using url "https://www.facebook.com"
     * 2. Verify the page title is "Facebook – log in or sign up"
     * 3. Close the opened webpage
     */
    @Test
    public void verifyPage() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://Facebook.com");
        String pageTitle = driver.getTitle();
        String titleGiven = "Facebook – log in or sign up";
        Assert.assertEquals(pageTitle, titleGiven, "The page title is different ");
        driver.quit();
    //why didnt close the page?
    }

}
