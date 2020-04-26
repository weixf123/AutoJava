package com.testing.class1;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.driverself.GoogleDriver;

public class GoogleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoogleDriver driver=new GoogleDriver("ExeDriver/chromedriver.exe");
		WebDriver gg=driver.getdriver();
		gg.get("https://www.baidu.com");
		WebElement element=gg.findElement(By.name("wd"));
		element.sendKeys("特斯汀学院");
		element.submit();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Page title is: " + gg.getTitle());
		gg.quit();
		
	}

}
