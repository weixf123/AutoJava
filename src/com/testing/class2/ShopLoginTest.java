package com.testing.class2;

import org.openqa.selenium.By;

import com.testing.keyword.KeywordOfWeb;

public class ShopLoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfWeb kw=new KeywordOfWeb();
		kw.openBrowser("chrome");
		kw.visitWeb("http://www.testingedu.com.cn:8000");
//		kw.multiElementText("//ul[@id='navitems']/li/a");
		kw.click("//a[text()='登录']");
		kw.inputByLocator(By.name("username"), "13800138006");
		kw.inputByXpath("//input[@name='password']", "123456");
		kw.inputByXpath("//input[@name='verify_code']", "2");
		kw.click("//a[@name='sbtbutton']");
		kw.halt("3");
		kw.assertElementTextIs("//a[@class='mu-m-phone']", "cc2");
		kw.halt("3");
		kw.click("//a[text()='首页']");
		kw.hover("//a[text()='手机数码']");
		kw.halt("3");
		kw.click("//a[text()='手机' and not(@class)]");
		kw.closeBrowser();

	}

}
