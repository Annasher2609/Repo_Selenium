package HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HW13 {

   //TC1.
    @Test
    public void checkingNotification() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.yahoo.com");

        WebElement bellIcon = driver.findElement(By.xpath("//label[@for='ybarNotificationMenu']"));
        Actions act = new Actions(driver);
        act.moveToElement(bellIcon).build().perform();
        act.click(bellIcon).build().perform();

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement firstNot = driver.findElement(By.xpath("//span[@class='yns-title']"));

        act.moveToElement(firstNot).build().perform();
        act.click(firstNot).build().perform();

    }
        //TC2
    @Test
    public void gettingError() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com");

        WebElement createAccButton = driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));
        createAccButton.click();

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement fNameField = driver.findElement(By.xpath("//input[@name='firstname']"));
        fNameField.sendKeys("Firstname");

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement lName = driver.findElement(By.xpath("//input[@name='lastname']"));
        lName.sendKeys("Lastname");

        WebElement regEmailField = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        regEmailField.sendKeys("abcd@test.com");

        WebElement emailConform = driver.findElement(By.name("reg_email_confirmation__"));
        emailConform.sendKeys("abcd@test.com");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement passwordField = driver.findElement(By.id("password_step_input"));
        passwordField.sendKeys("abcd@1234");

        WebElement monthDrop = driver.findElement(By.name("birthday_month"));
        Select month = new Select(monthDrop);
        month.selectByVisibleText("Jan");

        WebElement dayDrop  = driver.findElement(By.id("day"));
        Select day = new Select(dayDrop);
        day.selectByVisibleText("4");

        WebElement yearDrop = driver.findElement(By.id("year"));
        Select year = new Select(yearDrop);
        year.selectByVisibleText("1998");

        WebElement signUpButton = driver.findElement(By.name("websubmit"));
        signUpButton.click();

        WebElement errorMes = driver.findElement(By.xpath("//div[starts-with(text(),'Please choose a gender')]"));
        boolean isDisplayed = errorMes.isDisplayed();
        Assert.assertTrue(isDisplayed,"Error message is not displayed");


    }



}
