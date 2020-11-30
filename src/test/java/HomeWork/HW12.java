package HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HW12 {
    @Test
    //testCase1
    public void verifyTemperature() {
        /*
        1.Get locator for 'feels-like' temperature;
        2.Capture the text value for 'feels-like' temperature;
        3.Get locator for 'high' temperature;
        4.Capture the text value for 'high' temperature;
        5.Get locator for 'low' temperature;
        6.Capture the text value for 'low' temperature;
        7.Convert text values into int values for all temperatures, ignoring a degree sign.
        8.Compare int values to verify if 'feels-like' temperature is less than the 'high'
        and more than 'low' temperatures values.

         */
       System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");

        WebElement feelsLike = driver.findElement(By.xpath("//span[@class='feels-like-text']"));
        String feelsLikeT = feelsLike.getText();

        WebElement high = driver.findElement(By.xpath("//span[@class='high-temp-text']"));
        String highT = high.getText();

        WebElement low = driver.findElement(By.xpath("//span[@class='low-temp-text']"));
        String lowT = low.getText();

        //illegal symbol °
        int sizeFeelsLike = feelsLikeT.length();
        int feelsLikeNum = Integer.valueOf(feelsLikeT.substring(0,sizeFeelsLike-1));

        int sizeHigh = highT.length();
        int highNum = Integer.valueOf(highT.substring(0,sizeHigh-1));

        int sizeLow = lowT.length();
        int lowNum = Integer.valueOf(lowT.substring(0,sizeLow-1));

        Assert.assertTrue(feelsLikeNum>lowNum && feelsLikeNum< highNum,"'Feels -like' temperature is less than the 'low' or more than 'high' temperature");

    }

    //TC3
    @Test
    public void verifyConversion() {

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");

        /*
        1. get locator for a current temperature (in F);
        2. capture the current temperature value (string);
        3. covert string value to integer one;
        4. get a locator for a dropdown, click;
        5. get a locator for a T (C) button, click;
        6. use the locator for a current temperature to capture a new value (string);
        7. covert string value to integer ones;
        8. convert T(F) to T(C) [2];
        9. verify if a converted value T(C) [2] is equal to captured one ( T(C) ).
         */
        WebElement tempValueF = driver.findElement(By.xpath("//span[@class='summary swap']"));
        String tempTextF = tempValueF.getText();     //47° clear
        String [] tempArrayF = tempTextF.split(" ");
        int numF = Integer.valueOf(tempArrayF[0].substring(0, tempArrayF[0].length() -1));    //46

        WebElement button = driver.findElement(By.xpath("//div[@class='selectric']//b[@class='button'][1]"));
        button.click();
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement tempButtonC = driver.findElement(By.xpath("//li[contains(text(),'mph') and @class='last'][1]"));
        tempButtonC.click();

        String tempValueC = driver.findElement(By.xpath("//span[@class='summary swap']")).getText();
        String[] arrC = tempValueC.split(" ");
        int numC = Integer.valueOf(arrC[0].substring(0,arrC[0].length()-1));
        int numExpectedC = (numF -32) * 5/9;

        Assert.assertEquals(numC,numExpectedC, "Temperature values do not convert as expected");

    }

    //TC2
   @Test
    public void verifyReverseChronOrder() {
       System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
       WebDriver driver = new ChromeDriver();
       driver.get("https://blog.darksky.net/");

       WebElement firstDate = driver.findElement(By.xpath("//time[text()='August 1, 2020']"));
       String firstDateSt = firstDate.getText();
       Date firstParsed = null;
       SimpleDateFormat df = new SimpleDateFormat("MMMM dd,yyyy");
           try {
               firstParsed =df.parse(firstDateSt);
           } catch (ParseException e) {
               e.printStackTrace();
           }
       

       WebElement secondDate = driver.findElement(By.xpath("//time[text()='July 1, 2020']"));
       String secondDateSt = secondDate.getText();
       Date secondParsed = null;
       try {
           secondParsed= df.parse(secondDateSt);
       } catch (ParseException e) {
           e.printStackTrace();
       }

       WebElement thirdDate = driver.findElement(By.xpath("//time[text()='March 31, 2020']"));
       String thirdDateSt = thirdDate.getText();
       Date thirdParsed = null;
       try {
           thirdParsed = df.parse(thirdDateSt);
       } catch (ParseException e) {
           e.printStackTrace();
       }


       Assert.assertTrue(firstParsed.after(secondParsed) && secondParsed.after(thirdParsed), "Dates are in chronological order");


   }


}
