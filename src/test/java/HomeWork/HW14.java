package HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HW14 {

   //TC1
    @Test
    public void verifyHighLowTemp(){
        /**
         * Launch darksky.net;
         * Get a locator for 'low' Temp;
         * Capture the value of 'low' Temp;
         * Get a locator for 'high' Temp;
         * Capture the value of 'high' Temp;
         * Scroll to the 'Today Temp' bar;
         * Get a locator for 'low-bar' Temp;
         * Capture the value of 'low-bar' Temp;
         * Get a locator for 'high-bar' Temp;
         * Capture the value of 'high-bar' Temp;
         * verify that 'low' Temp equals to 'low-bar' Temp;
         * verify that 'high' Temp equals to 'high-bar' Temp;
        **/
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");

        WebElement lowLocator = driver.findElement(By.xpath("//span[@class='low-temp-text']"));
        String low = lowLocator.getText();
        WebElement highLocator = driver.findElement(By.xpath("//span[@class='high-temp-text']"));
        String high = highLocator.getText();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0,900);");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement lowBarLocator = driver.findElement(By.xpath("//span[contains(text(), 'Today')]/following::span[@class='minTemp'][1]"));
        String lowBar = lowBarLocator.getText();
        String highBar = driver.findElement(By.xpath("//span[contains(text(), 'Today')]/following::span[@class='maxTemp'][1]")).getText();

        Assert.assertTrue(low.equalsIgnoreCase(lowBar) && high.equalsIgnoreCase(highBar), "Temperatures are displayed incorrectly");

    }
    //TC2

    @Test
    public void verifyingNumberOfNights() {
        /**
         * Get the current date using Calendar class;
         * Calculate 'checkIn' date;
         * Calculate 'checkOut' date;
         * Launch hotels.com;
         * Get a locator for check in calendar ;
         * Open the calendar;
         * Get the locator for all days in the opened calendar;
         * click 'check in' date;
         * repeat for 'check out' date;
         * get a locator for nights count on black briefcase;
         * capture the text;
         * convert all the dates to int values;
         * subtract 'check-in' int value from 'check-out' int value;
         * verify if the number we get equals to number displayed on the briefcase.
          */
       Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("d");
        cal.add(Calendar.DATE, 1);
        Date dateTom = cal.getTime();
        String dateCheckIn = df.format(dateTom);
        cal.add(Calendar.DATE,7);
        Date date7Days = cal.getTime();
        String dateCheckOut = df.format(date7Days);

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com");

        driver.findElement(By.xpath("//label[@data-input='qf-0q-localised-check-in']")).click();
        List<WebElement> monthDates = driver.findElements(By.xpath("//td[starts-with(@data-date,'2020-11')]"));
        for (WebElement dateOfMonth : monthDates) {
            if (dateOfMonth.getText().equalsIgnoreCase(dateCheckIn)) {
                dateOfMonth.click();
                break;
            }
        }
        driver.findElement(By.xpath("//label[@data-input='qf-0q-localised-check-out']")).click();
        for (WebElement dateOfMonth : monthDates) {
            if (dateOfMonth.getText().equalsIgnoreCase(dateCheckOut)) {
                dateOfMonth.click();
                break;
            }
        }

        String briefCaseNumber = driver.findElement(By.xpath("//span[@class='widget-query-num-nights']")).getText();
        int dateCheckInInt = Integer.valueOf(dateCheckIn);
        int dateCheckOutInt = Integer.valueOf(dateCheckOut);
        int briefCaseNum = Integer.valueOf(briefCaseNumber);
        Assert.assertTrue(dateCheckOutInt-dateCheckInInt==briefCaseNum, "Number of nights is displayed incorrectly");

    }

    //TC3
    @Test
    public void verifyUserDetails() {
        /**
         * Save given parameters as String values;
         * Launch hotels.com;
         * Get locators for a destination section;
         * Enter the location value (.sendKeys);
         * Picked a suggestion (in this case the only one);
         * Get locator for dropdowns and choose needed values for all given parameters;
         * Get locator for 'Search' button and click;
         * Get locators for dropdowns of all parameters;
         * Capture parameters' values, save them as String values;
         * Verify that given values equal to the values captured on the website.
         */
        String roomCustom = "1";
        String adultCustom = "1";
        String childrenCustom = "2";
        String child1Custom = "1";
        String child2Custom = "2";
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com");
        driver.findElement(By.xpath("//input[@name='q-destination']")).sendKeys("Ritz Carlton paris France");
        driver.findElement(By.xpath("//tr[@id='hotelsqm-asi0-s0']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//label[@data-input='qf-0q-localised-check-in']")).click();
        driver.findElement(By.xpath("//td[@data-date='2020-11-9']")).click();
        driver.findElement(By.xpath("//label[@data-input='qf-0q-localised-check-out']")).click();
        driver.findElement(By.xpath("//td[@data-date='2020-11-16']")).click();
        Select rooms = new Select(driver.findElement(By.xpath("//select[@class='query-rooms']")));
        rooms.selectByValue(roomCustom);
        Select adults = new Select(driver.findElement(By.id("qf-0q-room-0-adults")));
        adults.selectByValue(adultCustom);
        Select children = new Select(driver.findElement(By.id("qf-0q-room-0-children")));
        children.selectByValue(childrenCustom);
        Select child1 = new Select(driver.findElement(By.id("qf-0q-room-0-child-0-age")));
        child1.selectByValue(child1Custom);
        Select child2 = new Select(driver.findElement(By.id("qf-0q-room-0-child-1-age")));
        child2.selectByValue(child2Custom);
        driver.findElement(By.xpath("//button[@type='submit']")).click();


        String roomsWeb = driver.findElement(By.xpath("//select[@id='q-rooms']//option[@selected='selected']")).getText();
        String adultsWeb = driver.findElement(By.xpath("//select[@id='q-room-0-adults']//option[@selected='selected']")).getText();
        String childrenWeb = driver.findElement(By.xpath("//select[@id='q-room-0-children']//option[@selected='selected']")).getText();
        String child1Web = driver.findElement(By.xpath("//select[@id='q-room-0-child-0-age']//option[@selected='selected']")).getText();
        String child2Web = driver.findElement(By.xpath("//select[@id='q-room-0-child-1-age']//option[@selected='selected']")).getText();

        Assert.assertTrue(roomCustom.equalsIgnoreCase(roomsWeb) && adultCustom.equalsIgnoreCase(adultsWeb) &&
                childrenCustom.equalsIgnoreCase(childrenWeb) && child1Custom.equalsIgnoreCase(child1Web)
                && child2Custom.equalsIgnoreCase(child2Web), "User details displayed incorrectly.");



    }


}
