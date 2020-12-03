package Class1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Practice {

    @Test
    public void scrolling () {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");
/**
 * Practice:
 *          * 1. Launch darksky.net
 *          * 2. Scroll to Time Machine button (scroll by pixels)
 *          * 3. Click on Time Machine button
 *          * 4. Verify currentDate is highlighted
 */
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("scrollBy(0,1300);");
        WebElement button = driver.findElement(By.xpath("//a[text()='Time Machine']"));
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        button.click();
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("d");
        String date = df.format(now);
        WebElement dateWeb = driver.findElement(By.xpath("//td[@class='is-today']//button"));
        String date2 = dateWeb.getText();
        if (date==date2) {
            Assert.assertTrue(dateWeb.isSelected(), "The date is not highlighted");
        }
    }

}
