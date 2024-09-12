package com.juaracoding;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CatatanKeuanganTest {
    private DesiredCapabilities dc;
    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "SM-A546E/DS");
        dc.setCapability("udid", "RRCW307WXRA");
        dc.setCapability("platformName", "android");
        dc.setCapability("platformVersion", "11");
        dc.setCapability("appPackage", "com.chad.financialrecord");
        dc.setCapability("appActivity", "com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset", "true");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url,dc);
    }

    @AfterClass
    public void finish(){
        delay(5);
        driver.quit();
    }

    @Test(priority = 1)
    public void testPemasukan(){
        delay(6);

        //test pemasukan gaji
        driver.findElementByXPath("//android.widget.ImageButton[@resource-id='com.chad.financialrecord:id/fabMenu']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/btnIncome']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.EditText[@resource-id='com.chad.financialrecord:id/etAmount']").sendKeys("20000000");
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Gaji Bulan Agustus");
        delay(3);
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.chad.financialrecord:id/btSave']").click();

        //test pemasukan tabungan
        delay(3);
        driver.findElementByXPath("//android.widget.ImageButton[@resource-id='com.chad.financialrecord:id/fabMenu']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/btnIncome']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.Spinner[@resource-id='com.chad.financialrecord:id/spCategory']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvName' and @text='Tabungan']").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id='com.chad.financialrecord:id/etAmount']").sendKeys("10000000");
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Tabungan Januari - Agustus");
        delay(3);
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.chad.financialrecord:id/btSave']").click();

        //sebelum melakukan assert, klik widget untuk masuk ke bagian rincian transaksi
        delay(3);
        driver.findElementByXPath("//android.widget.ExpandableListView[@resource-id='com.chad.financialrecord:id/elTransaction']/android.widget.RelativeLayout/android.widget.LinearLayout").click();

        //assert untuk mengecek pemasukan yang sudah terhitung
        String actualPemasukan = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvIncome']").getText();
        Assert.assertEquals(actualPemasukan,"30.000.000","Pemasukan Sesuai");

        //kembali ke halaman utama
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    @Test(priority = 2)
    public void testPengeluaran(){
        delay(3);
        //pengeluaran makan
        driver.findElementByXPath("//android.widget.ImageButton[@resource-id='com.chad.financialrecord:id/fabMenu']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.Spinner[@resource-id='com.chad.financialrecord:id/spCategory']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvName' and @text='Makanan']").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id='com.chad.financialrecord:id/etAmount']").sendKeys("1500000");
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Biaya Makan");
        delay(3);
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.chad.financialrecord:id/btSave']").click();

        //pengeluaran motor
        delay(1);
        driver.findElementByXPath("//android.widget.ImageButton[@resource-id='com.chad.financialrecord:id/fabMenu']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.Spinner[@resource-id='com.chad.financialrecord:id/spCategory']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvName' and @text='Motor']").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id='com.chad.financialrecord:id/etAmount']").sendKeys("1500000");
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Kredit Motor");
        delay(3);
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.chad.financialrecord:id/btSave']").click();

        //pengeluaran tagihan
        driver.findElementByXPath("//android.widget.ImageButton[@resource-id='com.chad.financialrecord:id/fabMenu']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.Spinner[@resource-id='com.chad.financialrecord:id/spCategory']").click();
        delay(1);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvName' and @text='Tagihan']").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id='com.chad.financialrecord:id/etAmount']").sendKeys("3000000");
        driver.findElementById("com.chad.financialrecord:id/etNote").sendKeys("Tagihan Listrik dan KPR");
        delay(3);
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.chad.financialrecord:id/btSave']").click();

        //mengarah ke halaman rincian transaksi
        driver.findElementByXPath("//android.widget.ExpandableListView[@resource-id='com.chad.financialrecord:id/elTransaction']/android.widget.RelativeLayout/android.widget.LinearLayout").click();
        delay(2);

        //assert untuk mengecek pengeluaran yang sudah terhitung
        String actualPemasukan = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvExpense']").getText();
        Assert.assertEquals(actualPemasukan,"6.000.000","Pengeluaran Sesuai");

        //kembali ke halaman utama
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    @Test(priority = 3)
    public void testCekSaldo(){
        delay(3);
        //mengarah ke halaman rincian transaksi
        driver.findElementByXPath("//android.widget.ExpandableListView[@resource-id='com.chad.financialrecord:id/elTransaction']/android.widget.RelativeLayout/android.widget.LinearLayout").click();

        //cek total saldo menggunakan assert
        String actualSaldo = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.chad.financialrecord:id/tvBalance']").getText();
        Assert.assertEquals(actualSaldo,"24.000.000","Saldo Sesuai");
    }

    // inisiasi method delay
    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


