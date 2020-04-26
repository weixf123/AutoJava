package com.testing.class2;

import com.testing.keyword.KeywordOfWeb;

public class TestKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfWeb kw=new KeywordOfWeb();
		kw.openBrowser("chrome");
		kw.visitWeb("https://www.baidu.com");
		kw.inputByName("wd", "特斯汀学院");
		kw.click("//input[@id='su']");
		kw.halt("5");
		String title=kw.getTitle();
		System.out.println("标题是："+title);
		kw.assertContains("特斯汀学院");
		kw.closeBrowser();

	}

}
