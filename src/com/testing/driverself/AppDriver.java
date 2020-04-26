package com.testing.driverself;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.testing.autocommon.AutoLogger;

import io.appium.java_client.android.AndroidDriver;

public class AppDriver {
	public AndroidDriver driver=null;
	public AppDriver(String devices,String plVersion,String appPak,String appAct,String appiumServerIP,String waitTime) {
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName", devices);
		capabilities.setCapability("PlatformName", "Android");
		capabilities.setCapability("platformVersion", plVersion);
		capabilities.setCapability("appPackage", appPak);
		capabilities.setCapability("appActivity", appAct);
		//可选参数
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("udid", devices);
		
		try {
			AndroidDriver driver=new AndroidDriver<>(new URL(appiumServerIP),capabilities);
			int t=1000;
			t=Integer.parseInt(waitTime);
			Thread.sleep(t*1000);
			AutoLogger.log.info("APP正在启动中..........");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("APP启动失败，请检查配置");
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	
	public AndroidDriver getdriver() {
		return this.driver;
	}
	

}
