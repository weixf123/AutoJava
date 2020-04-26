package com.testing.class6;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {

	public static void main(String[] args) {
//		String cmd="cmd /c start appium -a 127.0.0.1 -p 12345 -g E:\\appium.log --local-timezone --log-timestamp";
		String cmd="cmd /c start appium -a 127.0.0.1 -p 12345";
		try {
			Runtime.getRuntime().exec(cmd);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName", "127.0.0.1:7555");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("appPackage", "com.hexinpass.shequ");
		capabilities.setCapability("appActivity", ".activity.SplashActivity");
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("udid", "127.0.0.1:7555");
		
		try {
			AndroidDriver driver=new AndroidDriver<>(new URL("http://127.0.0.1:12345/wd/hub"),capabilities);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("com.hexinpass.shequ:id/bottom_account")).click();
			driver.findElement(By.id("com.hexinpass.shequ:id/tv_name")).click();
		    driver.findElement(By.xpath("//android.widget.EditText[@text='请输入手机号']")).sendKeys("15882195751");
		    driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.EditText")).sendKeys("A1234567");
		    driver.findElement(By.id("com.hexinpass.shequ:id/login_commit")).click();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
