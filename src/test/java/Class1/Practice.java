package Class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Practice {

    @Test
    public void enterAddressInDarksky() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("10001");
        driver.findElement(By.className("searchButton")).click();
    }
}
