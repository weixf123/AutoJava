package com.testing.driverself;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleDriver {
	private WebDriver driver=null;
	public GoogleDriver(String driverpath) {
		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-infobars");
		options.addArguments("--user-data-dir=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
		options.addArguments("--start-maximized");
		
		try {
			this.driver=new ChromeDriver(options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("log--error:创建drivers失败！");
		}
		
	}
	public WebDriver getdriver() {
		return this.driver;
	}

}
