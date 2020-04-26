package com.testing.adminShopping;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testing.keyword.KeywordOfWeb;

public class AdminLoginPage {
	public KeywordOfWeb kw ;
	public String url="http://112.74.191.10:8000/Admin/Admin/login";
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement user;
	
	@FindBy(xpath = "//input[@name='password']")
	public WebElement pwd;
	
	@FindBy(xpath = "//input[@name='vertify']")
	public WebElement vertifycord;
	
	@FindBy(xpath = "//input[@name='submit']")
    public WebElement submitButton;
	
	public AdminLoginPage(KeywordOfWeb kWeb) {
		kw=kWeb;
		PageFactory.initElements(kw.driver, this);
	}
	public void load() {
		kw.visitWeb(url);
	}
	public void loginAdmin() {
		try {
			user.sendKeys("admin");
			pwd.sendKeys("123456");
			vertifycord.sendKeys("1");
			submitButton.click();
			kw.halt("2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
