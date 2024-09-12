package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {

    AndroidDriver driver;
    @BeforeClass
    public void setUp()throws MalformedURLException{
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName","SM-A546E/DS");
        dc.setCapability("udid","RRCW307WXRA");
        dc.setCapability("platformName","android");
        dc.setCapability("platformVersion","11");
        dc.setCapability("appPackage","com.google.android.calculator");
        dc.setCapability("appActivity","com.android.calculator2.Calculator");
        dc.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
    }

    @AfterClass
    public void finish(){
        delay(3);
        driver.quit();
    }

    @Test
    public void testAdd(){
        MobileElement btnOne = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_1");
        btnOne.click();
        MobileElement btnPlus = (MobileElement) driver.findElementById("com.google.android.calculator:id/op_add");
        btnPlus.click();
        MobileElement btnTwo = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
        btnTwo.click();
        MobileElement btnEquals = (MobileElement) driver.findElementById("com.google.android.calculator:id/eq");
        btnEquals.click();
        MobileElement resultFinal = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        String actual = resultFinal.getText();
        Assert.assertEquals(actual,"3");
    }
    @Test
    public void testSubtract(){
        MobileElement btnThree = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_3");
        btnThree.click();
        MobileElement btnSub = (MobileElement) driver.findElementById("com.google.android.calculator:id/op_sub");
        btnSub.click();
        MobileElement btnFour = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_4");
        btnFour.click();
        MobileElement btnEquals = (MobileElement) driver.findElementById("com.google.android.calculator:id/eq");
        btnEquals.click();
        MobileElement resultFinal = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        String actual = resultFinal.getText();
        Assert.assertEquals(actual,"−1");

    }

    public void delay(long detik){
        try{
            Thread.sleep(detik*1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

}
