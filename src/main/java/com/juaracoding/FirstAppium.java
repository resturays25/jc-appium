package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstAppium {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName","SM-A546E/DS");
        dc.setCapability("udid","RRCW307WXRA");
        dc.setCapability("platformName","android");
        dc.setCapability("platformVersion","11");
        dc.setCapability("appPackage","com.google.android.calculator");
        dc.setCapability("appActivity","com.android.calculator2.Calculator");
        dc.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        // 1+2 = 3
        MobileElement btnOne = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_1");
        btnOne.click();
        MobileElement btnPlus = (MobileElement) driver.findElementById("com.google.android.calculator:id/op_add");
        btnPlus.click();
        MobileElement btnTwo = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
        btnTwo.click();
        MobileElement btnEquals = (MobileElement) driver.findElementById("com.google.android.calculator:id/eq");
        btnEquals.click();
        MobileElement resultFinal = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        System.out.println(resultFinal.getText());

        driver.quit();


    }
}