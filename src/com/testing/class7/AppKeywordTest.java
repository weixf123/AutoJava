package com.testing.class7;

import com.testing.driverself.AppDriver;
import com.testing.keyword.KeywordOfApp;

import io.appium.java_client.android.AndroidDriver;

public class AppKeywordTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfApp kwApp=new KeywordOfApp();
		kwApp.startAppium("12345", "10");
		kwApp.runAPP("6.0.1", "127.0.0.1:7555","com.hexinpass.shequ", ".activity.SplashActivity", "http://127.0.0.1:12345/wd/hub", "10");
		kwApp.wait("10");
		kwApp.click("com.hexinpass.shequ:id/bottom_account");
		kwApp.wait("2");
		kwApp.click("com.hexinpass.shequ:id/tv_name");
		kwApp.wait("2");
		kwApp.input("//android.widget.EditText[@text='请输入手机号']", "15882195751");
		kwApp.input("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.EditText", "A1234567");
		kwApp.wait("2");
		kwApp.click("com.hexinpass.shequ:id/login_commit");
		kwApp.quitApp();
		

	}

}
