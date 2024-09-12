package com.juaracoding;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BioskopTIXTest {
    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName","SM-A546E/DS");
        dc.setCapability("udid","RRCW307WXRA");
        dc.setCapability("platformName","android");
        dc.setCapability("platformVersion","11");
        dc.setCapability("appPackage","id.tix.android");
        dc.setCapability("appActivity",".splash.view.SplashActivity");
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
    public void testMovieSearch(){
        delay(10);
        driver.findElementById("id.tix.android:id/et_input").click();
        delay(2);
        driver.findElementById("id.tix.android:id/et_input").sendKeys("seni");
        delay(2);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id='id.tix.android:id/iv_picture_of_movie'])[1]").click();
        delay(3);
        String actual = driver.findElementByXPath("//android.widget.TextView[@resource-id='id.tix.android:id/tv_title' and @text='SENI MEMAHAMI KEKASIH']").getText();
        Assert.assertEquals(actual,"SENI MEMAHAMI KEKASIH");
    }

    @Test
    public void testChangeLocation(){
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        driver.findElementById("id.tix.android:id/tv_city").click();
        delay(2);
        driver.findElementById("id.tix.android:id/edt_search").click();
        String cityName = "KARAWANG";
        driver.findElementById("id.tix.android:id/edt_search").sendKeys(cityName);
        driver.findElementById("id.tix.android:id/tvCityName").click();
        delay(3);
        String actual = driver.findElementById("id.tix.android:id/tv_city").getText();
        Assert.assertEquals(actual,cityName);
    }

    @Test
    public void testMyAccountScreen(){
        delay(10);
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Kembali ke atas']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='id.tix.android:id/tv_cancel']").click();
        delay(1);
        driver.findElementById("id.tix.android:id/iv_account").click();
        delay(2);
        String txtLoginOrRegist = driver.findElementById("id.tix.android:id/btn_login_or_regist").getText();
        String txtVersion = driver.findElementByXPath("//android.widget.TextView[@resource-id='id.tix.android:id/tv_version']").getText();
        Assert.assertEquals(txtLoginOrRegist,"Masuk atau Daftar Akun");
        Assert.assertEquals(txtVersion,"3.10.0");
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        delay(2);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(10,10));
        touchAction.moveTo(PointOption.point(10,100));
        touchAction.release().perform();
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
