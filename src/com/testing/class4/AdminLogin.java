package com.testing.class4;

import com.testing.keyword.KeywordOfWeb;

public class AdminLogin {
	public static void main(String[] args) {
		KeywordOfWeb kw=new KeywordOfWeb();
		kw.openBrowser("chrome");
		adminLogin(kw);
	}
	private static void adminLogin(KeywordOfWeb kw) {
		kw.visitWeb("http://112.74.191.10:8000/Admin/Admin/login");
		kw.inputByXpath("//input[@name='username']", "admin");
		kw.inputByXpath("//input[@name='password']", "123456");
		kw.inputByXpath("//input[@name='vertify']", "1");
		kw.click("//input[@name='submit']");
		kw.halt("2");
	}

}
